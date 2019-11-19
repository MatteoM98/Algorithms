package algoritmoBellmanFord;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;

public class BellmanFordDAG {

	/*Variables*/
	private DirectedGraph graph;
	private DirectedGraph transposed;
	private int source;
	private Double[] distance;
	private int[] father;
	private boolean[] discovered;
	private boolean[] end;
	private boolean cycle;
	private ArrayList<Integer> topologicalOrder;
	private ArrayList<Integer> path;
	private ArrayList<Integer> visitedNodes;
	private final int N;
	
	
	/*Constructors*/
	public BellmanFordDAG(DirectedGraph g, int source) {
		graph = g;
		this.source = source;
		transposed = GraphUtils.reverseGraph(graph);
		N = graph.getOrder();
		distance = new Double[N];
		father = new int[N];
		discovered = new boolean[N];
		end = new boolean[N];
		topologicalOrder = new ArrayList<Integer>();
		path = new ArrayList<Integer>();
		visitedNodes = new ArrayList<Integer>();
		cycle = false;
	}
	
	/*Methods*/
	private void init() {
		distance = new Double[N];
		father = new int[N];
		discovered = new boolean[N];
		end = new boolean[N];
		topologicalOrder.clear();
		path = new ArrayList<Integer>();
		visitedNodes.clear();
		cycle = false;
	}
	
	private void DFS(int source) {
		discovered[source] = true;
		for(Edge sv: graph.getOutEdges(source)) {
			Integer v = sv.getHead();
			if(!discovered[v]) DFS(v);
			else if(discovered[v] && !end[v]) cycle = true;
		}
		visitedNodes.add(source);
		topologicalOrder.add(0,source);
		end[source] = true;
	}
	
	private void visitaDFSCompleta() {
		init();
		for(int i=0;i<graph.getOrder();i++) {
			if(!discovered[i]) {
				DFS(i);
			}
		}
	}
	
	private void BellmanFordDAGAlgorithm() {
		visitaDFSCompleta();
		for(int v=0;v<N;v++) {
			if(v==source) distance[v] = 0.0;
			else distance[v] = Double.POSITIVE_INFINITY;
			father[v] = -1;
		}
		
		for(Integer v: topologicalOrder) {
			Edge min = null;
			int m = 1;
			for(Edge wv: transposed.getOutEdges(v)) {
				if(m==1) min = wv;
				else if(distance[wv.getHead()] + wv.getWeight() < distance[min.getHead()] + min.getWeight()) min = wv;
				m++;
			}
			if(min!=null) {
				distance[v] = distance[min.getHead()] + min.getWeight();
				father[v] = min.getHead();
			}
		}
		
	}
	
	public boolean hasCycle() {
		BellmanFordDAGAlgorithm();
		return cycle;
	}
	
	public Double getDist(int u) {
		return hasCycle()==true ? Double.NEGATIVE_INFINITY : distance[u];
	}
	
	public ArrayList<Integer> getPath (int u) {
		if(hasCycle()) return null;
		if(father[u]==-1) {
			path.add(0,-1);;
			return path;
		}
		int last = u;
		path.add(0,last);
		while(last!=source) {
			last = father[last];
			path.add(0,last);
		}
		return path;
	}
}


