package lcs;

public class LCS {

	/*Varaibles*/
	private final String STRING_X;
	private final String STRING_Y;
	private final int N;
	private final int M;
	private int[][] lcs;
	
	/*Constructors*/
	public LCS(String x, String y) {
		STRING_X = x;
		STRING_Y = y;
		N = x.length();
		M = y.length();
		lcs = new int[N+1][M+1];
	}
	/*Methods*/
	private void init() {
		lcs = new int[N+1][M+1];
	}
	
	private void lcsAlgorithm() {
		for(int j=0;j<=M;j++) lcs[0][j] = 0;
		for(int i=0;i<=N;i++) lcs[i][0] = 0;
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(STRING_X.charAt(i-1) == STRING_Y.charAt(j-1)) lcs[i][j] = 1 + lcs[i-1][j-1];
				else lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);	
			}
		}
	}
	
	public int[][] getLCS() {
		init();
		lcsAlgorithm();
		return lcs;
	}
	
	public int getLCSlenght() {
		init();
		lcsAlgorithm();
		return lcs[N][M];
	}
}
