package routines;

import org.newdawn.slick.geom.Vector2f;

import main.Droid;
import main.World;

public class MoveTo extends Routine {

    final protected int destX;
    final protected int destY;

    public MoveTo(int destX, int destY) {
        super();
        this.destX = destX;
        this.destY = destY;
    }

    @Override
    public void restart() {
        start();
    }

    @Override
    public void act(Droid droid, World board) {
        if (isRunning()) {
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
    	Vector2f movement = new Vector2f(0, 0);
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
        droid.move(movement);
        if (isDroidAtDestination(droid)) {
            succeed();
        }
    }

    private boolean isDroidAtDestination(Droid droid) {
        return destX == droid.getPosition().x && destY == droid.getPosition().y;
    }
}
