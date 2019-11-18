package algoritmoKruskal;
import it.uniupo.algoTools.MaxHeap;

public class FracKnapsack {

	/*Variables*/
	private double capacity;
	private double[] volume; //volume of i object
	private double[] value; // value of i object
	private double[] dose; // dose of i object 
	private double maxValue;
	private final int OBJECTS; //number of objects
	private MaxHeap<Integer,Double> maxHeap;
	
	/*Constructors*/
	public FracKnapsack(double capacity, double[] volume, double[] value) {
		this.capacity = capacity;
		this.volume = volume;
		this.value = value;
		maxValue = 0;
		OBJECTS = volume.length;
		maxHeap = new MaxHeap<Integer,Double>();
		dose = new double[OBJECTS];
	}
	
	/*Methods*/
	private void init() {
		maxValue = 0;
		maxHeap = new MaxHeap<Integer,Double>();
		dose = new double[OBJECTS];
	}
	
	public double maxVal() { //returns the maximum value calcolated from algorithm
		init();
		FractionalKnapsack();
		return maxValue;
	}
	
	public double dose(int i) { //returns dose[i]
		init();
		FractionalKnapsack();
		return dose[i];
	}
	
	private void FractionalKnapsack() {
		for(int i=0;i<OBJECTS;i++) {
			maxHeap.add(i, value[i]/volume[i]);
			dose[i] = 0;
		}
		double freeSpace = capacity;
		while(freeSpace > 0) {
			Integer obj = maxHeap.extractMax();
			if(volume[obj] <= freeSpace) {
				dose[obj] = 1;
				maxValue += value[obj];
				freeSpace -= volume[obj];
			} else {
				dose[obj] = freeSpace / volume[obj];
				maxValue += dose[obj] * value[obj];
				freeSpace = 0;
			}
		}
	}
}
