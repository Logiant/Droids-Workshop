package main;

import graphics.GRect;
import graphics.Graphics;
import graphics.TextRect;

import org.newdawn.slick.geom.Vector2f;

import routines.*;

public class Droid {

	private String name;
	private Routine routine;
	
	int range;
	int damage;
	int health = 10;
	
	World world;
	
	public static int SIZE = 32;
	private Vector2f position;
	
	private int textureID;
	
	public Droid(String name, float xPos, float yPos, Graphics g, World world) {
		this.name = name;
		position = new Vector2f(xPos, yPos);
		this.world = world;
		textureID = g.loadImage("Droid");
		
		routine = new Repeat(new Wander(world));
		
	}
		
	public void draw(Graphics g) {
		Vector2f textPos = new Vector2f(0, 0);
		Vector2f textSize = new Vector2f(1, 1);
		Vector2f size = new Vector2f(SIZE, SIZE);
		g.draw(new GRect(position, size), new TextRect(textPos, textSize), textureID);
	}
	
	public void update() {
		if (routine.getState() == null) {
			routine.start();
		}
		routine.act(this, world);
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

	public void move(Vector2f movement) {
		position.add(movement);
	}
}
