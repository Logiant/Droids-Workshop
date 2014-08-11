/**
 * this is the world class, it holds the tiles the droids move on and the droids themselves
 * @author Logan Beaver
 */
package main;

import java.util.ArrayList;
import java.util.List;

import graphics.GRect;
import graphics.Graphics;
import graphics.TextRect;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import routines.RoutineFactory;

public class World {

	private int[][] tiles;
	private int width;
	private int height;
	
	//texture IDs
	private int tileId;
	private int wanderID;
	private int followID;

	//the size of a tile (pixels)
	public static final int TILE_SIZE = 32;

	private List<Droid> droids;

	public World(int width, int height) {
		this.width = width;
		this.height = height;

		//create the world as a bunch of zeroes
		tiles = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tiles[i][j] = 0;
			}
		}
		//set a tile to be a 1
		tiles[2][1] = 1;
		droids = new ArrayList<Droid>();
	}

	public void initialize(Graphics g) {
		tileId = g.loadImage("Ground");
		wanderID = g.loadImage("Animated Droid");
		followID = g.loadImage("Animated Droid 2");
	}

	public void addFollowDroid(String name, float xPos, float yPos, Graphics g) {
		droids.add(new Droid(name, xPos, yPos, g, this, RoutineFactory.FollowRoutine(this), followID));		
	}

	public void addWanderDroid(String name, float xPos, float yPos, Graphics g) {
		droids.add(new Droid(name, xPos, yPos, g, this, RoutineFactory.WanderRoutine(this), wanderID));
	}


	public void update(float dt) {
		for (int i = droids.size() - 1; i >= 0; i--) { //updates the droids looping backward
			Droid d = droids.get(i);
			if (d.isAlive()) {
				d.update(dt);
			} else { //remove dead droids
				droids.remove(i);
			}
		}
	}

	public void draw(Graphics g) {
		Vector2f textPos = new Vector2f(0, 0);
		Vector2f textSize = new Vector2f(1, 1);
		Vector2f size = new Vector2f(Main.screenWidth, Main.screenHeight);
		Vector2f position = new Vector2f(0, 0);
		//	Vector2f size = new Vector2f(TILE_SIZE, TILE_SIZE);
		//	for (int j = 0; j < height; j++) {
		//		for (int i = 0; i < width; i++) {
		//			Vector2f position = new Vector2f(i*TILE_SIZE, j*TILE_SIZE);
		////Draw one giant unmoving square instead of a bunch of tiles
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		g.draw(new GRect(position, size), new TextRect(textPos, textSize), tileId);
		GL11.glPopMatrix();
		//		}
		//	}
		//draw the droids on top
		for (Droid d: droids)
			d.draw(g);
	}

	@Override
	public String toString() {
		String world = "";
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				world += tiles[i][j] + " ";
			}
			world += "\n";
		}
		return world;
	}

	public Vector2f getSize() {
		return new Vector2f(width, height);
	}

	public List<Droid> getDroids() {
		return droids;
	}

}
