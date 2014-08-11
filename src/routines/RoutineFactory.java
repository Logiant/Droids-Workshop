/**
 * the routine factory produces different AI trees using static methods
 * routine visualizations can be seen in the Behavior Tree Diagrams folder
 * @author Logan Beaver
 */
package routines;

import java.util.ArrayList;
import java.util.List;

import main.World;

public class RoutineFactory {

	public static Routine FollowRoutine(World world) {
		List<Routine> firstBranch = new ArrayList<Routine>();
		List<Routine> secondBranch = new ArrayList<Routine>();
		secondBranch.add(new SelectTarget());
		secondBranch.add(new FollowTarget());
		firstBranch.add(new Node(secondBranch, Node.NodeTypes.Sequence));
		firstBranch.add(new Interrupt(new Wander(world), new SelectTarget()));
		return (new Repeat(new Node(firstBranch, Node.NodeTypes.Selector)));
	}
	
	public static Routine WanderRoutine(World world) {
		return (new Repeat(new Wander(world)));
	}
}
