package software;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;

public class SoftwareSystem {
	
	/*Variabili*/
	private DirectedGraph grafo;
	private GraphInterface alberoDFS;
	private ArrayList<Integer> nodiVisitatiInOrdine;
	private boolean[] scoperto; //array che tiene traccia per ogni nodo se è stato scoperto oppure no
	private boolean[] terminato; //array che tiene traccia per ogni nodo se è terminata la visita oppure no
	private int ultimoNodoVisitato; //tiene traccia del nodo che ha scovato il ciclo
	private int nodoIndietro; //tiene traccia del nodo di cui non è ancora terminata la visita
	private boolean ciclo; //variabile boolean che indica la presenza di almeno un ciclo nel grafo
	
	/*Costruttori*/
	public SoftwareSystem(DirectedGraph system) {
		grafo = system;
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		scoperto = new boolean[grafo.getOrder()];
		terminato = new boolean[grafo.getOrder()];
		ultimoNodoVisitato = -1;
		nodoIndietro = -1;
		ciclo = false;
		
	}
	
	/*Metodi*/
	
	private void init() {
		for(int i=0;i<scoperto.length;i++) {
			scoperto[i] = false;
			terminato[i] = false;
		}
		
		alberoDFS = grafo.create();
		nodiVisitatiInOrdine.clear();
		ultimoNodoVisitato = -1;
		nodoIndietro = -1;
		ciclo = false;
	}
	
	public boolean hasCycle() {
		visitaDFScompleta();
		return ciclo;
	}
	
	
	private void visitaDFS(int sorgente) {
		scoperto[sorgente] = true;
		for(Integer v: grafo.getNeighbors(sorgente)) {
			if(scoperto[v]==false) {
				alberoDFS.addEdge(sorgente,v);
				visitaDFS(v);
			}else if (scoperto[v] && !terminato[v])
			{
				ciclo = true;
				ultimoNodoVisitato = sorgente;
				nodoIndietro = v;
			}
		}
		
		nodiVisitatiInOrdine.add(sorgente);
		terminato[sorgente] = true;
	}
	
	
	private void visitaDFScompleta() {
		init();
		for(int i=0;i<scoperto.length;i++) {
			if(scoperto[i]==false) {
				visitaDFS(i);
			}
		}
	}

}
