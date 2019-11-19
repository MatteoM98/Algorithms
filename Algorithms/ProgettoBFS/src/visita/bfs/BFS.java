package visita.bfs;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class BFS {
	
	/*Variabili d' istanza*/
	private GraphInterface grafo; //per memorizzare il grafo su cui si lavora
	private boolean[] scoperto; //per memorizzare i nodi scoperti: scoperti[2]=true se il nodo 2 e' stato scoperto
	private ArrayList<Integer> nodiVisitatiInOrdine; //elenco dei nodi nell'ordine in cui sono stati visitati
	private int[] distanza; //distanza[v] = distanza del nodo v dalla sorgente
	private int[] padre; //array che tiene traccia per ogni nodo del padre
	private ArrayList<Integer> coda; //struttura dati usata per la visita in ampiezza
	private GraphInterface alberoBFS; //struttura dati che memorizza l' albero BFS generato dalla visita
	private boolean ciclo; // variabile booleana che indica se nel grafo è presente un ciclo
	private int ultimoVisitato; //nodo che ha scovato il ciclo
	int nodoIndietro;
	
	
	/* Costruttori */
	public BFS (GraphInterface g) {
		grafo = g;
		scoperto = new boolean[g.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<>();
		distanza = new int[g.getOrder()];
		padre = new int[g.getOrder()];
		coda = new ArrayList<>();
		ciclo = false;
		ultimoVisitato = -1;
		nodoIndietro = -1;
	}
	
	
	
	/*Metodi*/
	public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente) {
		init();
		visitaBFS(sorgente);
		return nodiVisitatiInOrdine;
		
	}
	
	public ArrayList<Integer> getNodesInOrderOfVisitCompleto() {
		init();
		visitaBFScompleta();
		return nodiVisitatiInOrdine;
		
	}
	
	/**
	 * Metodo che esegue la visita BFS
	 * @param sorgente numero del vertice da cui iniziare la visita
	 * 
	 */
	private void visitaBFS(int sorgente) {
		if(sorgente>=grafo.getOrder() || sorgente<0) throw new IllegalArgumentException("Argomento fuori dal range");
		coda.add(sorgente);
		scoperto[sorgente] = true;
		distanza[sorgente] = 0;
		nodiVisitatiInOrdine.add(sorgente);
		while(!coda.isEmpty()) {
			Integer u = coda.remove(0);
			for (Integer v: grafo.getNeighbors(u)) {
				if(scoperto[v]==false) {
					coda.add(v);
					scoperto[v]= true;
					nodiVisitatiInOrdine.add(v);
					alberoBFS.addEdge(new Edge(u,v));
					distanza[v] = distanza[u] + 1;
					padre[v] = u;
				}else if (scoperto[v] && padre[u]!=v) {
					ciclo = true;
					//ultimoVisitato = v;
					//nodoIndietro = u;
				}
			}
			
		}
	}
	
	 private void visitaBFScompleta() {
		 init();
		 for(int i=0;i<grafo.getOrder();i++) {
			 if(!scoperto[i]) {
				 visitaBFS(i);
			 }
		 }
	 }
	 
	 public boolean isConnected() {
		 init();
		 visitaBFS(0);
		 return nodiVisitatiInOrdine.size()==grafo.getOrder() ? true : false;
	 }
	 
	 public boolean isConnected2() {
		 init();
		 visitaBFS(0);
		 for (int i=0; i<grafo.getOrder(); i++) {
			 if(scoperto[i]==false) return false;
		 }
		 return true;
	 }
	 
	 public int[] getDistanza() {
		 init();
		 visitaBFS(0);
		 return distanza;
	 }
	 
	 public int[] getDistance(int sorgente) { 
		 init();
		 visitaBFS(sorgente);
		 return distanza; 
	 }
	 
	 public GraphInterface getBFStree(int sorgente) {
		 init();
		 visitaBFS(sorgente);
		 return alberoBFS;
	 }
	 
	 public GraphInterface getForest() {
		 visitaBFScompleta();
		 return alberoBFS;
	 }
	 
	 public boolean hasUndirectedCycle() {
		 visitaBFScompleta();
		 return ciclo;
	 }
	 
	
	//Metodo usato per inizializzare la BFS
	private void init() {
		for(int i=0;i<distanza.length;i++) {
			distanza[i] = -1;
			scoperto[i] = false;
			padre[i] = -1;
		}
		coda.clear();
		nodiVisitatiInOrdine.clear();
		alberoBFS = grafo.create();
		ciclo = false;
		ultimoVisitato = -1;
		nodoIndietro = -1;
	}

}
