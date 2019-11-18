package algoritmoFloydWarshall;

import java.util.ArrayList;
import java.util.Arrays;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

public class FloydWarshall {

	/*Variables*/
	private DirectedGraph graph;
	private Double[][] distance;
	private Double[][] path;
	private final int N;
	
	/*Constructors*/
	public FloydWarshall(DirectedGraph g) {
		graph = g;
		N = graph.getOrder();
		distance = new Double[N][N];
		path = new Double[N][N];
	}
	/*Methods*/
	public Double getDist(int u, int v) {
		return hasNegCycle()==true ? null : distance[u][v]; 
	}
	
	public boolean hasNegCycle() {
		floydWarshallAlgorithm();
		for(int v=0;v<N;v++) 
			if(distance[v][v]<0) return true;
		return false;
	}
	
	public Double[][] getDistance() {
		return distance;
	}
	
	private void floydWarshallAlgorithm() {
	/*	for(int u=0;u<N;u++) {
			for(int v=0;v<N;v++) {
				if(u==v) distance[u][v] = 0.0;
				else if(graph.hasEdge(new Edge(u,v))) {
					for(Edge uv: graph.getOutEdges(u)) {
						if(uv.getHead()==v) distance[u][v] = (double) uv.getWeight();
					}
				}else distance[u][v] = Double.POSITIVE_INFINITY;
			}
		}*/
		
	   for(int i = 0; i<N; i++) {
	            Arrays.fill(distance[i], Double.POSITIVE_INFINITY);
	            path[i][i] = (double)i;
	            distance[i][i] = 0.0;
	            for(Edge e: graph.getOutEdges(i)) {
	                distance[i][e.getHead()] = (double)e.getWeight();
	                path[i][e.getHead()] = (double)i;
	            }
	       }
		
		for(int k=1;k<N;k++) {
			for(int u=0;u<N;u++) {
				for(int v=0;v<N;v++) { 
					if(distance[u][v] > distance[u][k]+distance[k][v]) path[u][v] = (double) k;
					distance[u][v]= Math.min(distance[u][v], distance[u][k] + distance[k][v]);
				}
			}
		}
	}
	

    public ArrayList<Integer> getMinimumPath(int u, int v){
    	if(hasNegCycle()) return null;
        ArrayList<Integer> path = new ArrayList<>();
        double last = this.path[u][v];
        do {
            path.add((int)last);
            last = this.path[u][(int)last];
        }while (last!=u);
        return path;
    }
	
	
}
