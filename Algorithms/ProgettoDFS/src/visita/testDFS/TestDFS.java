package visita.testDFS;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;
import visita.dfs.DFS;

public class TestDFS {

	GraphInterface testGraph;
    DFS dfsTest;
    
    //********************************
    //testCreate(): non ci sono errori nel costruttore
    
    @Test
    public void test00Create() {
        testGraph = new UndirectedGraph(1);
        dfsTest = new DFS(testGraph);
        assertNotNull(dfsTest);
    }
    
    //********test base***************
    
    //deve funzionare correttamente in un grafo banale con un solo nodo e senza archi
    @Test
    public void test10OneNodeVisited() { //DFS su grafo con un solo nodo
        testGraph = new UndirectedGraph(1);
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(0);
        int edges = tree.getEdgeNum();
        assertEquals("Non ci sono archi", 0, edges);
    }

    //deve funzionare correttamente in un grafo banale con due nodi e un arco
    @Test
    public void test11TwoNodesVisited() { //DFS su grafo con due nodi
        testGraph = new UndirectedGraph(2);
        testGraph.addEdge(1, 0);
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(0);
        int edges = tree.getEdgeNum();
        assertEquals("C'e' un arco", 1, edges);

    }
    
    //************verifica che la visita sia realmente una DFS***********
    @Test
    public void test15DFSOrder() {
 	   testGraph = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(2);
        assertTrue("L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi",!(tree.hasEdge(2,3))  ||
     		   !(tree.hasEdge(0,2)) );
        assertTrue("L'albero di visita in profondita' con sorgente 2 deve avere gli archi (1,3) e (0,1)",(tree.hasEdge(1,3))  &&
     		   (tree.hasEdge(0,1)));
        
    }
    
    @Test
    public void test17DFSOrder() {
 	   testGraph = new UndirectedGraph("4;1 0;0 2;3 2;3 1");
 	   dfsTest = new DFS(testGraph);
 	   @SuppressWarnings("unused")
 	   ArrayList<Integer> nodiVisitati = dfsTest.getNodesInOrderOfVisit(1);
 	   
    }
    
    @Test
    public void test19DFSForest() {
 	   testGraph = new UndirectedGraph("5;0 2;0 1;2 1;4 3");
        dfsTest = new DFS(testGraph);
        GraphInterface tree = dfsTest.getDFStree(4);
        assertTrue("L'albero di visita in profondita' con sorgente 4 deve avere l'arco (4,3)"
        		+ " e nessun altro arco perché non un grafo connesso",(tree.hasEdge(4,3)) && !(tree.hasEdge(0,1)) && !(tree.hasEdge(0,2)) && !(tree.hasEdge(2,1)));
        dfsTest = new DFS(testGraph);
        GraphInterface forest = dfsTest.getForest();
        assertTrue(forest.hasEdge(0,2) && forest.hasEdge(2,1) && forest.hasEdge(4,3) && !forest.hasEdge(2,3));
        assertTrue(!forest.hasEdge(2,4));
        assertTrue(!forest.hasEdge(4,0));
        assertTrue(!forest.hasEdge(1,4));
    }
    
    //*************test inizializzazione******************
    
    //numero di archi
    @Test 
    public void test20InitArchi() {
      GraphInterface grafo = 
    		new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
      dfsTest = new DFS(grafo); //<<- creato una volta sola
      assertTrue("L'albero di visita in profondita' con sorgente 2 deve avere l'arco (2,3) o lo (0,2) ma non entrambi",!(dfsTest.getDFStree(2).hasEdge(2,3))  ||
   		   !(dfsTest.getDFStree(2).hasEdge(0,2)) );
      assertTrue("L'albero di visita in profondita' con sorgente 0 deve avere l'arco (0,2) o lo (0,1) ma non entrambi",!(dfsTest.getDFStree(0).hasEdge(0,1))  ||
     		   !(dfsTest.getDFStree(0).hasEdge(0,2)) );
    }
    
    
    //***********test presenza di cicli********************
    
    @Test
    public void test21Ciclo() {
    	GraphInterface grafo = new DirectedGraph("1");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che non ci sia alcun ciclo",false,dfsTest.hasDirCycle());
    }
    
    @Test
    public void test22Ciclo() {
    	GraphInterface grafo = new DirectedGraph("2;0 1");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che non ci sia alcun ciclo",false,dfsTest.hasDirCycle());
    }
    
    @Test
    public void test23Ciclo() {
    	GraphInterface grafo = new DirectedGraph("3;0 1;1 2;2 0");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci sia un ciclo",true,dfsTest.hasDirCycle());
    }
    
    @Test
    public void test24Ciclo() {
    	GraphInterface grafo = new DirectedGraph("3;0 2;2 1;1 0");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci sia un ciclo",true,dfsTest.hasDirCycle());
    }
    
    @Test
    public void test25Ciclo() {
    	GraphInterface grafo = new DirectedGraph ("5;4 0;4 1;4 2;2 3;3 4");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci sia un ciclo",true,dfsTest.hasDirCycle());
    }
    
    //***********test ricostruzione del ciclo********************
    
    @Test
    public void test60getDirCycle() {
    	 GraphInterface grafo = new DirectedGraph ("1");
		 DFS visit = new DFS(grafo);
		 assertEquals("Non mi aspetto alcun ciclo",null,visit.getDirCycle());
		 
		 GraphInterface grafo2 = new DirectedGraph ("2;0 1");
		 DFS visit2 = new DFS(grafo2);
		 assertEquals("Non mi aspetto alcun ciclo",null,visit2.getDirCycle());
		 
		 GraphInterface grafo3 = new DirectedGraph ("3;1 0;1 2;0 2");
		 DFS visit3 = new DFS(grafo3);
		 assertTrue(visit3.getDirCycle()==null);
		 
		 GraphInterface grafo4 = new DirectedGraph ("3;0 2;2 1;1 0");
		 DFS visit4 = new DFS(grafo4);
		 assertEquals("Mi aspetto che siano 3",3,visit4.getDirCycle().size());
		 System.out.println(visit4.getDirCycle());
		 
		 GraphInterface grafo5 = new DirectedGraph ("5;4 0;4 1;4 2;2 3;3 4");
		 DFS visit5 = new DFS(grafo5);
		 assertTrue(visit5.getDirCycle().size()==3);
		 System.out.println(visit5.getDirCycle());
		 
		GraphInterface grafo6 = new DirectedGraph ("7;0 2;2 1;1 0;3 4;4 5;5 6;6 3");
		 DFS visit6 = new DFS(grafo6);
		 assertEquals("Mi aspetto che siano 3",3,visit6.getDirCycle().size());
    }
    
    //**********test componenti connesse*********************
    
    @Test
    public void test26CC() {
    	GraphInterface grafo = new DirectedGraph("1");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci sia una sola componente connnessa",1,dfsTest.getContatore());
    }
    
    @Test
    public void test27CC() {
    	GraphInterface grafo = new DirectedGraph ("2;0 1");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci sia una sola componente connnessa",1,dfsTest.getContatore());
    }
    
    @Test
    public void test28CC() {
    	GraphInterface grafo = new UndirectedGraph ("2;0 1");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci sia una sola componente connnessa",1,dfsTest.getContatore());
    }
    
    @Test
    public void test29CC() {
    	GraphInterface grafo = new DirectedGraph ("7;0 2;2 1;1 0;3 4;4 5;5 6;6 3");
    	dfsTest = new DFS(grafo);
    	assertEquals("Mi aspetto che ci siano 2 componenti connnesse",2,dfsTest.getContatore());
    	assertEquals("Mi aspetto che ci siano 2 componenti connnesse",2,dfsTest.getConnectedComponents().size());
    	assertEquals("Mi aspetto che la prima CC abbia 3 elementi",3,dfsTest.getConnectedComponents().get(0).size());
    	assertEquals("Mi aspetto che la seconda CC abbia 4 elementi",4,dfsTest.getConnectedComponents().get(1).size());
    }
    
    
    //***************test ordine topologico***********************
    @Test
    public void test70TopologicalOrder() {
    	DirectedGraph grafo = new DirectedGraph("4;3 1;3 0;1 2;1 0;0 2");
    	DFS visita = new DFS(grafo);
    	assertTrue(visita.topologicalOrder().get(0)==3);
    	System.out.println("Ordine topologico --> " + visita.topologicalOrder() + "\nOrdine di Fine Visita --> " + visita.getNodesInOrderOfVisit(3));
    }
    
   

}
