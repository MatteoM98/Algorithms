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
		//costruire un oggetto per ogni variabile di istanza
	}
	
	private void init(){
		//qui inizializzate correttamente le variabili di istanza (tranne il grafo)
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
	     
	     /*********
			 * questo metodo non e' completo,
			 * dovete scrivere la visita BFS
			 */
	       
	    }
	 
	 public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente){
		 												 //restituisce i nodi del grafo nell'ordine in cui vengono scoperti
		/*********
		 * questo metodo non e' completo,
		 * dovete scrivere il codice giusto e restituire
		 * l'oggetto giusto
		 */
		 return null; 
	 }
	 
	 
	 public int[] getDistance(int sorgente) { //resituisce le distanza di ciascun nodo da sorg
		 /*********
			 * questo metodo non e' completo,
			 * dovete scrivere il codice giusto e restituire
			 * l'oggetto giusto
			 */
		 return null;
	 
	 }
	 

}
