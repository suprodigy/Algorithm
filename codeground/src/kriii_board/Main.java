package kriii_board;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long[] d = new long[N+1];
		
		for (int i=1; i<=N; i++) {
			d[i] = d[i-1] + 1;
			for (int j=3; i-j>= 0; j++) {
				d[i] = Math.max(d[i], d[i-j] * (j-1));
			}
		}
		
		System.out.println(d[N]);
	}
}
