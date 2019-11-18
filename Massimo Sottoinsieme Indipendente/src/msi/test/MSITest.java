package msi.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import msi.MSI;

public class MSITest {

	@Test
	public void testGetMaxVal() {
		int[] arr = {1, 5, 12, 6, 2, 5};
		MSI test = new MSI(arr);
		
		assertEquals(18, test.getMaxVal());
	}
	
	@Test
	public void testGetMaxVal2() {
		int[] arr = {8,4,2,6,3};
		MSI test = new MSI(arr);
		
		assertEquals(14, test.getMaxVal());
	}
	
	@Test
	public void testGetOptSol() {
		int[] arr = {1, 5, 9, 6, 2, 5};
		MSI test = new MSI(arr);
		
		assertEquals(16, test.getMaxVal());
		ArrayList<Integer> solution = test.getOptSol();
		
		assertEquals(3,solution.size());
		assertEquals(5, solution.get(0).intValue());
		assertEquals(3, solution.get(1).intValue());
		assertEquals(1, solution.get(2).intValue());

	}

}
