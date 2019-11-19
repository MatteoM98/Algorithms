package algoritmoPrim;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;
import it.uniupo.algoTools.MinHeap;

public class Prim {
	
	/*Variabili*/
	UndirectedGraph grafo;
	UndirectedGraph alberoMST; //albero MST
	private ArrayList<Integer> nodiVisitatiInOrdine;
	private MinHeap<Edge,Integer> heap; //heap utilizato dall' algoritmo di Prim
	private boolean[] scoperto; //array che tiene traccia per ogni nodo se è stato scoperto oppure no
	private int costo; //costo dell' MST
	
	
	
	/*Costruttori*/
	public Prim(UndirectedGraph grafo) {
		this.grafo = grafo;
		scoperto = new boolean[grafo.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		heap = new MinHeap<Edge,Integer>();
		costo = 0;
	}
	
	/*Metodi*/
	
	private void init() {
		for(int i=0;i<scoperto.length;i++) {
			scoperto[i] = false;
		}
		alberoMST = (UndirectedGraph) grafo.create();
		nodiVisitatiInOrdine.clear();
		heap = new MinHeap<Edge,Integer>();
		costo = 0;
	}
	
	public UndirectedGraph getMST() {
		init();
		algoritmoPrim(0);
		return alberoMST;
	}
	
	public int getMSTCost () {
		return costo;
	}
	
	private void algoritmoPrim(int sorgente) {
		scoperto[sorgente] = true;
		nodiVisitatiInOrdine.add(sorgente);
		for (Edge su: grafo.getOutEdges(sorgente)) heap.add(su, su.getWeight());
		while(!heap.isEmpty()) {
			Edge uv = heap.extractMin();
			Integer v = uv.getHead();
			if(!scoperto[v]) {
				scoperto[v] = true;
				alberoMST.addEdge(uv);
				costo += uv.getWeight();
				nodiVisitatiInOrdine.add(sorgente);
				for(Edge vw: grafo.getOutEdges(v)) {
					Integer w = vw.getHead();
					if(!scoperto[w]) heap.add(vw, vw.getWeight());
				}
			}
		}
	}

}
