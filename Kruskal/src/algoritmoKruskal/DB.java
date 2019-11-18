package algoritmoKruskal;

import it.uniupo.algoTools.MaxHeap;

public class DB {
	
	/*Variables*/
	private int[] dim; //dim[i] is the dimension of database i
	private double[] time; //time[i] is the time for the recostruction of DB i
	private double[] dose;
	private int[] qnt;
	private double maxValue;
	private MaxHeap<Integer,Double> maxHeap;
	private final int NDB;
	
	/*Constructors*/
	public DB(int[]dim, double[] time) {
		this.dim = dim;
		this.time = time;
		NDB = dim.length;
		dose = new double[NDB];
		qnt = new int[NDB];
		maxHeap = new MaxHeap<Integer,Double>();
		maxValue = 0;
	}
	/*Methods*/
	public int[] memorize(int memSpace) throws IllegalArgumentException {
		if(memSpace < 0) throw new IllegalArgumentException("Valore memspace non valido");
		algoFracKnapsack(memSpace);
		return qnt;
	}
	
	public double timeSaved(int memSpace) throws IllegalArgumentException {
		if(memSpace < 0) throw new IllegalArgumentException();
		algoFracKnapsack(memSpace);
		return maxValue;
	}
	
	private void algoFracKnapsack(int capacity) {
		for(int i = 0; i < dim.length; i++) {
			qnt[i] = 0;
			dose[i] = 0;
			maxHeap.add(i, time[i] / dim[i]);
		}
		int freeSpace = capacity;
		while(freeSpace > 0 && !maxHeap.isEmpty()) {
			Integer tmp = maxHeap.extractMax();
			if(freeSpace > dim[tmp]) {
				dose[tmp] = 1;
				qnt[tmp] = dim[tmp];
				maxValue += time[tmp];
				
				freeSpace -= dim[tmp];
			}
			else {
				dose[tmp] = (double) freeSpace / (double) dim[tmp];
				qnt[tmp] = freeSpace;
				
				maxValue += (double) time[tmp] * dose[tmp];
				freeSpace = 0;
			}
		}
	}
}
