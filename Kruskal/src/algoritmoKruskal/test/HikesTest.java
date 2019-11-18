package algoritmoKruskal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algoritmoKruskal.Hikes;
import it.uniupo.graphLib.UndirectedGraph;

public class HikesTest {

	@Test (expected = IllegalArgumentException.class)
	public void testHikes() {
		UndirectedGraph rifugi = new UndirectedGraph("5;0 4 9;0 2 16;0 1 13;0 3 19;1 3 14;1 2 7;"
										+ "1 4 22;2 3 12;2 4 15;3 4 26;");
		Hikes hi = new Hikes(rifugi);
		assertEquals(12,hi.minDistanza(3));
		assertEquals(13,hi.minDistanza(2));
		hi.minDistanza(-4);
	}

}
