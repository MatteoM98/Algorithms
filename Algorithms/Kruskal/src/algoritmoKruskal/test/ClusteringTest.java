package algoritmoKruskal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algoritmoKruskal.Clustering;
import it.uniupo.graphLib.UndirectedGraph;

public class ClusteringTest {

	@Test
	public void testSpaziamento() {
		UndirectedGraph g = new UndirectedGraph("4;0 3 7;0 1 2;1 2 2;2 3 1");
		Clustering cl = new Clustering(g, 2);
		int spaz = cl.spaziamento();
		System.out.println(spaz);
		assertEquals(2,spaz);
		
		g = new UndirectedGraph("4;0 3 7;0 1 3;1 2 2;2 3 1");
		cl = new Clustering(g, 2);
		spaz = cl.spaziamento();
		System.out.println(spaz);
		assertEquals(3,spaz);
		
	}

	@Test 
	public void testSameCluster() {
		UndirectedGraph g = new UndirectedGraph("4;0 3 7;0 1 2;1 2 2;2 3 1");
		Clustering cl = new Clustering(g, 2);
		assertTrue(cl.sameCluster(3, 1));
		assertFalse(cl.sameCluster(3, 0));		
	}

}
