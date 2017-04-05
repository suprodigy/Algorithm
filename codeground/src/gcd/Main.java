package gcd;

import java.util.Scanner;

public class Main {
	public static int gcd(int x, int y) {
		if (x < y) {
			return gcd(y, x);
		}
		
		if (y == 0) {
			return x;
		}
		
		return gcd(y, x % y);
	}
	
	public static void main (String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] a = new int[N];
		int[][] d = new int[2][N];
		
		// 순방향으로 gcd 구하기
		a[0] = d[0][0] = sc.nextInt();
		for (int i=1; i<N; i++) {
			a[i] = sc.nextInt();
			d[0][i] = gcd(d[0][i-1], a[i]);
		}
		
		// 역방향으로 gcd 구하기
		d[1][N-1] = a[N-1];
		for (int i=N-2; i>=0; i--) {
			d[1][i] = gcd(a[i], d[1][i+1]);
		}
		
		int ans = -1, except = -1;
		for (int i=0; i<N; i++) {
			int temp = 0;
			if (i == 0) {
				temp = d[1][i+1];
			} else if (i == N-1) {
				temp = d[0][i-1];
			} else {
				temp = gcd(d[0][i-1], d[1][i+1]);
			}
			
			if (((a[i] % temp) != 0) && (ans < temp)) {
				ans = temp; except = a[i];
			}
		}
		
		if (ans == -1) {
			System.out.println(-1);
		} else {
			System.out.println(ans + " " + except);
		}
	}
}