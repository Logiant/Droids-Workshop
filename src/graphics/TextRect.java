/**
 * this class is used to simplify the process of drawing objects by holding texture coordinates
 * @author Logan Beaver
 */
package graphics;

import org.newdawn.slick.geom.Vector2f;

public class TextRect {
	
	public float xMin; public float xMax;
	public float yMin; public float yMax;
	
	public TextRect(Vector2f position, Vector2f size) {
		xMin = position.x;
		xMax = position.x + size.x;
		yMin = position.y;
		yMax = position.y + size.y;

	}
	
	@Override
	public String toString() {
		return "TextRect: (" + xMin + ", " + yMin + "), (" + xMax + ", " + yMax + ")";
	}

}
