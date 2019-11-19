package lcs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import lcs.LCS;

public class LCSTest {

	@Test
	public void test() {
		String s1 = "stone";
		String s2 = "longest";
		
		LCS l = new LCS(s1,s2);
		assertNotNull(l);
		int[][] matrix = l.getLCS();
		assertNotNull(matrix);
		assertEquals("Mi aspetto sia 3",3,l.getLCSlenght());
		
	}
	
	@Test
	public void test2() {
		String s1 = "bd";
		String s2 = "abcd";
		
		LCS l = new LCS(s1,s2);
		assertNotNull(l);
		int[][] matrix = l.getLCS();
		assertNotNull(matrix);
		assertEquals("Mi aspetto sia 2",2,l.getLCSlenght());
		
	}
	
	@Test
	public void test3() {
		String s1 = "GXTXAYB";
		String s2 = "AGGTAB";
		
		LCS l = new LCS(s1,s2);
		assertNotNull(l);
		int[][] matrix = l.getLCS();
		assertNotNull(matrix);
		assertEquals("Mi aspetto sia 4",4,l.getLCSlenght());
		
	}
	
	

}
