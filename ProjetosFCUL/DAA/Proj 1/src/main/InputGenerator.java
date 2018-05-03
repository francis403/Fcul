package main;

import java.util.Random;

/**
 * Generates a random input for the Shipping Supplies problem
 * @author fc45701
 *
 */
public class InputGenerator {
	
	private static int MAX_VALUE = 100;
	
	/**
	 * returns the next random value for the input
	 * @return random value for the input bigger than 0 and smaller
	 *  or equals than MAX_VAL
	 */
	public int next(){
		Random r = new Random();
		return r.nextInt(MAX_VALUE) + 1;
	}
	
	/**
	 * Returns input of length n
	 * @param n length of the list returned
	 * @return lst with random values and length n
	 */
	public short[] nextN(int n){
		short[] lst = new short[n];
		for(int i = 0 ; i < n; i++)
			lst[i] = (short) next();
		return lst;
	}
	
}
