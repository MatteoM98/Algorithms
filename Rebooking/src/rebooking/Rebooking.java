package rebooking;

import java.util.Arrays;

public class Rebooking {

	/*Variables*/
	private int capacity;
	private int[] groupSizes;
	private int[] groupCosts;
	private int[][] solMatrix;
	
	/*Constructors*/
	public Rebooking(int availableSeats,int[] groupSizes, int[] groupCosts) {
		capacity = availableSeats;
		this.groupSizes = groupSizes;
		this.groupCosts = groupCosts;
	}
	
	/*Methods*/
	public int maxSaving() {
		if(capacity<0) return -1;
		if(groupSizes.length != groupCosts.length) return -1;
		solMatrix = new int[groupCosts.length+1][capacity+1];
		knapsackAlgorithm();
		return solMatrix[groupCosts.length][capacity];
	}
	
	private void knapsackAlgorithm() {
		Arrays.fill(solMatrix[0],0);
		for(int h=1; h<groupCosts.length+1; h++) {
			for(int k=0; k<=capacity; k++) {
				if(groupSizes[h-1]<=k) solMatrix[h][k] = Math.max(solMatrix[h-1][k],solMatrix[h-1][k-groupSizes[h-1]] + groupCosts[h-1]);
				else solMatrix[h][k] = solMatrix[h-1][k];
			}
		}
	}
	
}
