package atletica.test;

import static org.junit.Assert.*;

import org.junit.Test;

import atletica.Atletica;

public class AtleticaTest {

	@Test
	public void test1() {
		int disciplines = 5;
		Atletica atletica = new Atletica(disciplines);
		int[] at1 = {8,4,2,6,3};
		int[] at2 = {3,10,7,7,4};
		assertEquals(2,atletica.choose(at1, at2));
	}
	
	@Test
	public void test2() {
		int disciplines = 5;
		Atletica atletica = new Atletica(disciplines);
		int[] at1 = {8,4,2,6,3};
		int[] at2 = {3,10,7,4,4};
		assertEquals(0,atletica.choose(at1, at2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test3() {
		int disciplines = 5;
		Atletica atletica = new Atletica(disciplines);
		int[] at1 = {8,4,2,6,3,1};
		int[] at2 = {3,10,7,7,4};
		assertEquals(0,atletica.choose(at1, at2));
	}	
	

}
