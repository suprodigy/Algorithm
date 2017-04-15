package baegyangno_brake;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static final int INF = 123456789;
	
	public static int N, M;
	public static int[][] a;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new int[N+1][N+1];
		for (int i=0; i<N+1; i++) {
			Arrays.fill(a[i], INF);
		}
		
		for (int i=1; i<=N; i++) {
			a[i][i] = 0;
		}
		
		for (int i=0; i<M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int b = sc.nextInt();
			
			switch (b) {
			case 0:
				a[u][v] = 0;
				a[v][u] = 1;
				break;
			case 1:
				a[u][v] = 0;
				a[v][u] = 0;
				break;
			}
		}
		
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				if (i == k) continue;
				
				for (int j=1; j<=N; j++) {
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
				}
			}
		}
		
		int K = sc.nextInt();
		for (int i=0; i<K; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			System.out.println(a[s][e]);
		}
	}
}
