package it.uniupo.labAlgo2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

/*********
 * le seguenti tre righe servono per eseguire i test in ordine
 * Non e' corretto perche' i test devono avere successo in qualunque 
 * ordine siano eseguiti, ma serve per il lavoro in lab:
 * lavorate in modo che i test abbiano successo dal primo in avanti
 */
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestDFSguidata {
    GraphInterface testGraph;
    DFSguidata dfsTest;
    
    //********************************
    //testCreate(): non ci sono errori nel costruttore
    
    @Test
    public void test00Create() {
        testGraph = new UndirectedGraph(1);
        dfsTest = new DFSguidata(testGraph);
        assertNotNull(dfsTest);
    }
    
     
    //********test base***************
    
    //deve funzionare correttamente in un grafo banale con un solo nodo e senza archi
    @Test
    public void test10OneNodeVisited() { //DFS su grafo con un solo nodo
        testGraph = new UndirectedGraph(1);
        dfsTest = new DFSguidata(testGraph);
        GraphInterface tree = dfsTest.getDFStree(0);
        int edges = tree.getEdgeNum();
        assertEquals("Non ci sono archi", 0, edges);
    }

    //deve funzionare correttamente in un grafo banale con due nodi e un arco
    @Test
    public void test11TwoNodesVisited() { //DFS su grafo con due nodi
        testGraph = new UndirectedGraph(2);
        testGraph.addEdge(1, 0);
        dfsTest = new DFSguidata(testGraph);
        GraphInterface tree = dfsTest.getDFStree(0);
        int edges = tree.getEdgeNum();
        assertEquals("C'e' un arco", 1, edges);

    }
    
    //************verifica che la visita sia realmente una DFS***********
   @Test
   public void test15BFSOrder() {
	   testGraph = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
       dfsTest = new DFSguidata(testGraph);
       GraphInterface tree = dfsTest.getDFStree(2);
       assertTrue("L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi",!(tree.hasEdge(2,3))  ||
    		   !(tree.hasEdge(0,2)) );
       assertTrue("L'albero di visita in profondita' con sorgente 2 deve avere gli archi (1,3) e (0,1)",(tree.hasEdge(1,3))  &&
    		   (tree.hasEdge(0,1)));
       
   }
   
   
   //*************test inizializzazione******************
   
   //numero di archi
   @Test 
   public void test20InitArchi() {
     GraphInterface grafo = 
   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
     dfsTest = new DFSguidata(grafo); //<<- creato una volta sola
     assertTrue("L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi",!(dfsTest.getDFStree(2).hasEdge(2,3))  ||
  		   !(dfsTest.getDFStree(2).hasEdge(0,2)) );
     assertTrue("L'albero di visita in profondita' con sorgente 0 deve avere l'arco (0,2) o lo (0,1) ma non entrambi",!(dfsTest.getDFStree(0).hasEdge(0,1))  ||
    		   !(dfsTest.getDFStree(0).hasEdge(0,2)) );
   }
   
  
  //***************test eccezione input metodo*************
   
   @Test (expected = IllegalArgumentException.class)
   public void test30illegalargBig() {
	   GraphInterface grafo = 
		   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
		     DFSguidata visit = new DFSguidata(grafo);
		     visit.getDFStree(5);  
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void test35illegalargSmall() {
	   GraphInterface grafo = 
		   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
		     DFSguidata visit = new DFSguidata(grafo);
		     visit.getDFStree(-1);  
   }
   
   @Test (expected = IllegalArgumentException.class)
   public void test40illegalargOrd() {
	   GraphInterface grafo = 
		   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
		     DFSguidata visit = new DFSguidata(grafo);
		     visit.getDFStree(4);  
   }
   
   @Test 
   public void test50illegalargLegal() {
	   GraphInterface grafo = 
		   		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
		     DFSguidata visit = new DFSguidata(grafo);
		     try {
		     visit.getDFStree(0);  
		     } catch (IllegalArgumentException e) {
		    	 fail("0 e' un argomento legittimo");
		     }
   }
  
   @Test
   public void testInverse()
   {
	   GraphInterface grafo = new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
	   DFSguidata visit = new DFSguidata(grafo);
	   ArrayList <Integer> prova = visit.getNodesInOrderPostVisit(0);
	   assertEquals(visit.nodiVisitatiInOrdine.get(3),prova.get(0));
	   assertEquals(visit.nodiVisitatiInOrdine.get(2),prova.get(1));
	   assertEquals(visit.nodiVisitatiInOrdine.get(1),prova.get(2));
	   assertEquals(visit.nodiVisitatiInOrdine.get(0),prova.get(3));
	   
   }
   
    
}
