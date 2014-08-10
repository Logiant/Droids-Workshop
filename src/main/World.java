package main;

import graphics.GRect;
import graphics.Graphics;
import graphics.TextRect;

import org.newdawn.slick.geom.Vector2f;

public class World {

	private int[][] tiles;
	private int width;
	private int height;
	
	private int tileId;

	public static final int TILE_SIZE = 32;

	public World(int width, int height) {
		this.width = width;
		this.height = height;

		tiles = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tiles[i][j] = 0;
			}
		}
		tiles[2][1] = 1;
	}
	
	public void initialize(Graphics g) {
		tileId = g.loadImage("Ground");
	}

	public void draw(Graphics g) {
		Vector2f textPos = new Vector2f(0, 0);
		Vector2f textSize = new Vector2f(1, 1);
		Vector2f size = new Vector2f(TILE_SIZE, TILE_SIZE);
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				Vector2f position = new Vector2f(i*TILE_SIZE, j*TILE_SIZE);
				g.draw(new GRect(position, size), new TextRect(textPos, textSize), tileId);
			}
		}
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

}
