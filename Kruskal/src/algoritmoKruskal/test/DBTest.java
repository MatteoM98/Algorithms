package algoritmoKruskal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algoritmoKruskal.DB;

public class DBTest {

	@Test
	public void test1() {
		int[] dim = {3, 2, 4};
		double[] time = {2, 5, 1};
		
		DB test = new DB(dim,time);
		assertTrue(8 == test.timeSaved(10));
		int[] arr = test.memorize(10);
		assertEquals(3,arr.length);
		assertEquals(3,arr[0]);
		assertEquals(2,arr[1]);
		assertEquals(4,arr[2]);
	}

	@Test
	public void test2() {
		int[] dim = {70, 12, 3};
		double[] time = {10, 2, 0.3};
		
		DB test = new DB(dim,time);
		assertTrue(0.5 == test.timeSaved(3));
		int[] arr = test.memorize(3);
		assertEquals(3,arr.length);
		assertEquals(0,arr[0]);
		assertEquals(3,arr[1]);
		assertEquals(0,arr[2]);
	}
	
	@Test
	public void test3() {
		int[] dim = {4, 3, 4};
		double[] time = {5, 3, 1};
		
		DB test = new DB(dim,time);
		assertTrue(8.75 == test.timeSaved(10));
		int[] arr = test.memorize(10);
		assertEquals(3,arr.length);
		assertEquals(4,arr[0]);
		assertEquals(3,arr[1]);
		assertEquals(3,arr[2]);
	}

	@Test (expected = IllegalArgumentException.class) 
	public void test1Exc() {
		int[] dim = {3, 2, 4};
		double[] time = {2, 5, 1};
		
		DB test = new DB(dim,time);
		assertTrue(8 == test.timeSaved(-10));
	}

	@Test (expected = IllegalArgumentException.class)
	public void test1Mem() {
		int[] dim = {3, 2, 4};
		double[] time = {2, 5, 1};
		
		DB test = new DB(dim,time);
		test.memorize(-10);
	}

}
