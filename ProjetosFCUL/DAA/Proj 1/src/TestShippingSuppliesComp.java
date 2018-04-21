import static org.junit.Assert.*;

import org.junit.Test;


public class TestShippingSuppliesComp {
	
	
	@Test
	public void shipping1() {
		String esp = "AAABBBBAAA";
		int[] input = new int[]{11,9,9,12,12,12,12,9,9,11};
		assertEquals("Standard test",esp,
				ShippingSuppliesComp.findOptimalSolutionCost(input));
		assertEquals("Standard test cost ",98,
				ShippingSuppliesComp.OPTCost(0, input));
	}
	
	@Test
	public void shipping2() {
		String esp = "BBBB";
		int[] input = new int[]{1,2,44,3};
		assertEquals("Standard test",esp,
				ShippingSuppliesComp.findOptimalSolutionCost(input));
		assertEquals("Standard test cost ",40,
				ShippingSuppliesComp.OPTCost(0, input));
	}
	
	@Test
	public void shippingEmptyList() {
		String esp = "";
		int[] input = new int[]{};
		assertEquals("Standard test",esp,
				ShippingSuppliesComp.findOptimalSolutionCost(input));
		assertEquals("Standard test cost ",0,
				ShippingSuppliesComp.OPTCost(0, input));
	}
	
	@Test
	public void shippingSingleCost() {
		int[] input = new int[]{44};
		String res = ShippingSuppliesComp.findOptimalSolutionCost(input);
		assertEquals("Best res of single element over 40 ","B",res);
		assertEquals("Val of a list with a single value",40,
				ShippingSuppliesComp.OPTCost(0, input));
	}

	@Test
	public void shippingDoubleCostLowerThan40() {
		String esp = "AA";
		int[] input = new int[]{18,21};
		assertEquals("Standard test",esp,
				ShippingSuppliesComp.findOptimalSolutionCost(input));
		assertEquals("Val of a list with mult values",39,
				ShippingSuppliesComp.OPTCost(0, input));
	}
	
	@Test
	public void shippingDoubleCostHigherThan40() {
		String esp = "BB";
		int[] input = new int[]{22,21};
		assertEquals("Standard test",esp,
				ShippingSuppliesComp.findOptimalSolutionCost(input));
		assertEquals("Val of a list with mult values",40,
				ShippingSuppliesComp.OPTCost(0, input));
	}
	
}
