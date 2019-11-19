package visita.dfs;

import java.util.ArrayList;


import it.uniupo.graphLib.GraphInterface;

public class DFS {
	
	/*Variabili d' istanza*/
	private GraphInterface grafo; //per memorizzare il grafo su cui si lavora
	private boolean[] scoperto; //per memorizzare i nodi scoperti: scoperti[2]=true se il nodo 2 e' stato scoperto
	private ArrayList<Integer> nodiVisitatiInOrdine; //elenco dei nodi nell'ordine in cui sono stati visitati
	private ArrayList<Integer> ordineTopologico;
	private int[] distanza; //distanza[v] = distanza del nodo v dalla sorgente
	private int[] padre; //tiene traccia del padre di ogni nodo
	private int[] componentiConnesse; //array che mi indica per ogni nodo in quale componente connesse fa parte
	private GraphInterface alberoDFS; //struttura dati che mi tiene traccia dell' albero DFS generato dalla visita
	private boolean ciclo; //variabile booleana che mi indica la presenza di un ciclo
	private boolean[] terminato; //tiene traccia dei nodi di cui è terminata la visita
	private int ultimoNodoVisitato;  //tiene traccia del nodo che mi ha permesso di scovare il ciclo
	private int nodoIndietro; //nodo a cui non terminato a cui punta ultimoNodoVisitato
	private int contatore; //contatore che tiene traccia del numero di componenti connesse
	
	
	
	/*Costruttori*/
	public DFS (GraphInterface g) {
		grafo = g;
		scoperto = new boolean[g.getOrder()];
		terminato = new boolean[g.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		ordineTopologico = new ArrayList<Integer>();
		distanza = new int[g.getOrder()];
		padre = new int[g.getOrder()];
		componentiConnesse = new int[g.getOrder()];
		ciclo = false;
		ultimoNodoVisitato = -1;
		nodoIndietro = -1;
		contatore = 0;
	}
	
	/*Metodi*/
	
	private void init() {
		for(int i=0;i<distanza.length;i++) {
			distanza[i] = -1;
			scoperto[i] = false;
			terminato[i] = false;
			padre[i]=-1;
			componentiConnesse[i]=-1;
		}
		nodiVisitatiInOrdine.clear();
		ordineTopologico.clear();
		alberoDFS = grafo.create();
		ultimoNodoVisitato = -1;
		nodoIndietro = -1;
		ciclo = false;
		contatore = 0;
	}
	
	/*private void visitaDFS (int sorgente) {
		scoperto[sorgente] = true;
		for(Integer v: grafo.getNeighbors(sorgente)) {
			if(scoperto[v]==false) {
				alberoDFS.addEdge(sorgente, v);
				visitaDFS(v);
			}
		}
		nodiVisitatiInOrdine.add(sorgente);
		
	}*/
	
	private void visitaDFS (int sorgente) {
		scoperto[sorgente] = true;
		componentiConnesse[sorgente]=contatore;
		for(Integer v: grafo.getNeighbors(sorgente)) {
			if(scoperto[v]==false) {
				alberoDFS.addEdge(sorgente, v);
				padre[v]=sorgente;
				visitaDFS(v);
			}else if (scoperto[v] && !terminato[v]) {
				ciclo = true;
				ultimoNodoVisitato = sorgente;
				nodoIndietro = v;
			}
		}
		nodiVisitatiInOrdine.add(sorgente);
		terminato[sorgente] = true;
		ordineTopologico.add(0,sorgente);
	}
	
	private void visitaDFScompleta () {
		init();
		for(int i=0;i<scoperto.length;i++) {
			if(scoperto[i]==false) {
				visitaDFS(i);
				contatore++;
			}
		}
	}
	
	public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente) {
		init();
		visitaDFS(sorgente);
		return nodiVisitatiInOrdine;
	}
	
	public GraphInterface getDFStree(int sorgente) {
		init();
		visitaDFS(sorgente);
		return alberoDFS;
	}
	
	public GraphInterface getForest() {
		visitaDFScompleta();
		return alberoDFS;
	}
	
	public int getContatore() {
		visitaDFScompleta();
		return contatore;
	}
	
	public boolean hasDirCycle() {
		visitaDFScompleta();
		return ciclo;
	}
	
	public ArrayList<Integer> getDirCycle(){
		init();
		for(int i=0;i<grafo.getOrder();i++) {
			if(!scoperto[i]) {
				visitaDFS(i);
			}
			if(ciclo) return costruttoreCiclo();
		}
		return null;
	}
	
	/*private ArrayList<Integer> costruttoreCiclo(){
		int inizio = ultimoNodoVisitato;
		ArrayList<Integer> nodiCheFormanoCiclo = new ArrayList<>();
		nodiCheFormanoCiclo.add(inizio);
		while(inizio != nodoIndietro) {
			inizio = padre[inizio];
			nodiCheFormanoCiclo.add(padre[inizio]);
		}
		return nodiCheFormanoCiclo;
	}*/
	
	private ArrayList<Integer> costruttoreCiclo(){
		int inizio = ultimoNodoVisitato;
		ArrayList<Integer> nodiCheFormanoCiclo = new ArrayList<>();
		nodiCheFormanoCiclo.add(inizio);
		while(inizio != nodoIndietro) {
			inizio = padre[inizio];
			nodiCheFormanoCiclo.add(inizio);
		}
		return nodiCheFormanoCiclo;
	}
	
	public ArrayList<ArrayList<Integer>> getConnectedComponents() {
		init();
		visitaDFScompleta();
		ArrayList<ArrayList<Integer>> cc = new ArrayList<>();
		for(int i=0;i<contatore;i++)
		{
			ArrayList<Integer> lista = new ArrayList<>();
			for(int k=0;k<componentiConnesse.length;k++)
			{
				if(componentiConnesse[k]==i) lista.add(k);
			}
			cc.add(lista);
		}
		return cc;
	}
	
	public ArrayList<Integer> topologicalOrder() {
		visitaDFScompleta();
		return ordineTopologico;
		
	}
	
	
	//Per trovare ultimo ciclo scommentarare queasta porzione di codice e commentare altra funzione
	//getDirCycle e relativo costruttoreCiclo
	/*public ArrayList<Integer> getDirCycle() {
		visitaDFScompleta();
		ArrayList<Integer> nodiDelCiclo = new ArrayList<Integer>();
		while(ultimoNodoVisitato!=nodoIndietro && ciclo) {
			nodiDelCiclo.add(ultimoNodoVisitato);
			ultimoNodoVisitato = padre[ultimoNodoVisitato];
		}
		if(ultimoNodoVisitato!=-1 && ultimoNodoVisitato==nodoIndietro) nodiDelCiclo.add(ultimoNodoVisitato);
		return nodiDelCiclo.size()==0 ? null : nodiDelCiclo;
	}*/




}
