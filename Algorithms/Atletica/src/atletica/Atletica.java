package atletica;

public class Atletica {

	/*Variables*/
	private int disciplinesNumber;
	
	/*Constructors*/
	public Atletica(int disciplinesNumber) {
		this.disciplinesNumber = disciplinesNumber;
	}
	
	/*Methods*/
	public int choose(int[] rendAtleta1, int[] rendAtleta2) throws IllegalArgumentException {
		if(rendAtleta1.length != disciplinesNumber || rendAtleta2.length != disciplinesNumber)
				throw new IllegalArgumentException("Error: array's dimension is different");
		int a = msiAlgorithm(rendAtleta1);
		int b = msiAlgorithm(rendAtleta2);
		if(a==b) return 0;
		return a>b ? 1 : 2;
	}
	
	private int msiAlgorithm(int[] numbers) {
		int[] array = new int[disciplinesNumber];
		array[0] = numbers[0];
		array[1] = Math.max(array[0], numbers[1]);
		for(int i=2;i<disciplinesNumber;i++) 
			array[i] = Math.max(array[i-1], array[i-2] + numbers[i]);
		return array[disciplinesNumber -1];
	}
}
