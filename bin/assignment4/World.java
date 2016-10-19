package assignment4;

public class World {
	private String[][] worldGrid;
	public World(){
		worldGrid = new String[40][40];
	}
	public World(int rows, int columns){
		worldGrid = new String[rows][columns];
	}
}
