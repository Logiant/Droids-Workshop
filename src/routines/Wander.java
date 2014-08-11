/**
 * The wander routine outputs a random location and uses a MoveTo routine to move there
 * @author Logan Beaver
 */
package routines;

import java.util.Random;

import main.Droid;
import main.World;

public class Wander extends Routine {
	
	private static Random rGen = new Random();
	private World world;
	private MoveTo moveTo;
	
	public Wander(World world) {
		super();
		this.world = world;
		reset();
	}
	
	@Override public void start() {
		super.start();
		moveTo.start();
	}

	@Override
	public void reset() {
		this.moveTo = new MoveTo(rGen.nextInt((int)world.getSize().x)*World.TILE_SIZE, rGen.nextInt((int)world.getSize().y) * World.TILE_SIZE);		
	}

	@Override
	public void act(Droid droid, World world) {
		if (!moveTo.isRunning()) {
			//do nothing if moveTo is failed or passed
			return;
		}
		moveTo.act(droid, world);
		if (this.moveTo.isSuccess()) {
			succeed();
		} else if (this.moveTo.isFailure()) {
			fail();
		}
	}
	
	@Override
	public String toString() {
		return "Wander";
	}

}
