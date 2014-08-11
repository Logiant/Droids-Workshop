/**
 * This routine tells the droid to MoveTo a specific location
 * It currently moves in a straight line to that point
 * @author Logan Beaver
 */
package routines;

import org.newdawn.slick.geom.Vector2f;

import main.Droid;
import main.World;

public class MoveTo extends Routine {

	//the destination variables
    final protected int destX;
    final protected int destY;

    public MoveTo(int destX, int destY) {
        super();
        this.destX = destX;
        this.destY = destY;
    }

    public MoveTo(float x, float y) {
    	this((int) x, (int) y);
    }

	@Override
    public void reset() {
        start();
    }

    @Override
    public void act(Droid droid, World board) {
        if (this.isRunning()) {
            if (!droid.isAlive()) {
                fail();
                return;
            }
            if (!isDroidAtDestination(droid)) {
                moveDroid(droid);
            }
        }
    }

    private void moveDroid(Droid droid) {
    	Vector2f position = droid.getPosition();
    	Vector2f movement = new Vector2f(0, 0); //the direction to move
        if (destY != position.y) {
            if (destY > position.y) {
                movement.y ++;
            } else {
                movement.y --;
            }
        }
        if (destX != position.x) {
            if (destX > position.x) {
                movement.x ++;
            } else {
                movement.x --;
            }
        }
        //command the droid to move
        droid.move(movement);
        if (isDroidAtDestination(droid)) {
            succeed();
        }
    }

    private boolean isDroidAtDestination(Droid droid) {
        return destX == droid.getPosition().x && destY == droid.getPosition().y;
    }
    
    @Override
    public String toString() {
    	return "Move To";
    }
}
