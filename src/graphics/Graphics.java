package graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Graphics {

	List<Texture> textures;
	int currentId = -1;
	List<String> paths;

	public Graphics() {
		textures = new ArrayList<Texture>();
		paths = new ArrayList<String>();
	}

	public int loadImage(String path) {
		int id = -1;
		if (paths.contains(path)) {
			id = paths.indexOf(path);
		} else {
			try {
				textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + path + ".png")));
				id = paths.size();
				paths.add(path);
			} catch (IOException e) {
				System.err.println("Could not load file " + path);
				System.exit(-1);
			}

		}
		return id;
	}

	public void draw(GRect r, TextRect t, int id) {
		if (id >= 0 && id < textures.size()) {
			textures.get(id).bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(t.xMin, t.yMax);
			GL11.glVertex2f(r.xMin, r.yMin);
			
			GL11.glTexCoord2f(t.xMin, t.yMin);
			GL11.glVertex2f(r.xMin, r.yMax);
			
			GL11.glTexCoord2f(t.xMax, t.yMin);
			GL11.glVertex2f(r.xMax, r.yMax);
			
			GL11.glTexCoord2f(t.xMax, t.yMax);
			GL11.glVertex2f(r.xMax, r.yMin);
			GL11.glEnd();
		}
	}
}
