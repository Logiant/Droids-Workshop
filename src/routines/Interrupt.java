/**
 * This routine takes in two routines, Primary and Interrupt
 * Both routines run, and if Interrupt succeeds it then causes the primary routine to fail
 * This is used to interrupt the "Wander" routine with the "Select Target" routine so that there is continuous target selection
 * @author Logan Beaver
 */
package routines;

import main.Droid;
import main.World;

public class Interrupt extends Routine {

	//the primary routine
	private Routine primary;
	//the interrupting routine
	private Routine interrupt;


	public Interrupt(Routine primary, Routine interrupt) {
		super();
		this.primary = primary;
		this.interrupt = interrupt;
	}


	@Override
	public void reset() {
		primary.reset();
		interrupt.reset();
	}

	@Override
	public void act(Droid droid, World world) {
		if (this.isRunning()) {
			if (!interrupt.isRunning()) { //always run the interrupt
				interrupt.start();
			}
			interrupt.act(droid, world);
			primary.act(droid, world);
			//if the primary fails or the interrupt succeeds, we fail
			if (interrupt.isSuccess() || primary.isFailure()) {
				this.fail();
				return;
			}
			//we succeed if the primary succeeds
			if (primary.isSuccess()) {
				this.succeed();
				return;
			}
		}
	}

	@Override
	public void start() {
		super.start();
		primary.start();
		interrupt.start();
	}

	@Override
	public String toString() {
		return (primary + " interrupted by " + interrupt);
	}

}
