package startup;

import main.ShippingSuppliesComp;

public class Run {

	private static final short r = 1;
	private static final short c = 10;
	private static final short[] input = new short[]{11,9,9,12,12,12,12,9,9,11};
	
	
	public static void main(String[] args){
		
		ShippingSuppliesComp ssc = new ShippingSuppliesComp(r, c);
		
		System.out.println("result = " + ssc.findSolution(input));
		
	}
	
}
