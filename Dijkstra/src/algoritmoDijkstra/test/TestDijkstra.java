package algoritmoDijkstra.test;


import org.junit.Test;

import algoritmoDijkstra.Dijkstra;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class TestDijkstra {

	GraphInterface grafo;
	Dijkstra dj;
	
	@Test
	public void testDijkstra() {
		grafo = new UndirectedGraph("7; 1 2 5; 1 3 2; 1 4 3; 2 6 3; 6 0 2; 4 0 4; 3 5 2; 5 2 1");
		dj = new Dijkstra(grafo);
		int[] distanza = dj.getDistanze(1);
		for(int i=0;i<grafo.getOrder();i++)
			System.out.println(distanza[i]);
		System.out.println(dj.getOrdineFineVisita());
	}

}
