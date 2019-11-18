package kosaraju.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphUtils;
import kosaraju.Kosaraju;

public class KosarajuTest {

	DirectedGraph grafo;
	Kosaraju k;
	
	@Test
	public void test1UnicoNodo() {
		grafo = new DirectedGraph("1");
		assertNotNull(grafo);
		k = new Kosaraju(grafo);
		assertNotNull(k);
		
		assertEquals("Mi aspetto che trovi un solo nodo",1,k.getSCC().length);
		assertEquals("Mi aspetto che questo nodo faccia parte della SCC 0",0,k.getSCC()[0]);
	}
	
	@Test
	public void test2DAG() {
		grafo = new DirectedGraph("6;0 1;1 2;2 3;3 4;4 2;3 5;5 2");
		assertNotNull(grafo);
		k = new Kosaraju(grafo);
		assertNotNull(k);
		
		assertEquals("Mi aspetto che trovi un solo nodo",6,k.getSCC().length);
		assertEquals("Mi aspetto che il nodo 0 faccia parte della SCC 0",0,k.getSCC()[0]);
		assertEquals("Mi aspetto che il nodo 1 faccia parte della SCC 1",1,k.getSCC()[1]);
		assertEquals("Mi aspetto che il nodo 2 faccia parte della SCC 2",2,k.getSCC()[2]);
		assertEquals("Mi aspetto che il nodo 3 faccia parte della SCC 2",2,k.getSCC()[3]);
		assertEquals("Mi aspetto che il nodo 4 faccia parte della SCC 2",2,k.getSCC()[4]);
		assertEquals("Mi aspetto che il nodo 5 faccia parte della SCC 2",2,k.getSCC()[5]);
		
	}
	
	@Test
	public void test3Ciclo() {
		grafo = new DirectedGraph("4; 0 1; 1 2; 2 3; 3 0");
		assertNotNull(grafo);
		k = new Kosaraju(grafo);
		assertNotNull(k);
		
		assertEquals("Mi aspetto che trovi un solo nodo",4,k.getSCC().length);
		assertEquals("Mi aspetto che il nodo 0 faccia parte della SCC 0",0,k.getSCC()[0]);
		assertEquals("Mi aspetto che il nodo 1 faccia parte della SCC 0",0,k.getSCC()[1]);
		assertEquals("Mi aspetto che il nodo 2 faccia parte della SCC 0",0,k.getSCC()[2]);
		assertEquals("Mi aspetto che il nodo 3 faccia parte della SCC 0",0,k.getSCC()[3]);
	}
	
	@Test //verifico il lemma per cui le SCC di un grafo sono identiche a quelle del suo trasposto
	public void test4Trasposto() {
		grafo = new DirectedGraph("4; 0 1; 1 2; 2 3; 3 0");
		assertNotNull(grafo);
		k = new Kosaraju(grafo);
		assertNotNull(k);
		
		assertEquals("Mi aspetto che trovi un solo nodo",4,k.getSCC().length);
		assertEquals("Mi aspetto che il nodo 0 faccia parte della SCC 0",0,k.getSCC()[0]);
		assertEquals("Mi aspetto che il nodo 1 faccia parte della SCC 0",0,k.getSCC()[1]);
		assertEquals("Mi aspetto che il nodo 2 faccia parte della SCC 0",0,k.getSCC()[2]);
		assertEquals("Mi aspetto che il nodo 3 faccia parte della SCC 0",0,k.getSCC()[3]);
		
		DirectedGraph grafo2 = GraphUtils.reverseGraph(grafo);
		assertNotNull(grafo2);
		k = new Kosaraju(grafo2);
		assertNotNull(k);
		
		assertEquals("Mi aspetto che trovi un solo nodo",4,k.getSCC().length);
		assertEquals("Mi aspetto che il nodo 0 faccia parte della SCC 0",0,k.getSCC()[0]);
		assertEquals("Mi aspetto che il nodo 1 faccia parte della SCC 0",0,k.getSCC()[1]);
		assertEquals("Mi aspetto che il nodo 2 faccia parte della SCC 0",0,k.getSCC()[2]);
		assertEquals("Mi aspetto che il nodo 3 faccia parte della SCC 0",0,k.getSCC()[3]);
		
	}
	
	@Test
	public void test1PostVisit() {
		grafo = new DirectedGraph("5; 0 2; 2 0; 1 2; 4 0; 1 4; 4 3; 3 1");
		assertNotNull(grafo);
		k = new Kosaraju(grafo);
		assertNotNull(k);
		assertTrue(k.postVisitList().indexOf(0)<k.postVisitList().indexOf(1));
	}
	
	@Test
	public void test2PostVisit() {
		grafo = new DirectedGraph("5; 1 2; 2 1; 0 2; 4 1; 0 4; 4 3; 3 0");
		assertNotNull(grafo);
		k = new Kosaraju(grafo);
		assertNotNull(k);
		assertTrue(k.postVisitList().indexOf(0)>k.postVisitList().indexOf(1));
	}
	
	
	
	
	


}
