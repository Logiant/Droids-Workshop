package routines;

import main.Droid;
import main.World;

public class Repeat extends Routine{

	private Routine routine;
	private int times;
	private int originalTimes;
	
	public Repeat(Routine routine) {
		this(routine, -1);
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
	public void restart() {
		this.times = originalTimes;
	}
	
	@Override
	public void act(Droid droid, World world) {
		if (routine.isFailure()) {
			fail();
		} else if(routine.isSuccess()) {
			if (times == 0) {
				succeed();
				return;
			} else {
				times --;
				routine.restart();
				routine.start();
			}
		}
		if (routine.isRunning()) {
			routine.act(droid, world);
		}
	}
}
