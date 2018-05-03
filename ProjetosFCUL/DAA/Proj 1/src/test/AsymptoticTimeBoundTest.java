package test;


import java.util.concurrent.TimeUnit;

import main.InputGenerator;
import main.ShippingSuppliesComp;

/**
 * Numerical Testing for the asymptotic Time Bound of the function
 * @author fc45701
 *
 */
public class AsymptoticTimeBoundTest {
	
	private ShippingSuppliesComp ssc;
	private InputGenerator ig;
	
	private static final int NUM_TESTS = 1000;
	private static final int MAX_N = 1000000;
	
	public AsymptoticTimeBoundTest(){
		short r = 1;
		short c = 10;
		ssc = new ShippingSuppliesComp(r,c);
		ig = new InputGenerator();
	}
	
	private long testTime(short[] input){
		long startTime = System.nanoTime();
		ssc.findSolution(input);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		return totalTime;
	}
	
	public void testTimes(){
		for(int s = 10; s <= MAX_N; s *= 10){
			long average = 0;
			for(int i = 0;i < NUM_TESTS; i++){
				short[] input = ig.nextN(s);
				average += testTime(input);
			}
			average = Math.floorDiv(average, NUM_TESTS);
			System.out.print("Size " + s + " average time = " + average + " nanoseconds");
			System.out.println(" Seconds = " + TimeUnit.NANOSECONDS.toSeconds(average));
		}
	}	
	
	public static void main(String[] args){
		AsymptoticTimeBoundTest astbt = new AsymptoticTimeBoundTest();
		astbt.testTimes();
		
		//System.out.println(totalTime + " nanoseconds ");
		
		//System.out.println( TimeUnit.NANOSECONDS.toSeconds(totalTime) + " seconds"); 
	}
	
}
