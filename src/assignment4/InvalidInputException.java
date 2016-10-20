/* CRITTERS InvalidInputException.java
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

public class InvalidInputException extends Exception {
	String input;
	
	public InvalidInputException(String input) {
		this.input = input;
	}
	
	public String toString() {
		return "error processing: " + input;
	}

}
