package first_grader;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] a = new int[N+1];
		long[][] d = new long[21][N+1];
		for (int i=0; i<21; i++) {
			Arrays.fill(d[i], 0);
		}
		
		for (int i=1; i<=N; i++) {
			a[i] = sc.nextInt();
		}
		d[a[1]][1] = 1;
		
		for (int i=2; i<=N-1; i++) {
			int now = a[i];
			for (int j=0; j<21; j++) {
				if (inRange(j + now)) {
					d[j+now][i] += d[j][i-1];
				}
				
				if (inRange(j - now)) {
					d[j-now][i] += d[j][i-1];
				}
			}
		}
		
		System.out.println(d[a[N]][N-1]);
	}
	
	public static boolean inRange(int num) {
		return (0<=num && num <= 20);
	}
}
