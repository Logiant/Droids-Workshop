/**
 * The node routines are ways to handle branches in the AI tree
 * the node can be a Sequence (AND) or a Selector (OR)
 * Sequence runs each routine until it passes, then moves on to the next
 * Selector runs each routine until it fails, then moves on to the next
 * @author Logan Beaver
 */
package routines;

import java.util.LinkedList;
import java.util.List;

import main.Droid;
import main.World;

public class Node extends Routine {

	public enum NodeTypes {
		Sequence,
		Selector
	}

	//list of routines to run
	private List<Routine> routines;
	private NodeTypes type;

	public Node(List<Routine> routines, NodeTypes type) {
		this.routines = new LinkedList<Routine>();
		this.routines.addAll(routines);
		this.type = type;
	}

	@Override
	public void start() {
		super.start();
		for (Routine r: routines)
			r.start();
	}


	@Override
	public void reset() {
		for (Routine r: routines)
			r.reset();
	}

	@Override
	public void act(Droid droid, World world) {
		if (this.isRunning()) {
			for (Routine r: routines) { //start any routines that have not been started
				if (r.getState() == null) {
					r.start();
				}
			}
			switch (type) {
			case Sequence: //AND
				for (Routine r: routines) {
					if (r.isRunning()) { //do one routine until it passes or fails
						r.act(droid, world);
						return;
					}
					if (r.isFailure()) { //fail if any routine fails
						this.fail();
						return;
					}
				}
				this.succeed(); //all routines did not fail to get here
				break;
			case Selector: //OR
				for (Routine r: routines) {
					if (r.isRunning()) { //do one routine until it passes or fails
						r.act(droid, world);
						return;
					}
					if (r.isSuccess()) { //succeed if any routine succeeds
						this.succeed();
						return;
					}
				}
				this.fail(); //no routines have succeeded if we get here
				break;
			}
		}
	}

	@Override
	public String toString() {
		String nodes = "";
		for (Routine r: routines)
			nodes += r + ", ";
		return type + " node of:: " + nodes + " _ ";
	}
}
