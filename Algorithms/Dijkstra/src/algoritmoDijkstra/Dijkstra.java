package algoritmoDijkstra;

import java.util.ArrayList;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.*;

public class Dijkstra {

	/*Variabili*/
	GraphInterface grafo;
	GraphInterface albero;
	private int[] distanza; //array il cui elemento v è la distanza del nodo v dalla sorgente
	private boolean[] scoperto; //array che tiene traccia dei nodi scoperti
	private ArrayList<Integer> nodiVisitatiInOrdine; //tiene traccia dei nodi visitati in ordine;
	private MinHeap<Edge,Integer> heap;  //heap usato nell' algoritmo
	
	
	/*Costruttori*/
	public Dijkstra(GraphInterface g) {
		grafo = g;
		distanza = new int[grafo.getOrder()];
		scoperto = new boolean[grafo.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		heap = new MinHeap<Edge,Integer>();
	}
	
	/*Metodi*/
	private void init() {
		for(int i=0;i<scoperto.length;i++) {
			distanza[i] = -1;
			scoperto[i] = false;
		}
		nodiVisitatiInOrdine.clear();
		heap = new MinHeap<Edge,Integer>();
		albero = grafo.create();
		
	}
	
	public int[] getDistanze(int sorg) {
		init();
		algoritmoDijkstra(sorg);
		return distanza;
	}
	
	public ArrayList<Integer> getOrdineFineVisita() {
		return nodiVisitatiInOrdine.size()==0 ? null : nodiVisitatiInOrdine;
	}
	
	private void algoritmoDijkstra (int sorgente) throws IllegalArgumentException {
		if(sorgente<0 || sorgente>=grafo.getOrder()) throw new IllegalArgumentException("Nodo sorgente non valido");
		scoperto[sorgente] = true;
		distanza[sorgente] = 0;
		nodiVisitatiInOrdine.add(sorgente);
		for(Edge e : grafo.getOutEdges(sorgente)) {
			heap.add(e, distanza[sorgente] + e.getWeight());
		}
		while(!heap.isEmpty()) {
			Edge uv = heap.extractMin();
			Integer v = uv.getHead();
			if(!scoperto[v]) {
				scoperto[v] = true;
				distanza[v] = distanza[uv.getTail()] + uv.getWeight();
				nodiVisitatiInOrdine.add(v);
				for(Edge vw : grafo.getOutEdges(v)) {
					Integer w = vw.getHead();
					if(!scoperto[w]) heap.add(vw, distanza[v]+vw.getWeight());
				}
			}
		}
	}
	
	
	
	
}
