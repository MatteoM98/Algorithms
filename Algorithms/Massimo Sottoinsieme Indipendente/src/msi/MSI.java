package msi;

import java.util.ArrayList;

public class MSI {

	/*Variables*/
	private int[] weight; //weight of node i
	private int[] array;  //solution for problem i
	private ArrayList<Integer> solution; //node included in solution 
	
	/*Costrunctors*/
	public MSI(int[] w) {
		weight = w;
		array = new int[w.length];
		solution = new ArrayList<Integer>();
	}
	
	/*Methods*/
	public int getMaxVal() {
		array[0] = weight[0];
		array[1] = Math.max(array[0],weight[1]);
		for(int i=2; i<weight.length; i++)
			array[i] = max(array[i-1], array[i-2] + weight[i]);
		return array[weight.length-1];
	}
	
	
	private int max(int a, int b) {
		return (a>=b) ? a : b;
	}
	
	public ArrayList<Integer> getOptSol() {
		int i = weight.length-1;
		while(i>1) {
			if(array[i-1] > array[i-2] + weight[i]) i--;
			else {
				solution.add(i);
				i-=2;
			}
				
		}
		if(i==1) solution.add(1);
		return solution;
	}
	
	/*public ArrayList<Integer> getOptSol() { //in this solution the array "array" must been already implemented with all solutions, calculated in getMaxVal function
		int i = weight.length-1;
		while(i>0) {
			if(array[i] > array[i-1]) {
				solution.add(i);
				i -= 2;
			}else i--;
		}
		return solution;
	}*/
	
	
}
