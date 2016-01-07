package cachetest;

public class Transpose {
	public static int n = 6400;
	public static int [][] a;
	public static int [][] b;

	public static void main(String[] args) {
		a = new int [n][n];
		b = new int [n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = (int) Math.random() * 100;
			}
		}
		
		transpose1();
		for (int blocksize = 8; blocksize < 180; blocksize += 4) {
			transpose2(blocksize);
		}
	}
	public static void transpose1() {

		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				b[j][i] = a[i][j];
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Runtime in Milliseconds = " + (end - start));
	}
	
	public static void transpose2(int blocksize) {	
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < n; i += blocksize) {
			for (int j = 0; j < n; j+= blocksize) {
				for (int i1 = i; i1 < i + blocksize && i1 < n; i1++) {
					for (int j1 = j; j1 < j + blocksize && j1 < n; j1++) {
						b[j1][i1] = a[i1][j1];
					}
				}
			}
		}
		long end1 = System.currentTimeMillis();
		System.out.println("Block size = " + blocksize + ", " + "Runtime in Milliseconds = " + (end1 - start1));
	}
}
