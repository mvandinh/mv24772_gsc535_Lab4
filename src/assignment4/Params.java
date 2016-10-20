/* CRITTERS Params.java
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
 * Change these parameter values for testing.  
 * Do not add or remove any parameters in this file.
 */

public class Params {
	/*public final static int world_width = 10;
	public final static int world_height = 10;
	public final static int walk_energy_cost = 2;
	public final static int run_energy_cost = 5;
	public final static int rest_energy_cost = 0;
	public final static int min_reproduce_energy = 20;
	public final static int refresh_algae_count = 10;

	public static final int photosynthesis_energy_amount = 1;
	public static final int start_energy = 100;*/
	
	public  static int world_width = 20;
	public  static int world_height = 20;
	public  static int walk_energy_cost = 2;
	public  static int run_energy_cost = 5;
	public  static int rest_energy_cost = 1;
	public  static int min_reproduce_energy = 20;
	public  static int refresh_algae_count = (int)Math.max(1, world_width*world_height/1000);

	public static  int photosynthesis_energy_amount = 1;
	public static  int start_energy = 100;

}
