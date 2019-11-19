package metro;

import java.util.ArrayList;

import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class Metro {

	/*Variabili*/
	private GraphInterface grafo;
	private GraphInterface alberoBFS;
	private int[] distanza;
	private int[] padre; 
	private boolean[] scoperto;
	private ArrayList<Integer> nodiVisitatiInOrdine;
	private ArrayList<Integer> coda;
	
	
	/*Costruttore*/
	public Metro(UndirectedGraph mappa) {
		grafo = mappa;
		distanza = new int[grafo.getOrder()];
		scoperto = new boolean[grafo.getOrder()];
		padre = new int[grafo.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		coda = new ArrayList<Integer>();
	}
	
	/*Metodi*/
	private void visitaBFS(int sorgente) {
		scoperto[sorgente]=true;
		coda.add(sorgente);
		distanza[sorgente]=0;
		nodiVisitatiInOrdine.add(sorgente);
		while(!coda.isEmpty()) {
			Integer u = coda.remove(0);
			for(Integer v: grafo.getNeighbors(u)) {
				if(scoperto[v]==false) {
					scoperto[v] = true;
					alberoBFS.addEdge(u,v);
					coda.add(v);
					distanza[v] = distanza[u]+1;
					padre[v] = u;
				}
			}
		}
	}
	
	private void init() {
		for(int i=0;i<distanza.length;i++) {
			distanza[i] = -1;
			scoperto[i] = false;
			padre[i] = -1;
		}
		coda.clear();
		nodiVisitatiInOrdine.clear();
		alberoBFS = grafo.create();
	}
	
	public int numeroFermate (int partenza, int arrivo) {
		init();
		visitaBFS(partenza);
		return distanza[arrivo];
	}
	
	public ArrayList<Integer> fermate( int partenza, int arrivo) throws IllegalArgumentException {
		if(partenza<0 || arrivo<0 || partenza > grafo.getOrder() || arrivo > grafo.getOrder()) throw new IllegalArgumentException("Parametri non validi");
		init();
		visitaBFS(partenza);
		ArrayList<Integer> insiemeFermate = new ArrayList<Integer>();
		while(arrivo!=partenza) {
			insiemeFermate.add(0,arrivo);
			if(padre[arrivo]==-1) return null;
			arrivo = padre[arrivo];
		}
		insiemeFermate.add(0,partenza);
		return insiemeFermate;
	}
}
