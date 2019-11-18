package software.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniupo.graphLib.DirectedGraph;
import software.SoftwareSystem;

public class SoftwareSystemTest {

	DirectedGraph grafo;
	SoftwareSystem ss;
	
	@Test
	public void test1Ciclo() {
		grafo = new DirectedGraph("3;0 1;0 2;1 2");
		assertNotNull(grafo);
		ss = new SoftwareSystem(grafo);
		assertNotNull(ss);
		
		assertEquals("Mi aspetto che NON ci sia alcun ciclo",false,ss.hasCycle());
	}
	
	@Test
	public void test2Ciclo() {
		grafo = new DirectedGraph("5;3 2;3 4;4 2");
		assertNotNull(grafo);
		ss = new SoftwareSystem(grafo);
		assertNotNull(ss);
		
		assertEquals("Mi aspetto che NON ci sia alcun ciclo",false,ss.hasCycle());
	}
	
	@Test
	public void test3Ciclo() {
		grafo = new DirectedGraph("3;0 1;1 2;2 0");
		assertNotNull(grafo);
		ss = new SoftwareSystem(grafo);
		assertNotNull(ss);
		
		assertEquals("Mi aspetto che ci sia un ciclo",true,ss.hasCycle());
	}
	
	@Test
	public void test4iclo() {
		grafo = new DirectedGraph("5;0 1;2 1;2 0;2 3;3 4;4 2");
		assertNotNull(grafo);
		ss = new SoftwareSystem(grafo);
		assertNotNull(ss);
		
		assertEquals("Mi aspetto che ci sia un ciclo",true,ss.hasCycle());
	}
	
	@Test
	public void test5iclo() {
		grafo = new DirectedGraph("6;4 1;1 3;4 3;0 1;3 0;0 5;2 5;2 0;3 2");
		assertNotNull(grafo);
		ss = new SoftwareSystem(grafo);
		assertNotNull(ss);
		
		assertEquals("Mi aspetto che ci sia un ciclo",true,ss.hasCycle());
	}
	
	
	
	
	
	

}
