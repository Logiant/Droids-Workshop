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
	
	public static int screenWidth = 800;
	public static int screenHeight = 800;
	
	private Droid droid;
	
	public Main() {
		
		setupDisplay();
		setupGraphics();
		
		initialize();
		
		droid = new Droid("Droidy", 10, 10, g, world);		

		
		while (!Display.isCloseRequested()) {
			cleanScreen();
			update();
			droid.update();
			droid.draw(g);
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public void update() {
		cam.update();
		
		
		render();
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
		world = new World(20, 20);
		cam = new Camera(-50, -50);
		
		world.initialize(g);
	}
	
	public void setupDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
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
		glEnable(GL_CULL_FACE);// Enables face culling (working)
		glCullFace(GL_BACK); // Doesn't draw back faces

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}

}
