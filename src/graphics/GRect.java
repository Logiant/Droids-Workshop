package graphics;

import org.newdawn.slick.geom.Vector2f;

public class GRect {

	public int xMin; public int xMax;
	public int yMin; public int yMax;
	
	public GRect(Vector2f position, Vector2f size) {
		xMin = (int) (position.x + 0.5);
		xMax = (int) (position.x + size.x + 0.5);
		yMin = (int) (position.y + 0.5);
		yMax = (int) (position.y + size.y + 0.5);

	}
	
	@Override
	public String toString() {
		return "GRect: (" + xMin + ", " + yMin + "), (" + xMax + ", " + yMax + ")";
	}
}
