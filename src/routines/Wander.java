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
		restart();
	}
	
	@Override public void start() {
		super.start();
		moveTo.start();
	}

	@Override
	public void restart() {
		this.moveTo = new MoveTo(rGen.nextInt((int)world.getSize().x)*World.TILE_SIZE, rGen.nextInt((int)world.getSize().y) * World.TILE_SIZE);		
	}

	@Override
	public void act(Droid droid, World world) {
		if (!moveTo.isRunning()) {
			return;
		}
		moveTo.act(droid, world);
		if (this.moveTo.isSuccess()) {
			succeed();
		} else if (this.moveTo.isFailure()) {
			fail();
		}
	}

}
