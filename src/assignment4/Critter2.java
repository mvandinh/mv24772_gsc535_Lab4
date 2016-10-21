/* CRITTERS Critter2.java
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
 * Example critter
 */
public class Critter2 extends Critter {
	
	@Override
	public String toString() { return "2"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter2() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) {
		if (getEnergy()<100){
			int rand = Critter.getRandomInt(80);
			if((rand + getEnergy()) < 140){
				return true;
			}
		}
		return false;
	}

	@Override
	public void doTimeStep() {
		/* chance to walk or run depending on HP */
		int rand = Critter.getRandomInt(60);
		if((rand + getEnergy()) < 100){
			walk(dir);
		}
		else{
			run(dir);
		}
		
		if (getEnergy() > 150) {
			Critter2 child = new Critter2();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
		/* pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}

	public static void runStats(java.util.List<Critter> critter2) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter2) {
			Critter2 c = (Critter2) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + critter2.size() + " total Critter2    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter2.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter2.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter2.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter2.size()) + "% left   ");
		System.out.println();
	}
}