package algoritmoFloydWarshall.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algoritmoFloydWarshall.FloydWarshall;
import it.uniupo.graphLib.DirectedGraph;

public class FloydWarshallTest {

	@Test
	public void test() {
		DirectedGraph g = new DirectedGraph("4;0 1 3;0 2 4;2 1 5;1 3 2;3 2 3");
		FloydWarshall fw = new FloydWarshall(g);
		assertEquals(5.0,fw.getDist(1,2),0.00001);
		assertEquals(false,fw.hasNegCycle());
		for(Double[] i : fw.getDistance()) {
			for(double j : i) {
				System.out.print(j+"   ");
			}
			System.out.print("\n");
		}
	}
	
	@Test
	public void test1() {
		DirectedGraph test = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");
		FloydWarshall flo = new FloydWarshall(test);
		assertTrue(1 == flo.getDist(0, 2));
		
	}
	
	@Test
	public void test2() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		FloydWarshall flo = new FloydWarshall(test);
		assertTrue(0 == flo.getDist(0, 0));
		assertTrue(3 == flo.getDist(0, 1));
		assertTrue(0 == flo.getDist(0, 2));
		assertTrue(3 == flo.getDist(0, 3));
		assertTrue(2 == flo.getDist(0, 4));
		assertFalse(flo.hasNegCycle());
	}
	
	@Test
	public void testNegCycle() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
		FloydWarshall bell = new FloydWarshall(test);
		assertTrue(bell.hasNegCycle());				
	}
	
	 @Test
	    public void getMinimumPath() {
	        FloydWarshall floydWarshall = new FloydWarshall(new DirectedGraph("3;0 1 3;1 2 -2;0 2 2"));
	        assertEquals("Mi aspetto 1",true,floydWarshall.getMinimumPath(0,2).contains(1));
	        DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
			FloydWarshall bell = new FloydWarshall(test);
			assertNull(bell.getMinimumPath(0, 2));	
	    }
}
