package algoritmoBellmanFord;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;

public class BellmanFord {
	
	/*Variables*/
	private DirectedGraph graph;
	private DirectedGraph transposed; //transposed graph of 'graph'
	private Double[][] distance;
	private ArrayList<Integer> path;
	private int[] father;
	private int source;
	private boolean cycle;
	private final int N;
	
	
	/*Constructors*/
	public BellmanFord(DirectedGraph g, int source) {
		graph = g;
		transposed = GraphUtils.reverseGraph(graph);
		path = new ArrayList<Integer>();
		this.source = source;
		N = g.getOrder();
		distance = new Double[N+1][N];
		father = new int[N];
		cycle = false;

	}
	
	private void BellmanFordAlgorithm() {
			
			for(int v=0;v<N;v++) {
				if(v==source) distance[0][v] = 0.0;
				else distance[0][v] = Double.POSITIVE_INFINITY;
				father[v] = -1;
			}
			
			for(int i=1;i<=N;i++) {
				Edge min;
				int m;
				for(int v=0;v<N;v++) {
					min = null;
					m = 1;
					for(Edge wv: transposed.getOutEdges(v)) {
						if(m==1) min = wv;
						else if(wv.getHead() + wv.getWeight() < min.getHead() + min.getWeight()) min = wv;
						m++;
					}
				    if(min == null) distance[i][v] = distance[i-1][v];
				    else {
				    	distance[i][v] = Math.min(distance[i-1][v], distance[i-1][min.getHead()]+min.getWeight());
				    	if(distance[i][v]==distance[i-1][min.getHead()]+min.getWeight()) {
						father[v] = min.getHead();
					}
				}
			}
		}
			for(int v = 0; v<N; v++) 
				if(distance[N][v] < distance[N-1][v]) cycle = true;
	}
	
	
	public Double getDist(int u) {
		BellmanFordAlgorithm();
		return (cycle==true) ? Double.NEGATIVE_INFINITY : distance[N][u];
	}
	
	public boolean hasNegCycle() {
		BellmanFordAlgorithm();
		return cycle;
	}
	
	public ArrayList<Integer> getPath(int u) {
		if(hasNegCycle()) return null;
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
