/**
 * The repeat routine takes in a routine and repeats it a set number of times
 * @author Logan Beaver
 */
package routines;

import main.Droid;
import main.World;

public class Repeat extends Routine{

	//routine to repeat
	private Routine routine;
	//times left to repeat
	private int times;
	//number of times to repeat
	private int originalTimes;
	
	public Repeat(Routine routine) {
		this(routine, -1);
		//default to infinite (n < 0)
	}
	
	public Repeat(Routine routine, int times) {
		super();
		this.routine = routine;
		this.times = times;
		this.originalTimes = times;
	}
	
	@Override
	public void start() {
		super.start();
		routine.start();
	}
	
	@Override
	public void reset() {
		this.times = originalTimes;
	}
	
	@Override
	public void act(Droid droid, World world) {
		if(!routine.isRunning()) { //if the routine is not running
			if (times == 0) {
				succeed();
				return;
				//succeed if we have repeated n times
			} else {
				//decrement times to run, and restart routine
				times --;
				routine.reset();
				routine.start();
			}
		}
		//run the routine
		if (routine.isRunning()) {
			routine.act(droid, world);
		}
	}
	
	@Override
	public String toString() {
		return "Repeating " + routine;
	}
}
