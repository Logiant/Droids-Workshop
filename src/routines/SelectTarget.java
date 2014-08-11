/**
 * this routine takes in a list of droids and finds the first droid closes to the current position
 * @author Logan Beaver
 */
package routines;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import main.Droid;
import main.World;

public class SelectTarget extends Routine {

	@Override
	public void reset() {
		//do nothing
	}

	@Override
	public void act(Droid droid, World world) {
		if (this.isRunning()) { //do nothing if not running
			float range = droid.getRange();
			//closest droid info
			float closestDistance = range + 1;
			int droidId = -1;
			
			List<Droid> droids = world.getDroids();
			
			for (int i = 0; i < droids.size(); i++) {
				Droid d= droids.get(i);
				float distance = getDistance(droid, d);
				if (droid != d && distance < closestDistance) {
					droidId = i;
					closestDistance = distance;
				}
			}
			if (closestDistance <= range) {
				this.succeed();
				droid.setTarget(droids.get(droidId));
				return; //we found a droid in range!
			}
			this.fail(); //no droids found
			droid.setTarget(null); //no target
		}
	}

	private float getDistance(Droid droid, Droid d) {
		Vector2f diff = new Vector2f(droid.getCenter().sub(d.getCenter()));
		return diff.length() ;
	}

	@Override
	public String toString() {
		return "Select Target";
	}
}
