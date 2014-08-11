/**
 * This routine uses a MoveTo to follow a target by tracking its position continuously
 * @author Logan Beaver
 */
package routines;

import main.Droid;
import main.World;

public class FollowTarget extends Routine{

	private MoveTo moveTo;

	@Override
	public void reset() {
		if (moveTo != null) {
			moveTo.reset();
		}
	}

	@Override
	public void act(Droid droid, World world) {
		if (this.isRunning()) {
			if (droid.getTarget() == null || !droid.getTarget().isAlive()) { //if there's no living target we fail
				this.fail();
				return;
			}
			//move toward the targets current position
			moveTo = new MoveTo(droid.getTarget().getPosition().x - 2 * Droid.SIZE, droid.getTarget().getPosition().y);
			moveTo.start();
			moveTo.act(droid, world);
			if (moveTo.isSuccess()) { //we succeed when we catch the target
				this.succeed();
			}
		}
	}

	@Override
	public String toString() {
		return "Follow Target";
	}

}
