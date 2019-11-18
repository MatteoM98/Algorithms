package algoritmoKruskal;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Clustering {

	/*Variabili*/
	private UndirectedGraph grafo;
	private UndirectedGraph alberoMST;
	private MinHeap<Edge,Integer> heap;
	private int costoMST;
	private UnionFind set;
	private int spaziamento;
	private int numeroInsiemi;
	
	/*Costruttori*/
	public Clustering (UndirectedGraph graph, int nInsiemi) {
		grafo = graph;
		heap = new MinHeap<Edge,Integer>();
		set = new UnionByRank(grafo.getOrder());
		costoMST = 0;
		spaziamento = 0;
		numeroInsiemi = nInsiemi;
		alberoMST = (UndirectedGraph) grafo.create();
	}
	
	/*Metodi*/
	private void init() {
		heap = new MinHeap<Edge,Integer>();
		set = new UnionByRank(grafo.getOrder());
		costoMST = 0;
		alberoMST = (UndirectedGraph) grafo.create();
		spaziamento = 0;
	}
	
	public UndirectedGraph getMST() {
		init();
		//algoritmoClusteringKruskal(numeroInsiemi);
		return alberoMST;
	}
	
	public int getCost () {
		return costoMST;
	}
	
	public int spaziamento() {
		init();
		algoritmoClusteringKruskal(numeroInsiemi);
		return spaziamento;
	}
	
	public boolean sameCluster(int a, int b) {
		init();
		algoritmoClusteringKruskal(numeroInsiemi);
		return set.find(a) == set.find(b) ? true : false;
	}
	
	private void algoritmoClusteringKruskal(int k) {
		addEdges(heap);
		while(!heap.isEmpty() && set.getNumberOfSets() > k) {
			Edge uv = heap.extractMin();
			Integer u = uv.getTail();
			Integer v = uv.getHead();
			if(set.find(u) != set.find(v)) {
				alberoMST.addEdge(uv);
				set.union(set.find(u), set.find(v));
			}
		}
		
		Integer u;
		Integer w;
		do {						//continuo ad estrarre per ricercare il primo arco minimo non appartente allo stesso cluster, ovvero t.c u app C1 && w app C2
			Edge tmp = heap.extractMin();
			u = tmp.getTail();
			w  = tmp.getHead();
			spaziamento = tmp.getWeight();
		} while(set.find(u) == set.find(w));
		
	}
	
	
	private void addEdges(MinHeap<Edge,Integer> heap) {
		for(int i=0;i<grafo.getOrder();i++) {
			for(Edge uv : grafo.getOutEdges(i)) heap.add(uv, uv.getWeight());
		}
	}
}
