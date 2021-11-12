package CZ2002_Assignment;

import java.util.Scanner;

 /**
 * Class to check for input 
 */
public class InputChecker {
	private int numOfTries = 4;
	
	 /**
	 * Method that returns a boolean based on the min and max value defined compared against the value
	 * @param value value to be compared to
	 * @param min minimum value to compare with
	 * @param max maximum value to compare with
	 * @return boolean if the value exceeds the min/ max value 
	 */
	public boolean inRange(double value, double min, double max) {
		   return (value>= min) && (value<= max);
		}
	
	/**
	 * Checks against a double and returns a double if it can be assigned to c
	 * @param c value to be tested against
	 * @param sc scanner reference to read input
	 * @return double value to assigned to the variable, return -1 if unsuccessful
	 */
	public double doubleChecker(double c, Scanner sc) {
		try {
			c = sc.nextDouble();
			return ((double)c);
		} catch(Exception e) {
			return -1;
		}
	}
	
	/**
	 * Checks against an int and returns an int if it can be assigned to c
	 * @param c value to be tested against
	 * @param sc scanner reference to read input
	 * @return int value to assigned to the variable, return -1 if unsuccessful
	 */
	public int intChecker(int c, Scanner sc) {
		try {
			c = sc.nextInt();
			return ((int)c);
		} catch(Exception e) {
			return -1;
		}
	}

	/**
	 * Returns number of tries for all checks
	 * @return int number of tries
	 */
	public int getNumOfTries() {
		return numOfTries;
	}
}
