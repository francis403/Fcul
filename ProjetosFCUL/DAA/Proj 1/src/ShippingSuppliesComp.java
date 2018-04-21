
/**
 * DOUBT: Se tivermos menos de 
 * 		4 supplies para fazermos shipping, podemos usar o B?
 * @author fc45701
 *
 */
public class ShippingSuppliesComp {
	
	private static final int r = 1;
	private static final int c = 10;

	//MEMORY
	private static int[] M;
	
	/**
	 * Finds the optimal solution to the Shipping n supplies by air
	 *  with minimum cost
	 * @param input array of supplies 
	 * @return array of corresponding best air freight companies, A or B
	 */
	public static String findOptimalSolutionCost(int[] input){ //O(n) se ja tivermos calculado M
		
		M = new int[input.length];
		
		//Calculates optimal cost and puts it in the memory
		OPTCost(0,input);
		
		//Passes threw all of the indexes in the memory and checks
		// if the corresponding index (input[index]) corresponds to
		//the optimal value of using A or B
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < input.length;i++){
			if (i == input.length - 1)
				res.append( input[i] * r == M[i] ? 'A' : 'B');
			else if((M[i+1] + r * input[i]) == M[i])
				res.append('A');
			else
				for(int j = 0; j < 4 && i < input.length;j++){
					res.append('B');
					if (j != 3)
						i++;
				}
		}
		
		return res.toString();
	}
	
	/**
	 * Calculates the optimal cost from a given array and initial index
	 * @param j initial index of the input where we will get the best cost
	 * @param input Array given with all the costs of the shipments
	 * @return Optimal Cost value of input from index j
	 */
	public static int OPTCost(int j,int[] input){ //O(?)
		//prob here
		if (j > input.length -1)
			return 0;
		else if( M[j] != 0)
			return M[j];
		else{
			M[j] = Math.min(OPTCost(j+1,input) + r *input[j], OPTCost(j+4,input) + c * 4);
			return M[j];
		}
	}
	
}
