package assignment4;

import assignment4.Critter.TestCritter;

public class MyCritter6 extends TestCritter {
	
	@Override
	public void doTimeStep() {
	}

	@Override
	public boolean fight(String opponent) {
		run(0);
		return false;
	}

	@Override
	public String toString () {
		return "5";
	}
}
