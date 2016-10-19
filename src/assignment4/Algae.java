/* CRITTERS Algae.java
 * EE422C Project 4 submission by
 * Minh Van-Dinh
 * mv24772
 * 16475
 * Garrett Custer
 * gsc535
 * 16475
 * Slip days used: <0>
 * Git URL: https://github.com/mvandinh/mv24772_gsc535_Lab4
 * Fall 2016
 */

package assignment4;
/*
 * Do not change this file.
 */
import assignment4.Critter.TestCritter;

public class Algae extends TestCritter {

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
}
