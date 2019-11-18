package rebooking.test;

import static org.junit.Assert.*;

import org.junit.Test;

import rebooking.Rebooking;

public class RebookingTest {

	@Test
	public void test() {
		int[] dimGruppo = {8,2,10};
		int[] costoGruppo = {8,3,10};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(11,test.maxSaving());
	}
	
	@Test
	public void test2() {
		int[] dimGruppo = {8,2,10};
		int[] costoGruppo = {8,3,10};
		Rebooking test = new Rebooking(-3,dimGruppo,costoGruppo);
		assertEquals(-1,test.maxSaving());
	
	}
	
	
	
	@Test
	public void test3() {
		int[] dimGruppo = {8,2,10,1};
		int[] costoGruppo = {8,3,10};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(-1,test.maxSaving());
	}
	
	
	 
	@Test
	public void test4() {
		int[] dimGruppo = {8,1,10};
		int[] costoGruppo = {8,3,10};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(11,test.maxSaving());
	}
	
	
	@Test
	public void test5() {
		int[] dimGruppo = {8,2,10};
		int[] costoGruppo = {8,3,10,3};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(-1,test.maxSaving());
	}
	
	@Test
	public void test6() {
		int[] dimGruppo = {8,2,10};
		int[] costoGruppo = {8,2,10};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(10,test.maxSaving());
	}
	
	@Test
	public void test7() {
		int[] dimGruppo = {8,2,10};
		int[] costoGruppo = {8,1,10};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(10,test.maxSaving());
	}

	
	@Test
	public void test8() {
		int[] dimGruppo = {5,8,3};
		int[] costoGruppo = {3,11,9};
		Rebooking test = new Rebooking(10,dimGruppo,costoGruppo);
		assertEquals(12,test.maxSaving());
		
		int[] dimGruppo2 = {5,8,3};
		int[] costoGruppo2 = {3,11,9};
		Rebooking test2 = new Rebooking(-4,dimGruppo2,costoGruppo2);
		assertEquals(-1,test2.maxSaving());
		
		int[] dimGruppo3 = {5,8};
		int[] costoGruppo3 = {3,11,9};
		Rebooking test3 = new Rebooking(10,dimGruppo3,costoGruppo3);
		assertEquals(-1,test3.maxSaving());
		
		int[] dimGruppo4 = {5,8,3};
		int[] costoGruppo4 = {3,11,9};
		Rebooking test4 = new Rebooking(0,dimGruppo4,costoGruppo4);
		assertEquals(0,test4.maxSaving());
		
		int[] dimGruppo5 = {5,8,3};
		int[] costoGruppo5 = {3,11,9};
		Rebooking test5 = new Rebooking(1,dimGruppo5,costoGruppo5);
		assertEquals(0,test5.maxSaving());
		
		int[] dimGruppo6 = {5,8,3};
		int[] costoGruppo6 = {3,11,9};
		Rebooking test6 = new Rebooking(20,dimGruppo6,costoGruppo6);
		assertEquals(23,test6.maxSaving());
		
	}

}
