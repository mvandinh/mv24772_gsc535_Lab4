/* CRITTERS Critter.java
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	private int movement_flag = 0;	// 0 for rest, 1 for walk/run, 2 for fight
	
	protected final void walk(int direction) {
		energy -= Params.walk_energy_cost;
		int x_temp = 0;
		int y_temp = 0;
		if (movement_flag != 1) {	// critter has already moved during time step
			if (direction == 0) {
				x_temp = x_coord + 1;
			}
			else if(direction == 1) {
				x_temp = x_coord + 1;
				y_temp = y_coord + 1;
			}
			else if(direction == 2) {
				y_temp = y_coord + 1;
			}
			else if(direction == 3) {
				x_temp = x_coord - 1;
				y_temp = y_coord + 1;
			}
			else if(direction == 4) {
				x_temp = x_coord - 1;
			}
			else if(direction == 5) {
				x_temp = x_coord - 1;
				y_temp = y_coord - 1;
			}
			else if(direction == 6) {
				y_temp = y_coord - 1;
			}
			else {	// direction == 7
				x_temp = x_coord + 1;
				y_temp = y_coord - 1;
			}
			x_temp %= Params.world_width;
			y_temp %= Params.world_height;
			if (movement_flag == 0) {
				x_coord = x_temp;
				y_coord = y_temp;
				movement_flag = 1;
			}
			else {	// movement flag == 2
				for (Critter crit: population) {
					if (!this.equals(crit)) {
						if ((x_temp == crit.x_coord) && (y_temp == crit.y_coord)) {
							return;
						}
					}
				}
				x_coord = x_temp;
				x_coord = y_temp;
			}
		}
	}
	
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;
		int x_temp = 0;
		int y_temp = 0;
		if (movement_flag != 1) {	// critter has already moved during time step
			if (direction == 0) {
				x_temp = x_coord + 2;
			}
			else if(direction == 1) {
				x_temp = x_coord + 2;
				y_temp = y_coord + 2;
			}
			else if(direction == 2) {
				y_temp = y_coord + 2;
			}
			else if(direction == 3) {
				x_temp = x_coord - 2;
				y_temp = y_coord + 2;
			}
			else if(direction == 4) {
				x_temp = x_coord - 2;
			}
			else if(direction == 5) {
				x_temp = x_coord - 2;
				y_temp = y_coord - 2;
			}
			else if(direction == 6) {
				y_temp = y_coord - 2;
			}
			else {	// direction == 7
				x_temp = x_coord + 2;
				y_temp = y_coord - 2;
			}
			x_temp %= Params.world_width;
			y_temp %= Params.world_height;
			if (movement_flag == 0) {
				x_coord = x_temp;
				y_coord = y_temp;
				movement_flag = 1;
			}
			else {	// movement flag == 2
				for (Critter crit: population) {
					if (!this.equals(crit)) {
						if ((x_temp == crit.x_coord) && (y_temp == crit.y_coord)) {
							return;
						}
					}
				}
				x_coord = x_temp;
				x_coord = y_temp;
			}
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) {	// make sure parent has enough energy
			return;
		}
		offspring.energy = energy / 2;	// assign half of energy to child (rounding down)
		energy -= offspring.energy;		// assign half of energy to parent (rounding up)
		if (direction == 0) {
			offspring.x_coord = x_coord + 1;
		}
		else if(direction == 1) {
			offspring.x_coord = x_coord + 1;
			offspring.y_coord = y_coord + 1;
		}
		else if(direction == 2) {
			offspring.y_coord = y_coord + 1;
		}
		else if(direction == 3) {
			offspring.x_coord = x_coord - 1;
			offspring.y_coord = y_coord + 1;
		}
		else if(direction == 4) {
			offspring.x_coord = x_coord - 1;
		}
		else if(direction == 5) {
			offspring.x_coord = x_coord - 1;
			offspring.y_coord = y_coord - 1;
		}
		else if(direction == 6) {
			offspring.y_coord = y_coord - 1;
		}
		else {	// direction == 7
			offspring.x_coord = x_coord + 1;
			offspring.y_coord = y_coord - 1;
		}
		offspring.x_coord %= Params.world_width;
		offspring.y_coord %= Params.world_height;
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try {
			Class<?> c = Class.forName(myPackage + "." + critter_class_name);
			Constructor<?> newConstructor = c.getConstructor();
			Object obj = newConstructor.newInstance();
			Critter newCritter = (Critter)obj;
			population.add(newCritter);
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		return;
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(Critter c: population){
			try {
				if(Class.forName(myPackage + "." + critter_class_name).isInstance(c)){
					result.add(c);
				}
			} catch (ClassNotFoundException e) {
				throw new InvalidCritterException(critter_class_name);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
	}
	
	public static void worldTimeStep() {
		for (Critter crit: population) {
			crit.doTimeStep();
			if (crit.energy <= 0) {	// kill dead critters
				population.remove(crit);
			}
		}
		boolean fightA = false;
		boolean fightB = false;
		int powerA = 0;
		int powerB = 0;
		for (Critter critA: population) {	// encounters between critter A and critter B
			for (Critter critB: population) {
				if (!critA.equals(critB)) {
					if ((critA.x_coord == critB.x_coord) && (critA.y_coord == critB.y_coord)) {
						critA.movement_flag = 2;
						fightA = critA.fight(critB.toString());
						if (critA.energy <= 0) {	// critter A died while running away
							population.remove(critA);
							break;
						}
						critB.movement_flag = 2;
						fightB = critB.fight(critA.toString());
						if (critB.energy <= 0) {	// critter B died while running away
							population.remove(critB);
							break;
						}
						if ((critA.x_coord == critB.x_coord) && (critA.y_coord == critB.y_coord)) {
							if (fightA == false) {	// critter A elected not to fight
								powerA = 0;
							}
							else {	// fightA == true
								powerA = getRandomInt(critA.energy);
							}
							if (fightB == false ) {	// critter B elected not to fight
								powerB = 0;
							}
							else { // fightB == true
								powerB = getRandomInt(critB.energy);
							}
							if (powerA >= powerB) {	// critter A kills critter B
								critA.energy += critB.energy / 2;
								population.remove(critB);
							}
							else {	// critter B kills critter A
								critB.energy += critA.energy / 2;
								population.remove(critA);
							}
						}
						else {
							break;
						}
					}
				}
			}
		}
		for (Critter crit: population) {	// reset movement flag for critters
			crit.movement_flag = 0;
		}
		for (Critter crit: babies) {	// add babies to the population
			population.add(crit);
			babies.remove(crit);
		}
	}
	
	public static void displayWorld() {
		String[][] grid = new String[Params.world_height + 2][Params.world_width + 2];
		int i;
		int j;
		for(i = Params.world_height + 1; i >=0; i--){
			for(j = Params.world_width + 1; j >= 0; j--){
				if((i == Params.world_height + 1) || (i == 0)){
					if((j == Params.world_width + 1) || (j == 0)){ grid[i][j] = "+";}
					else { grid[i][j] = "-";}
				}
				else{
					if((j == Params.world_width + 1) || (j == 0)){ grid[i][j] = "|";}
					else {grid[i][j] = " ";}
				}
			}
		}
		for (Critter c: population){
			grid[c.x_coord + 1][c.y_coord + 1] = c.toString();
		}
		for(i = 0; i < Params.world_height + 1; i++){
			for(j = 0; j < Params.world_width + 1; j++){
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
}
