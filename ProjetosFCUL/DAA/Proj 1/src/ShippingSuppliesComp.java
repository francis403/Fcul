
/**
 * DOUBT: Se tivermos menos de 
 * 		4 supplies para fazermos shipping, podemos usar o B?
 * @author fc45701
 *
 */
public class ShippingSuppliesComp {
	
	private static final int r = 1;
	private static final int c = 10;
	private static int[] input = new int[]{11,9,9,12,12,12,12,9,9,11};
	private static int[] input2 = new int[]{40,9,9,12,12,12,12,9,50,11};
	private static int[] M = new int[input.length];
	
	public static void main(String[] args){
		int[] test = input; //fazer os testes apropriados
		System.out.println("Cost = " + OPTCostHelper(0,test));
		System.out.print("Solution: ");
		System.out.println(findOptimalSolutionCost(test));
	}
	
	public static char[] findOptimalSolutionCost(int[] input){ //O(n) se ja tivermos calculado M
	
		char[] res = new char[input.length];
		for(int i = 0; i < input.length;i++){
			if (i == input.length - 1)
				res[i] = input[i] * r == M[i] ? 'A' : 'B';
			else if((M[i+1] + r * input[i]) == M[i])
				res[i] = 'A';
			else
				for(int j = 0; j < 4;j++){
					res[i] = 'B';
					if (j != 3)
						i++;
				}
		}
		
		return res;
	}
	
	private static int OPTCostHelper(int j,int[] input){ //O(?)
		if (j >= input.length - 1){
			M[input.length - 1] = Math.min(input[input.length - 1] * r, c * 4);
			return M[input.length - 1];
		}
		else if( M[j] != 0)
			return M[j];
		else{
			M[j] = Math.min(OPTCostHelper(j+1,input) + r *input[j], OPTCostHelper(j+4,input) + c * 4);
			return M[j];
		}
	}
	
}
