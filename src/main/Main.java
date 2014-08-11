/**
 * The main Droid Workshop class that sets up the game and runs the main loop
 * @author Logan Beaver
 */
package main;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import graphics.Graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main {

	private Graphics g;
	private World world;
	private Camera cam;
	
	private float dt;
	private long lastTime;
		
	public static int screenWidth = 800;
	public static int screenHeight = 800;
	

	public Main() {
		
		//sets up OpenGL
		setupDisplay();
		//initializes a 2d orthographic projection
		setupGraphics();
		//initializes everything
		initialize();
		
		//easy way to set the number of each droid spawned
		int numFollow = 1;
		int numWander = 15;
		
		for (int i = 0; i < numFollow; i++) {
			world.addFollowDroid("Follow Mk." + i, 0 + 5*i, 10 + 5*i, g);
		}
		for (int i = 0; i < numWander; i++) {
			world.addWanderDroid("Wander Mk." + i, 150 + 10*i, 200 + 10*i, g);
		}


		lastTime = System.nanoTime();
		while (!Display.isCloseRequested()) {
			//clears the screen
			cleanScreen();
			//updates each object and renders
			update();
			
			//updates the display with new render information
			Display.update();
			//syncs the display to 60 FPS
			Display.sync(60);
		}
		//closes OpenGL when the display is closed
		Display.destroy();
	}
	
	public void update() {
		//finds the time between frames
		calcDT();
		//transforms the origin a small amount
		cam.update();
		//updates the world with the time since last update
		world.update(dt);
		//renders the world
		render();
	}
	
	public void calcDT() {
		long time = System.nanoTime();
		dt = (time - lastTime) / 1000000000.0f;
		lastTime = time;
	}
	public void render() {
		world.draw(g);
	}
	
	public void cleanScreen() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
	}
	
	public void initialize() {
		g = new Graphics();
		//the world is 20x20 cells
		world = new World(20, 20);
		//moves the world right so nothing walks off the screen accidentally
		cam = new Camera(-75, -75);
		//loads all the graphics used by world
		world.initialize(g);
	}
	
	public void setupDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.setTitle("Droid AI");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void setupGraphics() {
		GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glEnable(GL11.GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_CULL_FACE);// Enables face culling
		glCullFace(GL_BACK); // Don't draw back faces

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}

}
