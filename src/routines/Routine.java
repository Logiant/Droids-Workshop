/**
 * Routines form the base of the Droid AI. They use a strategy pattern in a behavior tree to decide what to do next
 * @author Logan Beaver
 */

package routines;

import main.Droid;
import main.World;

public abstract class Routine {

	//there are three states of the routine
	public enum RoutineState {
		Success,
		Failure,
		Running
	}
	
	//current state
	protected RoutineState state;
	
	//default constructor
	protected Routine() {};
	
	//start tells the routine to run
	public void start() {
		this.state = RoutineState.Running;
	}
	
	//this is routine specific
	public abstract void reset();
	
	//this is routine specific - we need to know about the curent droid and its world
	public abstract void act(Droid droid, World world);
	
	//tell the routine to succeed
	protected void succeed() {
		this.state = RoutineState.Success;
	}
	
	//tell the routine to fail
	protected void fail() {
		this.state = RoutineState.Failure;
	}
	
	//check for success
	public boolean isSuccess() {
		return state.equals(RoutineState.Success);
	}
	
	//check for failure
	public boolean isFailure() {
		return state.equals(RoutineState.Failure);
	}
	
	//check if the routine is running
	public boolean isRunning() {
		return state.equals(RoutineState.Running);
	}
	
	//get the routine state
	public RoutineState getState() {
		return state;
	}
	
}
