package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.InputGenerator;

public class InputGeneratorTest {

	private InputGenerator ig;
	
	@Before
	public void setup(){
		ig = new InputGenerator();
	}
	
	
	@Test
	public void testNextZero() {
		assertEquals(0, ig.nextN(0).length);
	}
	
	@Test
	public void testNextN1() {
		Random r = new Random();
		int n = r.nextInt(1000) + 1;
		assertEquals(n, ig.nextN(n).length);
	}
	@Test
	public void testNextN2() {
		int n = 1000000;
		assertEquals(n, ig.nextN(n).length);
	}
	

}
