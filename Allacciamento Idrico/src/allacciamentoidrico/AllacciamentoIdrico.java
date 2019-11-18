package allacciamentoidrico;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;
import it.uniupo.algoTools.MinHeap;

public class AllacciamentoIdrico {

	/*Variabili*/
	private UndirectedGraph grafo;
	private UndirectedGraph alberoMST;
	private UndirectedGraph alberoDikstra;
	private int[][] costoScavo; //costoScavo[i][j] costo dello scavo fra i e j
	private boolean[] scoperto; //array che tiene traccia per ogni nodo se è stato scoperto oppure no
	private int[] distanzaDijkstra; //array che tiene traccia per ogni nodi della distanza minima dalla sorgente
	private int[] costoSingoloProprietario; //costo per ogni singolo proprietario
	private int[] nuovoCostoSingoloProprietario; //costo per ogni singolo proprietario
	private ArrayList<Integer> nodiVisitatiInOrdine;
	private MinHeap<Edge,Integer> heap; //struttura heap
	private ArrayList<Integer> coda; //struttura dati usata per la visita in ampiezza
	private int costoTubo; //costo del tubo al metro
	private int sorgente; //punto da cui partiranno i tubi
	private int costoComune; //costo del comune per realizzare gli scavi seguendo il proprio progetto
	private int costoComuneProp; //costo del comune per realizzare gli scavi seguendo il progetto dei proprietari
	
	/*Costruttori*/
	public AllacciamentoIdrico (UndirectedGraph mappa, int[][] costoScavo, int costoTubo, int puntoAllacciamento) {
		grafo = mappa;
		sorgente = puntoAllacciamento;
		this.costoScavo = costoScavo;
		this.costoTubo = costoTubo;
		scoperto = new boolean[grafo.getOrder()];
		distanzaDijkstra = new int[grafo.getOrder()];
		costoSingoloProprietario = new int[grafo.getOrder()];
		nuovoCostoSingoloProprietario = new int[grafo.getOrder()];
		nodiVisitatiInOrdine = new ArrayList<Integer>();
		heap = new MinHeap<Edge,Integer>();
		coda = new ArrayList<>();
		alberoMST = (UndirectedGraph) grafo.create();
		alberoDikstra = (UndirectedGraph) grafo.create();
		costoComune = 0;
		costoComuneProp = 0;
	}
	
	/*Metodi*/
	private void init() {
		for(int i=0;i<scoperto.length;i++) {
			scoperto[i] = false;
		}
		nodiVisitatiInOrdine.clear();
		coda.clear();
		heap = new MinHeap<Edge,Integer>();
		costoComuneProp = 0;
	}
	
	public UndirectedGraph progettoProprietari() { //restituisce il percorso che conviene ai vari proprietari (quello minimo di distanza -> Dijkstra)
		init();
		for(int i=0;i<grafo.getOrder();i++) {
			distanzaDijkstra[i] = -1;
			costoSingoloProprietario[i] = 0;
		}
		alberoDikstra = (UndirectedGraph) grafo.create();
		algoritmoDijkstra(sorgente);
		return alberoDikstra;
	}
	
	public UndirectedGraph progettoComune() { //restituisce il percorso che conviene al comune (MST di costo minimo -> Prim)
		init();
		alberoMST = (UndirectedGraph) grafo.create();
		costoComune = 0;
		algoritmoPrim(sorgente);
		return alberoMST;
	}
	
	public int getCostoComune() {
		return costoComune;
	}
	
	public int speseExtraComune() { //restituisce il costo extra che dovrebbe pagare il comune se al posto di seguire il proprio percorso facesse quello dei propretari //sum (costoDijkstra[i] - costoPrim[i])
		init();
		visitaDFS(sorgente,alberoDikstra);
		return costoComuneProp - costoComune;												
	}
	
	public int speseExtraProprietario (int villetta) { //restituisce il costo extra che dovrebbe pagare il prop della villetta se al posto di seguire il proprio percorso
		init();
		//visitaBFS(sorgente,alberoMST);
		visitaDFS2(sorgente,alberoMST);
		return nuovoCostoSingoloProprietario[villetta] -costoSingoloProprietario[villetta];													    //facesse quello del comune (costoPrim[i] - costoDijkstra[i])
		
	}
	
	public int[] getDistanzeDijkstra() {
		return distanzaDijkstra;
	}
	
	public int[][] getCostoScavo() {
		return costoScavo;
	}
	
	public int getCostoProprietario(int proprietario) {
		
		return costoSingoloProprietario[proprietario];
	}
	
	
	
	private void algoritmoDijkstra(int sorgente) {
		scoperto[sorgente] = true;
		distanzaDijkstra[sorgente] = 0;
		nodiVisitatiInOrdine.add(sorgente);
		costoScavo[sorgente][sorgente] = 0;
		for(Edge sv: grafo.getOutEdges(sorgente)) heap.add(sv, distanzaDijkstra[sorgente] + sv.getWeight());
		while(!heap.isEmpty()) {
			Edge uv = heap.extractMin();
			Integer v = uv.getHead();
			Integer u = uv.getTail();
			if(!scoperto[v]) {
				scoperto[v] = true;
				distanzaDijkstra[v] = distanzaDijkstra[u] + uv.getWeight();
				costoSingoloProprietario[v] = distanzaDijkstra[v]*costoTubo;
				nodiVisitatiInOrdine.add(v);
				alberoDikstra.addEdge(uv);
				for(Edge vw: grafo.getOutEdges(v)) {
					Integer w = vw.getHead();
					if(!scoperto[w]) heap.add(vw, distanzaDijkstra[vw.getTail()] + vw.getWeight());
				}
			}
		}
	}
	
	private void algoritmoPrim(int sorgente) {
		scoperto[sorgente] = true;
		nodiVisitatiInOrdine.add(sorgente);
		for(Edge su: grafo.getOutEdges(sorgente)) heap.add(su, costoScavo[su.getTail()][su.getHead()]);
		while(!heap.isEmpty()) {
			Edge uv = heap.extractMin();
			Integer v = uv.getHead();
			Integer u = uv.getTail();
			if(!scoperto[v]) {
				scoperto[v] = true;
				alberoMST.addEdge(uv);
				costoComune += costoScavo[u][v];
				nodiVisitatiInOrdine.add(v);
				for(Edge vw: grafo.getOutEdges(v)) {
					Integer w = vw.getHead();
					if(!scoperto[w]) heap.add(vw, costoScavo[vw.getTail()][vw.getHead()]);
				}
			}
		}
	}
	
	private void visitaDFS(int sorgente,UndirectedGraph grafo) {
		scoperto[sorgente] = true;
		for(Edge sv : grafo.getOutEdges(sorgente)) {
			Integer v = sv.getHead();
			if(!scoperto[v]) {
				costoComuneProp += costoScavo[sorgente][v];
				visitaDFS(v,grafo);
			}
		}
	}
	
		/*private void visitaBFS(int sorgente,UndirectedGraph grafo) {
			coda.add(sorgente);
			scoperto[sorgente] = true;
			nuovoCostoSingoloProprietario[sorgente] = 0;
			nodiVisitatiInOrdine.add(sorgente);
			while(!coda.isEmpty()) {
				Integer u = coda.remove(0);
				for (Edge uv: grafo.getOutEdges(u)) {
					Integer v = uv.getHead();
					if(scoperto[v]==false) {
						coda.add(v);
						scoperto[v]= true;
						nodiVisitatiInOrdine.add(v);
						nuovoCostoSingoloProprietario[v] = nuovoCostoSingoloProprietario[u] + uv.getWeight() * costoTubo;
				}
			}
		}
		
	}*/
	
	
	private void visitaDFS2(int sorgente,UndirectedGraph grafo) {
		scoperto[sorgente] = true;
		for(Edge sv : grafo.getOutEdges(sorgente)) {
			Integer v = sv.getHead();
			if(!scoperto[v]) {
				nuovoCostoSingoloProprietario[v] += nuovoCostoSingoloProprietario[sv.getTail()] + sv.getWeight() * costoTubo;
				visitaDFS2(v,grafo);
			}
		}
	}
	
	
}
