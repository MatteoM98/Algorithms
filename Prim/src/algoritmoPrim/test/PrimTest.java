package algoritmoPrim.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algoritmoPrim.Prim;
import it.uniupo.graphLib.UndirectedGraph;

public class PrimTest {

	@Test
	public void test() {
		UndirectedGraph g = new UndirectedGraph("6;0 2 1;0 1 3;1 3 2;3 2 4;2 4 3;4 5 6;5 1 1");
		Prim p = new Prim(g);
		UndirectedGraph albero = p.getMST();
		System.out.println("Grafo di partenza\n"+g);
		System.out.println("MST\n"+albero);
		assertEquals(5,albero.getEdgeNum());
		assertEquals(true,!albero.hasEdge(4,5) && !albero.hasEdge(2,3));
		assertEquals("Il costo dell' MST dovrebbe essere 10",10,p.getMSTCost());
	}
	
	@Test
	public void test2() {
		UndirectedGraph g = new UndirectedGraph("7;0 5 10;5 4 25;4 3 22;3 2 12;4 6 24;6 3 18;1 2 16;1 6 14;0 1 28");
		Prim p = new Prim(g);
		UndirectedGraph albero = p.getMST();
		System.out.println("Grafo di partenza\n"+g);
		System.out.println("MST\n"+albero);
		assertEquals(g.getOrder()-1,albero.getEdgeNum());
		assertEquals("Il costo dell' MST dovrebbe essere 99",99,p.getMSTCost());
	}

}
