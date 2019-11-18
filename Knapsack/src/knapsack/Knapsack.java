package knapsack;

import java.util.ArrayList;
import java.util.Arrays;

public class Knapsack {

	/*Variables*/
	 private int[] values;
	 private int[] volumes;
	 private int capacity;
	 private final int[][] solMatrix;

	 /*Constructors*/
     public Knapsack(int[] values, int[] volumes, int capacity) {
	        this.values = values;
	        this.volumes = volumes;
	        this.capacity = capacity;
	        this.solMatrix = new int[values.length+1][capacity+1];
     }
     
     /*Methods*/
     private void knapsackAlgorithm() {
    	 	Arrays.fill(solMatrix[0],0);
	        for(int h = 1; h<=values.length; h++) {
	            for(int k = 0; k<=capacity; k++){
	                if (volumes[h-1] <= k) solMatrix[h][k] = Math.max(solMatrix[h-1][k],solMatrix[h-1][k-volumes[h-1]]+ values[h-1]);
	                else solMatrix[h][k] = solMatrix[h-1][k];
	           }
	       }
	  }
     
     public int getMaxVal() {
    	 knapsackAlgorithm();
 		return solMatrix[values.length][capacity];
 	}
     
     public ArrayList<Integer> getOptSol() {
    	knapsackAlgorithm();
 		ArrayList<Integer> solution = new ArrayList<Integer>();
 		int n = values.length;
 		int c = capacity;
 		while(n>0 && c>0) {
 			if(solMatrix[n][c]> solMatrix[n-1][c]) {
 				solution.add(0,n-1);
 				n--;
 				c -= volumes[n];
 			}else n--;
 		}
 		return solution;
 	}
}
