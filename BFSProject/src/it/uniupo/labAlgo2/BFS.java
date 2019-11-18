package it.uniupo.labAlgo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class BFS {
	//variabili di istanza
	GraphInterface grafo; //per memorizzare il grafo su cui si lavora
	boolean[] scoperto; //per memorizzare i nodi scoperti: scoperti[2]=true se il nodo 2 e' stato scoperto
	ArrayList<Integer> nodiVisitatiInOrdine; //elenco dei nodi nell'ordine in cui sono stati visitati
	int[] distanza; //distanza[v] = distanza del nodo v dalla sorgente
	
	/****************************
	 * Questo e' il costruttore
	 ****************************/
	BFS(GraphInterface grafoInInput){
		//memorizzare il grafo in input nella variabile di istanza
		this.grafo = grafoInInput;
		//costruire un oggetto per ogni variabile di istanza
		this.scoperto = new boolean[this.grafo.getOrder()];
		this.distanza = new int[this.grafo.getOrder()];
	}
	
	private void init(){
		//qui inizializzate correttamente le variabili di istanza (tranne il grafo)
		for(int i=0;i<this.grafo.getOrder();i++)
		{
			this.distanza[i] = -1;
			this.scoperto[i] = false;
		}
		
		this.nodiVisitatiInOrdine = new ArrayList<Integer>();
	}

	
	/********
	 *  Il metodo visitaBFS(int sorgente) fa una visita BFS dalla sorgente, ma non restituisce niente:
	 *  modifica i valori delle opportune variabili di istanza
	 */
	 private void visitaBFS(int sorgente) {
		 //la coda puo' essere implementata come una ArrayList di interi
	     ArrayList<Integer> coda = new ArrayList<Integer>();
	     //per aggiungere un elemento in fondo alla "coda": coda.add(elemento)
	     //per leggere e cancellare il primo elemento coda.remove(0)
	     init();
	     this.distanza[sorgente]=0;
	     coda.add(sorgente);
	     this.scoperto[sorgente] = true;
	     this.nodiVisitatiInOrdine.add(sorgente);
	     while(!coda.isEmpty())
	     {
	    	 int u = coda.remove(0);
	    	 Iterable <Integer> elencoVicini = this.grafo.getNeighbors(u);
	    	 for(Integer v:elencoVicini)
	    	 {
	    		 if(this.scoperto[v]==false)
	    		 {
	    			 coda.add(v);
	    			 this.scoperto[v]=true;
	    			 this.nodiVisitatiInOrdine.add(v);
	    			 this.distanza[v] = this.distanza[u] + 1;
	    		 }
	    			 
	    	 }
	     }
	       
	 }
	 
	 public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente){
		visitaBFS(sorgente);
		if(!this.nodiVisitatiInOrdine.isEmpty()) return this.nodiVisitatiInOrdine;
		return null; 
	 }
	 
	 
	 public int[] getDistance(int sorgente) { //resituisce le distanza di ciascun nodo da sorg
		visitaBFS(sorgente);
		if(this.distanza.length>0) return this.distanza;
		return null;
	 
	 }
	 

}
