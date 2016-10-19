/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Minh Van-Dinh
 * mv24772
 * 16475
 * Garrett Custer
 * gsc535
 * 16475
 * Slip days used: <0>
 * Fall 2016
 */

package assignment4; // cannot be in default package
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        String[] splitInput = null;
        do {
        	Arrays.fill(splitInput, null);
        	System.out.print("critters>");
            splitInput = kb.nextLine().split("\\s+");
    		if (splitInput[0].equals("show")) {
    			Critter.displayWorld();
    		}
    		else if (splitInput[0].equals("step")) {
    			int num_steps = 1;
    			if (!splitInput[1].equals(null)) {
    				num_steps = Integer.parseInt(splitInput[1]);
    			}
    			for (int i = 0; i < num_steps; i++) {
    				
    			}
    		}
    		else if (splitInput[0].equals("seed")) {
    			if (!splitInput[1].equals(null)) {
    				Critter.setSeed(Integer.parseInt(splitInput[1]));
    			}
    			else {	// INVALID INPUT
    				
    			}
    		}
    		else if (splitInput[0].equals("make")) {
    			int num_make = 1;
    			if (!splitInput[1].equals(null)) {
    				if (!splitInput[2].equals(null)) {
    					num_make = Integer.parseInt(splitInput[2]);
    				}
    				for (int i = 0; i < num_make; i++) {
    					// Critter.makeCritter(critter_class_name);
    				}
    			}
    			else {	// INVALID INPUT
    				
    			}
    		}
    		else if (splitInput[0].equals("stats")) {
    			if (!splitInput[1].equals(null)) {
    				// Critter.getInstances(critter_class_name);
    				// critter_class_name.runStats();
    			}
    			else {	// INVALID INPUT
    				
    			}
    		}
    		else {	// INVALID INPUT
    			
    		}
        } while (!splitInput[0].equals("quit"));
        System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();

    }
}
