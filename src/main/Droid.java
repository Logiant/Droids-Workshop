/**
 * The droid class contains an object for the AI to move around
 * @author Logan Beaver
 */
package main;

import graphics.GRect;
import graphics.Graphics;
import graphics.TextRect;

import org.newdawn.slick.geom.Vector2f;

import routines.*;

public class Droid {

	//the name of the droid
	private String name;
	//the AI routine to follow
	private Routine brain;
	//generic statistics (should be set by a DroidFactory instead of using defaults)
	int range = 128;
	int damage = 1;
	int health = 10;
	//important information about the world
	private World world;
	private Droid target;
	//animation frame information
	private int animationFrames = 4;
	private int currentFrame = 0;
	//animation timing information
	private float frameTime = 1/12f; //seconds/frame
	private float time = 0;
	//droid size (pixels)
	public static int SIZE = 32;
	//current droid position
	private Vector2f position;
	//ID of the droids texture
	private int textureID;
	
	
	public Droid(String name, float xPos, float yPos, Graphics g, World world, Routine routine, int textureID) {
		this.name = name;
		position = new Vector2f(xPos, yPos);
		this.world = world;
		this.textureID = textureID;		
		brain = routine;
	}
		
	public void draw(Graphics g) {
		Vector2f textPos = new Vector2f(0.25f * currentFrame, 0);
		Vector2f textSize = new Vector2f(0.25f, 1);
		Vector2f size = new Vector2f(SIZE, SIZE);
		g.draw(new GRect(position, size), new TextRect(textPos, textSize), textureID);
	}
	
	public void update(float dt) {
		if (brain.getState() == null) { //start the routine if it has not been started yet
			brain.start();
		}
		brain.act(this, world);
		//update the animation information
		time += dt;
		if (time >= frameTime) {
			time = 0;
			currentFrame = (currentFrame + 1) % animationFrames;
		}
		
	}
	
	public void setTarget(Droid target) {
		this.target = target;
	}
	
	public Droid getTarget() {
		return target;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	@Override
	public String toString() {
		return "Droid " + name;
	}

	public Vector2f getPosition() {
		return new Vector2f(position);
	}
	
	public Vector2f getCenter() {
		return new Vector2f(position.x + SIZE/2, position.y + SIZE/2);
	}
	
	public float getRange() {
		return range;
	}

	public void move(Vector2f movement) {
		position.add(movement);
	}
}
