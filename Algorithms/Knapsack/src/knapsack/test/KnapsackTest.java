package knapsack.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import knapsack.Knapsack;

public class KnapsackTest {

	@Test
	public void testMaxValue1() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 3};
		int capacity = 3;
		
		Knapsack test = new Knapsack(value, volume, capacity);
		assertEquals(3, test.getMaxVal());
	}

	@Test
	public void testMaxValue2() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 4};
		int capacity = 3;
		
		Knapsack test = new Knapsack(value, volume, capacity);
		assertEquals(2, test.getMaxVal());
	}

	@Test
	public void testMaxValue3() {
		int[] value = {4 , 4, 4};
		int[] volume = {1, 2, 4};
		int capacity = 4;
		
		Knapsack test = new Knapsack(value, volume, capacity);
		assertEquals(8, test.getMaxVal());
	}
	
	@Test
	public void testSolCreat1() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 4};
		int capacity = 3;
		
		Knapsack test = new Knapsack(value, volume, capacity);
		ArrayList<Integer> sol = test.getOptSol();
		
		assertEquals(2, sol.size());
		assertEquals(0, sol.get(0).intValue());
		assertEquals(1, sol.get(1).intValue());
	}

	@Test
	public void testSolCreat2() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 3};
		int capacity = 3;
		
		Knapsack test = new Knapsack(value, volume, capacity);
		ArrayList<Integer> sol = test.getOptSol();
		
		assertEquals(1, sol.size());
		assertEquals(2, sol.get(0).intValue());
	}
	
	@Test
    public void getMaxVal() {
        Knapsack knapsack = new Knapsack( new int[]{3,11,9},new int[]{5,8,3}, 10);
        assertEquals(12, knapsack.getMaxVal());
        ArrayList<Integer> sol = knapsack.getOptSol();


        assertTrue(sol.contains(0));
        assertFalse(sol.contains(1));
        assertTrue(sol.contains(2));
    }
}
