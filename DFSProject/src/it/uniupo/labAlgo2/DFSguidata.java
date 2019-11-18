package it.uniupo.labAlgo2;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class DFSguidata {
	GraphInterface grafo;
	boolean[] scoperto;
	GraphInterface alberoDFS;
	ArrayList <Integer> nodiVisitatiInOrdine;
	
	public DFSguidata (GraphInterface grafoInput) {
		grafo = grafoInput;
		scoperto = new boolean[grafo.getOrder()];
		nodiVisitatiInOrdine = new ArrayList <Integer>(grafo.getOrder());
		alberoDFS = grafo.create();
	}
	
	
	private void init() {
		scoperto = new boolean[grafo.getOrder()];
		nodiVisitatiInOrdine = new ArrayList <Integer>(grafo.getOrder());
		alberoDFS = grafo.create();
	}

	
	private void visitaDFS(int sorgente) {
		 nodiVisitatiInOrdine.add(sorgente);
			scoperto[sorgente] = true;
			for(int i:grafo.getNeighbors(sorgente))
			{
				if(scoperto[i]==false)
				{
					alberoDFS.addEdge(sorgente,i);
					visitaDFS(i);
				}
			}
		
	}
	
	public GraphInterface getDFStree (int sorgente) {
		init();
		visitaDFS(sorgente);
		return alberoDFS; //decidete voi cosa restituisce
	}
	
	public ArrayList<Integer> getNodesInOrderPostVisit(int sorgente)
	{
		init();
		visitaDFS(sorgente);
		ArrayList <Integer> reverse = new ArrayList<Integer>(nodiVisitatiInOrdine.size());
		for(int i=nodiVisitatiInOrdine.size()-1;i>=0;i--) reverse.add(nodiVisitatiInOrdine.get(i));
		return reverse;
	}

}
