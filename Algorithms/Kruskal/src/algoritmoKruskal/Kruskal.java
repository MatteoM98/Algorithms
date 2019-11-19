package algoritmoKruskal;

import it.uniupo.algoTools.*;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Kruskal {

	/*Variabili*/
	private UndirectedGraph grafo;
	private UndirectedGraph alberoMST;
	private MinHeap<Edge,Integer> heap;
	private int costoMST;
	private UnionFind set;
			
	/*Costruttori*/
	public Kruskal(UndirectedGraph graph) {
		grafo = graph;
		heap = new MinHeap<Edge,Integer>();
		set = new UnionByRank(grafo.getOrder());
		costoMST = 0;
	}
	
	/*Metodi*/
	private void init() {
		heap = new MinHeap<Edge,Integer>();
		set = new UnionByRank(grafo.getOrder());
		costoMST = 0;
		alberoMST = (UndirectedGraph) grafo.create();
	}
	
	public UndirectedGraph getMST() {
		init();
		algoritmoKruskal();
		return alberoMST;
	}
	
	public int getCost () {
		return costoMST;
	}
	
	private void algoritmoKruskal() {
		addEdges(heap);
		while(!heap.isEmpty()) {
			Edge uv = heap.extractMin();
			Integer u = uv.getTail();
			Integer v = uv.getHead();
			if(set.find(u) != set.find(v)) {
				alberoMST.addEdge(uv);
				costoMST += uv.getWeight();
				set.union(set.find(u), set.find(v));
			}
		}
	}
	
	
	private void addEdges(MinHeap<Edge,Integer> heap) {
		for(int i=0;i<grafo.getOrder();i++) {
			for(Edge uv : grafo.getOutEdges(i)) heap.add(uv, uv.getWeight());
		}
	}
}
