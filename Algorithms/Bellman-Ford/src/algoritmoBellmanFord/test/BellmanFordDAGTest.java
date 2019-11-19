package algoritmoBellmanFord.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algoritmoBellmanFord.BellmanFordDAG;
import it.uniupo.graphLib.DirectedGraph;

public class BellmanFordDAGTest {

	@Test
	public void test1() {
		DirectedGraph test = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");
		BellmanFordDAG bell = new BellmanFordDAG(test,0);
		assertTrue(1 == bell.getDist(2));
	}

	@Test
	public void test2() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		BellmanFordDAG bell = new BellmanFordDAG(test,0);
		assertTrue(0 == bell.getDist(0));
		assertTrue(3 == bell.getDist(1));
		assertTrue(0 == bell.getDist(2));
		assertTrue(3 == bell.getDist(3));
		assertTrue(2 == bell.getDist(4));
	}
	
	@Test
	public void testPath() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		BellmanFordDAG bell = new BellmanFordDAG(test,0);
		ArrayList<Integer> path = bell.getPath(2);
		
		assertFalse(bell.hasCycle());
		assertNotNull(path);
		assertEquals(3, path.size());
		assertEquals(0, path.get(0).intValue());
		assertEquals(1, path.get(1).intValue());
		assertEquals(2, path.get(2).intValue());
	}
	
	public void testHasNegCycle() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 +2;2 0 -3");
		BellmanFordDAG bell = new BellmanFordDAG(test,0);
		assertTrue(bell.hasCycle());
		assertTrue(Double.POSITIVE_INFINITY == bell.getDist(1));
		assertTrue(null == bell.getPath(2));
	}

}
