package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.ShippingSuppliesComp;


/**
 * Tests the correctness of the ShippingSuppliesComp program
 * @author fc45701
 *
 */
public class TestShippingSuppliesComp {
	
	private ShippingSuppliesComp ssc;
	private short r = 1;
	private short c = 10;
	
	@Before
	public void setup(){ ssc = new ShippingSuppliesComp(r,c);}
	
	@Test
	public void shippingNullTest() {
		try{
			ssc.findSolution(null);
		}catch(IllegalArgumentException e){
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void shippingEmptyList() {
		short[] input = new short[]{};
		try{
			ssc.findSolution(input);
		}catch(IllegalArgumentException e){
			assertTrue(true);
			return;
		}
		fail();
	}
	
	@Test
	public void shipping1() {
		String esp = "AAABBBBAAA";
		short[] input = new short[]{11,9,9,12,12,12,12,9,9,11};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Standard test cost ",98,
				ssc.computeOPT(input));
	}
	
	@Test
	public void shipping2() {
		String esp = "BBBB";
		short[] input = new short[]{1,2,44,3};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Standard test cost ",40,
				ssc.computeOPT(input));
	}
	
	@Test
	public void shipping3(){
		String esp = "AAABBBB";
		short[] input = new short[]{11,9,9,12,12,12,12};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Standard test cost ",69,
				ssc.computeOPT(input));
	}
	
	@Test
	public void shipping4(){
		String esp = "AABBBBA";
		short[] input = new short[]{11,9,9,12,12,12,3};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Standard test cost ",63,
				ssc.computeOPT(input));
	}
	
	@Test
	public void shippingSingleCost() {
		short[] input = new short[]{44};
		String res = ssc.findSolution(input);
		assertEquals("Best res of single element over 40 ","B",res);
		assertEquals("Val of a list with a single value",40,
				ssc.computeOPT(input));
	}

	@Test
	public void shippingDoubleCostLowerThan40() {
		String esp = "AA";
		short[] input = new short[]{18,21};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Val of a list with mult values",39,
				ssc.computeOPT(input));
	}
	
	@Test
	public void shippingDoubleCostHigherThan40() {
		String esp = "BB";
		short[] input = new short[]{22,21};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Val of a list with mult values",40,
				ssc.computeOPT(input));
	}
	@Test
	public void shippingDoubleCostTrippleThan40() {
		String esp = "BBB";
		short[] input = new short[]{1,1,39};
		assertEquals("Standard test",esp,
				ssc.findSolution(input));
		assertEquals("Val of a list with mult values",40,
				ssc.computeOPT(input));
	}
	
}
