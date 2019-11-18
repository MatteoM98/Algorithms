package algoritmoKruskal;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Hikes {

	/*Variabili*/
	private UndirectedGraph grafo;
	private UndirectedGraph alberoMST;
	private MinHeap<Edge,Integer> heap;
	private UnionFind uf;
	private int spaziamento;
	
	/*Costruttori*/
	public Hikes (UndirectedGraph rifugi) {
		grafo = rifugi;
		alberoMST = (UndirectedGraph) grafo.create();
		heap = new MinHeap<Edge,Integer>();
		uf = new UnionByRank(grafo.getOrder());
		spaziamento = 0;
	}
	
	/*Metodi*/
	private void init() {
		alberoMST = (UndirectedGraph) grafo.create();
		heap = new MinHeap<Edge,Integer>();
		uf = new UnionByRank(grafo.getOrder());
		spaziamento = 0;
	}
	
	
	public int minDistanza(int numZones) throws IllegalArgumentException {
		if(numZones <= 0 || numZones >= grafo.getOrder()) throw new IllegalArgumentException("Numero zona non valida");
		init();
		algoritmoClusteringKruskal(numZones);
		return spaziamento;
	}
	
	
	private void addEdges(MinHeap<Edge,Integer> heap) throws IllegalArgumentException {
		for(int i=0;i<grafo.getOrder();i++) {
			for(Edge uv: grafo.getOutEdges(i)) {
				if(uv.getWeight() <= 0 ) throw new IllegalArgumentException("Arco con valore negativo o nullo");
				heap.add(uv,uv.getWeight());
			}
		}
	}
	
	
	private void algoritmoClusteringKruskal(int k) throws IllegalArgumentException {
		addEdges(heap);
		while(!heap.isEmpty() && uf.getNumberOfSets() > k) {
			Edge uv = heap.extractMin();
			Integer u = uv.getTail();
			Integer v = uv.getHead();
			if(uf.find(u) != uf.find(v)) {
				alberoMST.addEdge(uv);
				uf.union(uf.find(u), uf.find(v));
			}
		}
		
		Integer u;
		Integer v;
		do {
			Edge uv = heap.extractMin();
			u = uv.getTail();
			v = uv.getHead();
			spaziamento = uv.getWeight();
		} while(uf.find(u) == uf.find(v));
	}
	
	
}
