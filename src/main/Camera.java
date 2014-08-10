package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

	private Vector2f transform;

	public Camera() {
		this(0, 0);
	}
	
	public Camera(float x, float y) {
		transform = new Vector2f(x, y);
	}


	public void update() {
		handleInput();
		GL11.glTranslatef(-transform.x, -transform.y, -0);
	}

	public void handleInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			transform.y --;
		}if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			transform.y ++;
		}if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			transform.x --;
		}if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			transform.x ++;
		}
	}
}
