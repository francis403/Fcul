package main;


/**
 * Solution To the Shipping Supplies Problem
 * @author fc45701
 *
 */
public class ShippingSuppliesComp {
	
	private short r = 1;
	private short c = 10;

	private static final byte WEEK_SKIP = 4;
	
	//MEMORY
	//optimal values of each sub-problems
	private static short[] M;
	
	
	public ShippingSuppliesComp(short r, short c){
		this.r = r;
		this.c = c;
	}
	
	/**
	 * Finds the optimal solution to the Shipping n supplies by air in O(2n) time... right?
	 *  with minimum cost
	 * @param input array of supplies 
	 * @return array of corresponding best air freight companies, A or B
	 */
	public String findSolution(short[] input){ //O(n) se ja tivermos calculado M
		if (input == null)
			throw new IllegalArgumentException("Cannot pass null value");
		if (input.length == 0)
			throw new IllegalArgumentException("Cannot pass empty list");
		
		//Calculates optimal solution for each of the sub-problems
		computeOPT(input); //O(n)
		
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < input.length;i++){
			if (i == input.length - 1)
				res.append( input[i] * r == M[i] ? 'A' : 'B');
			else if((M[i+1] + r * input[i]) == M[i])
				res.append('A');
			else{
				res.append("BBBB");
				i += WEEK_SKIP - 1; //skip the next 3
			}
		}
		return res.toString().substring(0, input.length);
	}
	
	/**
	 * Calculates the optimal cost recursively from a given array and initial index in O(n) time
	 * @param j initial index of the input where we will get the best cost
	 * @param input Array given with all the costs of the shipments
	 * @return Optimal Cost value of input from index j
	 */
	public int computeOPTRec(int j,short[] input){ //O(n)
		if (j >= input.length)
			return 0;
		else if( M[j] != 0)
			return M[j];
		else{
			M[j] = (short) Math.min(computeOPT(input) + r *input[j], computeOPT(input) + c * WEEK_SKIP);
			return M[j];
		}
	}
	
	/**
	 * Calculates the optimal cost from a given array and initial index in O(n) time
	 * @param input Array given with all the costs of the shipments
	 * @return Optimal Cost value of input from index j
	 * @requires input != null && input.length > 0
	 */
	public int computeOPT(short[] input){ //O(n)
		M = new short[input.length];
		M[input.length - 1] = (short)Math.min(r *input[input.length - 1],c * WEEK_SKIP);
		for(int i = input.length - 2; i >= 0; i--){
			M[i] = (short) Math.min(M[i+1] + r *input[i],
					i + WEEK_SKIP < input.length ?
							M[i+WEEK_SKIP] + c * WEEK_SKIP : c * WEEK_SKIP);
		}
		return M[0];
	}
	
}
