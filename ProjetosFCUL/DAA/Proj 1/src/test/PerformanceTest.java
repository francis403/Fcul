package test;

import main.InputGenerator;
import main.ShippingSuppliesComp;

public class PerformanceTest {
	
	private static final int NUM_TESTS = 1000;
	private static final int MAX_N = 1000000;
	
	
	private ShippingSuppliesComp ssc;
	private InputGenerator ig;
	
	private static final long MEGABYTE = 1024L * 1024L;
	
	public PerformanceTest() {
		short  r = 1;
		short c = 10;
		ssc = new ShippingSuppliesComp(r,c);
		ig = new InputGenerator();
	}
	
	public static long bytesToMegaBytes(long bytes){
		return bytes/MEGABYTE;
	}
	
	public long spaceOccupied(short[] input){
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		ssc.findSolution(input);
		return runtime.totalMemory() - runtime.freeMemory();
	}
	
	public void testSpace(){
		for(int s = 10; s <= MAX_N; s *= 10){
			long average = 0;
			for(int i = 0;i < NUM_TESTS; i++){
				short[] input = ig.nextN(s);
				average += spaceOccupied(input);
			}
			average = average/NUM_TESTS;
			System.out.print("Size " + s + " average time = " + average + " bytes ");
			System.out.println(bytesToMegaBytes(average)+ " MEGABYTES");
		}
	}	
	
	public static void main(String[] args){
		PerformanceTest pf = new PerformanceTest();
		//calculate the used memory
		pf.testSpace();
	}
	
}
