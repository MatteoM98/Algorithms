package visita.testBFS;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import visita.bfs.BFS;

public class TestBFS {
	
	private BFS a;
	private GraphInterface grafo = new DirectedGraph ("3;0 1;1 2;2 0");
	
	 GraphInterface testGraph;
	 BFS visit;

	@Before
	public void init() {
		a = new BFS(grafo);
		assertNotNull(a);
	}
	
	@Test
	public void testVisita() {
		ArrayList<Integer> nodi =  a.getNodesInOrderOfVisit(0);
		assertEquals("Mi aspetto che ne abbia visitati 3",3,nodi.size());
	}
	
	@Test
	public void testConnessione() {
		assertEquals("Mi aspetto che siano veri entrambi",a.isConnected(),a.isConnected2());
		
		GraphInterface grafoNuovo = new DirectedGraph ("5;0 1;1 2;2 0;3 4");
		BFS b = new BFS(grafoNuovo);
		assertEquals("Mi aspetto che siano falsi entrambi",b.isConnected(),b.isConnected2());
	}
	
	@Test
	public void testVisitaCompleta() {
		GraphInterface grafoNuovo = new DirectedGraph ("5;0 1;1 2;2 0;3 4");
		BFS b = new BFS(grafoNuovo);
		assertEquals("Mi aspetto che ne abbia visitati 3",3,b.getNodesInOrderOfVisit(0).size());
		assertEquals("Mi aspetto che ne abbia visitati 5",5,b.getNodesInOrderOfVisitCompleto().size());
	}
	
	@Test
	public void testDistanza() {
		assertEquals("Mi aspetto che il nodo 1 disti 1",1,a.getDistanza()[1]);
		assertEquals("Mi aspetto che il nodo 2 disti 2",2,a.getDistanza()[2]);
	}
	
	@Test
	public void testInitNumeroNodiVisitati() {
		GraphInterface grafo = new UndirectedGraph ("4;0 2;0 1;2 3;1 3");  
		BFS bfsTest = new BFS(grafo);
		int numeroNodi = bfsTest.getNodesInOrderOfVisit(0).size();
		assertEquals(4, numeroNodi);   
		numeroNodi = bfsTest.getNodesInOrderOfVisit(2).size();
		assertEquals(4, numeroNodi);
	}
	
	//************verifica che la visita sia realmente una BFS***********
	   @Test
	   public void test15BFSOrder() {
		   testGraph = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
	       visit = new BFS(testGraph);
	       assertTrue("La visita in ampiezza con sorgente 2 deve trovare i nodi nell'ordine 2,3,0,1 (0 e' il terzo visitato) oppure 2,0,3,1 (3 e' il terzo visitato)",
	    		   visit.getNodesInOrderOfVisit(2).get(2) == 0 ||
	    		   visit.getNodesInOrderOfVisit(2).get(2) == 3);
	       int node = visit.getNodesInOrderOfVisit(2).get(2);
	       assertNotEquals("La visita in ampiezza non deve trovare 1 come terzo nodo", 1, node);
	   }
	   
	   @Test
	   public void test19DFSForest() {
		   testGraph = new UndirectedGraph("5;0 2;0 1;2 1;4 3");
	       visit = new BFS(testGraph);
	       GraphInterface tree = visit.getBFStree(4);
	       assertTrue("L'albero di visita in profondita' con sorgente 4 deve avere l'arco (4,3)"
	       		+ " e nessun altro arco perché non un grafo connesso",(tree.hasEdge(4,3)) && !(tree.hasEdge(0,1)) && !(tree.hasEdge(0,2)) && !(tree.hasEdge(2,1)));
	       visit = new BFS(testGraph);
	       GraphInterface forest = visit.getForest();
	       assertTrue(forest.hasEdge(0,2));// && forest.hasEdge(2,1)); && forest.hasEdge(4,3) && !forest.hasEdge(2,3));
	       assertTrue(!forest.hasEdge(2,4));
	       assertTrue(!forest.hasEdge(4,0));
	       assertTrue(!forest.hasEdge(1,4));
	   }
	   
	 //inizializzazione: metodo richiamato con due sorgenti diverse
	   @Test
	   public void test35InitDistanza() {
	     GraphInterface grafo = 
	   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
	      visit = new BFS(grafo); //<<- creato una volta sola
	 	  assertEquals("distanza(2,2) = 0", 0, visit.getDistance(2)[2]); //<--distanze da 2
	 	  assertEquals("distanza(2,0) = 1", 1, visit.getDistance(2)[0]);
	 	  assertEquals("distanza(3,0) = 2", 2, visit.getDistance(3)[0]); //<--distanze da 3
	 	  assertEquals("distanza(3,1) = 1", 1, visit.getDistance(3)[1]);

	   }
	   
	   @Test
	    public void test25InitOrdine() {
	      GraphInterface grafo =
	            new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
	      BFS visit = new BFS(grafo); //<<- creato una volta sola
	      int dist = visit.getNodesInOrderOfVisit(2).get(3);
	      assertNotEquals("Nella visita da sorgente 2: 3 non e' il quarto visitato", 3, dist); //<<-tre chiamate del metodo
	      dist = visit.getNodesInOrderOfVisit(1).get(3);
	      assertNotEquals("Nella visita da sorgente 1: 0 non e' il quarto visitato", 0, dist);
	      dist = visit.getNodesInOrderOfVisit(0).get(3);
	      assertNotEquals("Nella visita da sorgente 0: 2 non e' il quarto visitato", 2, dist);
	    }
	   
	   //***********test presenza di cicli********************
	    
	    @Test
	    public void test21Ciclo() {
	    	GraphInterface grafo = new UndirectedGraph("1");
	    	a = new BFS(grafo);
	    	assertEquals("Mi aspetto che non ci sia alcun ciclo",false,a.hasUndirectedCycle());
	    }
	    
	    @Test
	    public void test22Ciclo() {
	    	GraphInterface grafo = new UndirectedGraph("2;0 1");
	    	a = new BFS(grafo);
	    	assertEquals("Mi aspetto che non ci sia alcun ciclo",false,a.hasUndirectedCycle());
	    }
	    
	    @Test
	    public void test23Ciclo() {
	    	GraphInterface grafo = new UndirectedGraph("3;0 1;1 2;2 0");
	    	a = new BFS(grafo);
	    	assertEquals("Mi aspetto che ci sia un ciclo",true,a.hasUndirectedCycle());
	    }
	    
	    @Test
	    public void test24Ciclo() {
	    	GraphInterface grafo = new UndirectedGraph("3;0 2;2 1;1 0");
	    	a = new BFS(grafo);
	    	assertEquals("Mi aspetto che ci sia un ciclo",true,a.hasUndirectedCycle());
	    }
	    
	    @Test
	    public void test25Ciclo() {
	    	GraphInterface grafo = new UndirectedGraph ("5;4 0;4 1;4 2;2 3;3 4");
	    	a = new BFS(grafo);
	    	assertEquals("Mi aspetto che ci sia un ciclo",true,a.hasUndirectedCycle());
	    }
	    
	    //***********test ricostruzione del ciclo********************
	  /*  
	    @Test
	    public void test60getDirCycle() {
	    	 GraphInterface grafo = new UndirectedGraph ("1");
			 BFS visit = new BFS(grafo);
			 assertEquals("Non mi aspetto alcun ciclo",null,visit.getUndirCycle());
			 
			 GraphInterface grafo2 = new UndirectedGraph ("2;0 1");
			 BFS visit2 = new BFS(grafo2);
			 assertEquals("Non mi aspetto alcun ciclo",null,visit2.getUndirCycle());
			 
			 GraphInterface grafo3 = new UndirectedGraph ("3;1 0;1 2;0 2");
			 BFS visit3 = new BFS(grafo3);
			 assertTrue(visit3.getUndirCycle()!=null);
			 
			 GraphInterface grafo4 = new UndirectedGraph ("3;0 2;2 1;1 0");
			 BFS visit4 = new BFS(grafo4);
			 assertEquals("Mi aspetto che siano 3",3,visit4.getUndirCycle().size());
			 
			 GraphInterface grafo5 = new UndirectedGraph ("5;4 0;4 1;4 2;2 3;3 4");
			 BFS visit5 = new BFS(grafo5);
			 assertEquals("Mi aspetto",3,visit5.getUndirCycle().size());
			 
			 GraphInterface grafo6 = new UndirectedGraph ("7;0 2;2 1;1 0;3 4;4 5;5 6;6 3");
			 BFS visit6 = new BFS(grafo6);
			 assertEquals("Mi aspetto che siano 3",3,visit6.getUndirCycle().size());
	    }*/
	

}
