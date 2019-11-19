package algoritmoBellmanFord.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoritmoBellmanFord.BellmanFord;
import it.uniupo.graphLib.DirectedGraph;

public class BellmanFordTest {

	@Test
	public void test1() {
		DirectedGraph test = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(1 == bell.getDist(2));
	}

	@Test
	public void test2() {
		DirectedGraph test = new DirectedGraph("4;1 0 4;0 3 -5;3 2 1;1 2 -3");
		BellmanFord bell = new BellmanFord(test,1);
		assertEquals(-3.0,bell.getDist(2),0.00001);
		assertEquals(4.0,bell.getDist(0),0.00001);
	}
	
	@Test
	public void test3() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(0 == bell.getDist(0));
		assertTrue(3 == bell.getDist(1));
		assertTrue(0 == bell.getDist(2));
		assertTrue(3 == bell.getDist(3));
		assertTrue(2 == bell.getDist(4));
		assertFalse(bell.hasNegCycle());
	}
	
	@Test
	public void test2NegCy() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(Double.NEGATIVE_INFINITY == bell.getDist(0));		
	}
	
	@Test
	public void testHasNegCycle() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(bell.hasNegCycle());		
	}
	
	@Test
	public void testPath() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		BellmanFord bell = new BellmanFord(test,0);
		ArrayList<Integer> path = bell.getPath(2);
		
		assertEquals(3, path.size());
		assertEquals(0, path.get(0).intValue());
		assertEquals(1, path.get(1).intValue());
		assertEquals(2, path.get(2).intValue());
	}

}
