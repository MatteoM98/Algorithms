package kosaraju;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.GraphUtils;

public class Kosaraju {
	
	/*Variabili*/
	private DirectedGraph grafo;
	private GraphInterface alberoDFS;
	private boolean[] scoperto;
	private int[] scc; //array delle componenti fortemente connesse, indica per ogni nodo il numero della SCC alla quale appartiene
	private ArrayList<Integer> nodiVisitatiInOrdine; //iene traccia dell' ordine di fine visita
	private ArrayList<Integer> ordineTopologico; //tiene traccia dell' ordine topologico
	private int contatore;
	
	
	/*Costruttore*/
	public Kosaraju (DirectedGraph g) {
		grafo = g;
		scoperto = new boolean[g.getOrder()];
		scc = new int[g.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		ordineTopologico = new ArrayList<Integer>();
		contatore = 0;
	}
	
	
	/*Metodi*/
	
	public void init() {
		for(int i=0;i<scoperto.length;i++) {
			scoperto[i] = false;
			scc[i] = -1;
		}
		nodiVisitatiInOrdine.clear();
		//ordineTopologico.clear();
		alberoDFS = grafo.create();
		contatore = 0;
		
	}
	
	public ArrayList<Integer> postVisitList() {
		visitaDFScompleta();
		return nodiVisitatiInOrdine;
	}
	
	public int[] getSCC() {
		algoritmoDiKosaraju();
		return scc;
	}
	
	private void visitaDFS(int sorgente) {
		scoperto[sorgente] = true;
		for(Integer v: grafo.getNeighbors(sorgente)) {
			if(scoperto[v]==false)
			{
				alberoDFS.addEdge(sorgente,v);
				visitaDFS(v);
			}
		}
		nodiVisitatiInOrdine.add(sorgente);
		ordineTopologico.add(0,sorgente);
	}
	
	private void visitaDFSTrasposto(int sorgente,DirectedGraph grafo) {
		scoperto[sorgente] = true;
		scc[sorgente] = contatore;
		for(Integer v: grafo.getNeighbors(sorgente)) {
			if(scoperto[v]==false)
			{
				alberoDFS.addEdge(sorgente,v);
				visitaDFSTrasposto(v,grafo);
			}
		}
		nodiVisitatiInOrdine.add(sorgente);
	}
	
	private void visitaDFScompletaOT(DirectedGraph grafo) {
		init();
		for(Integer i: ordineTopologico) {
			if(scoperto[i]==false) {
				visitaDFSTrasposto(i,grafo);
				contatore++;
			}
		}
	}
	
	private void visitaDFScompleta() {
		init();
		for(int i=0;i<scoperto.length;i++) {
			if(scoperto[i]==false) {
				visitaDFS(i);
			}
		}
	}
	
	public void algoritmoDiKosaraju() {
		//effettuo una visita del grafo
		visitaDFScompleta();
		
		//effettuo la trasposizione del grafo
		DirectedGraph trasposto = GraphUtils.reverseGraph(grafo);
		
		//visita DFS sul grafo trasposto
		visitaDFScompletaOT(trasposto);
	}
	
}
