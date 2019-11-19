package algoritmoKruskal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algoritmoKruskal.FracKnapsack;

public class FracKnapsackTest {

	@Test
	public void testMaxVal() {
		double capacity = 6;
		double[] volume = {3, 4, 3};
		double[] value = {5, 1, 6};
		
		FracKnapsack knap = new FracKnapsack(capacity, volume, value);
		assertTrue(11.0 == knap.maxVal());
	}

	@Test
	public void testDose() {
		double capacity = 6;
		double[] volume = {3, 4, 3};
		double[] value = {5, 1, 6};
		
		FracKnapsack knap = new FracKnapsack(capacity, volume, value);
		assertTrue(1.0 == knap.dose(0));
		assertTrue(1.0 == knap.dose(2));
		assertTrue(0 == knap.dose(1));
	}

}
