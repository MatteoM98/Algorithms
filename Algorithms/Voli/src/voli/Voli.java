package voli;

import java.util.ArrayList;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class Voli {
	
	/*Variabili*/
	DirectedGraph grafo;
	GraphInterface albero;
	private boolean[] scoperto; //array che tiene traccia dei nodi scoperti
	private int[] distanza; //array che tiene traccia per ogni nodi della distanza dalla sorgente
	private int[] tempo; //array che tiene traccia per ogni vertice il tempo di arrivo
	private int[] scali; //array che tiene tracci per ogni vertice il numero di scali
	private int[] padre; //array che tiene traccia per ogni nodi il padre
	private MinHeap<Edge,Integer> heap; //struttura heap usato dall' algoritmo di Dijkstra
	private ArrayList<Integer> nodiVisitatiInOrdine; //lista dei nodi visitati in ordine
	private ArrayList<Integer> coda; //struttura dati per la visita BFS
	private ArrayList<Integer> scaliEffettuati; //scali effettuati durante il viaggio
	private ArrayList<Edge> archiPercorsi; //archi percorsi durante la tratta più veloce
	
	/*Costruttori*/
	public Voli(DirectedGraph collegamenti) {
		grafo = collegamenti;
		scoperto = new boolean[grafo.getOrder()];
		distanza = new int[grafo.getOrder()];
		tempo = new int[grafo.getOrder()];
		scali = new int[grafo.getOrder()];
		padre = new int[grafo.getOrder()];
		heap = new MinHeap<Edge,Integer>();
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		coda = new ArrayList<Integer>();
		archiPercorsi = new ArrayList<Edge>();
		scaliEffettuati = new ArrayList<Integer>();
	}
	
	/*Metodi*/
	private void init() {
		for(int i=0;i<scoperto.length;i++) {
			scoperto[i] = false;
			distanza[i] = -1;
			tempo[i] = -1;
			scali[i] = -1;
			padre[i] = -1;
		}
		
		heap = new MinHeap<Edge,Integer>();
		nodiVisitatiInOrdine.clear();
		scaliEffettuati.clear();
		archiPercorsi.clear();
		coda.clear();
		albero = grafo.create();
	}
	
	public int tempo (int partenza, int destinazione) throws IllegalArgumentException { //tempo totale di volo con tratta con minor scali
		if(partenza<0 || destinazione<0 || partenza>=grafo.getOrder() || destinazione>=grafo.getOrder())
			throw new IllegalArgumentException("Parametri non validi");
		init();
		visitaBFS(partenza,destinazione);
		return tempo[destinazione];
	}
	
	public int scali(int partenza, int destinazione) throws IllegalArgumentException { //numero minimo di scali per arrivare a destinazione
		if(partenza<0 || destinazione<0 || partenza>=grafo.getOrder() || destinazione>=grafo.getOrder())
			throw new IllegalArgumentException("Parametri non validi");
		init();
		visitaBFS(partenza,destinazione);
		if(scali[destinazione]==0) return 0;
		return scali[destinazione]==-1 ? -1 : scali[destinazione]-1 ;
	}
	
	public int tempoMinimo(int partenza, int destinazione) throws IllegalArgumentException { //tempo minimo di arrivo
		if(partenza<0 || destinazione<0 || partenza>=grafo.getOrder() || destinazione>=grafo.getOrder())
			throw new IllegalArgumentException("Parametri non validi");
		init();
		algoritmoDijkstra(partenza,0);
		return distanza[destinazione];
	}
	
	public ArrayList<Edge> trattaVeloce (int partenza, int destinazione) throws IllegalArgumentException {
		if(partenza<0 || destinazione<0 || partenza>=grafo.getOrder() || destinazione>=grafo.getOrder())
			throw new IllegalArgumentException("Parametri non validi");
		init();
		algoritmoDijkstra(partenza,destinazione);
		return scoperto[destinazione]==true ? archiPercorsi : null;
	}
	
	public ArrayList<Integer> elencoScali (int partenza, int destinazione) throws IllegalArgumentException {
		int scali = scali(partenza,destinazione);
		int figlio = destinazione;
		int papa;
		while(true) {
			papa = padre[figlio];
			figlio = papa;
			if(papa==partenza || papa==-1) break;
			scaliEffettuati.add(papa);
		}
		return scali==-1 ? null : scaliEffettuati;
	}
	
	public GraphInterface getAlberoCamminiMinimi(int sorgente) {
		init();
		algoritmoDijkstra(sorgente,0);
		return albero;
	}
	
	private void visitaBFS(int sorgente,int destinazione) throws IllegalArgumentException {
		if(sorgente<0 || sorgente>=grafo.getOrder()) throw new IllegalArgumentException("Nodo sorgente non valido");
		scoperto[sorgente] = true;
		coda.add(sorgente);
		tempo[sorgente] = 0;
		scali[sorgente] = 0;
		nodiVisitatiInOrdine.add(sorgente);
		while(!coda.isEmpty()) {
			Integer u = coda.remove(0);
			for(Edge uv: grafo.getOutEdges(u)) {
				Integer v = uv.getHead();
				if(scoperto[v]==false) {
					coda.add(v);
					scoperto[v] = true;
					nodiVisitatiInOrdine.add(v);
					albero.addEdge(u, v);
					padre[v] = u;
					tempo[v] = tempo[u] + uv.getWeight();
					scali[v] = scali[u] + 1;
					if(v==destinazione) return;
				}
				
			}
		
		}
	}
	
	
	private void algoritmoDijkstra(int sorgente,int destinazione) throws IllegalArgumentException {
		if(sorgente<0 || sorgente>=grafo.getOrder()) throw new IllegalArgumentException("Nodo sorgente non valido");
		scoperto[sorgente] = true;
		distanza[sorgente] = 0;
		nodiVisitatiInOrdine.add(sorgente);
		for(Edge sv: grafo.getOutEdges(sorgente)) heap.add(sv,distanza[sorgente] + sv.getWeight());
		while(!heap.isEmpty()) {
			Edge uv = heap.extractMin();
			Integer v = uv.getHead();
			if(!scoperto[v]) {
				scoperto[v] = true;
				distanza[v] = distanza[uv.getTail()] + uv.getWeight();
				nodiVisitatiInOrdine.add(v);
				archiPercorsi.add(uv);
				albero.addEdge(uv);
				if(destinazione!=0 && v==destinazione) return;
				for(Edge vw: grafo.getOutEdges(v)) {
					Integer w = vw.getHead();
					if(!scoperto[w]) heap.add(vw,distanza[v] + vw.getWeight());
				}
			}
		}
	}
	

}
