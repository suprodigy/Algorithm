package freelancer;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt();
			
			int[][] a = new int[2][N+1];
			int[] d = new int[N+1];
			
			for (int i=0; i<2; i++) {
				for (int j=1; j<=N; j++) {
					a[i][j] = sc.nextInt();
				}
			}
			
			d[0] = 0;
			for (int i=1; i<=N; i++) {
				if (i==1) {
					d[i] = Math.max(a[0][i], a[1][i]);
				} else {
					d[i] = Math.max(d[i-1] + a[0][i], d[i-2] + a[1][i]);
				}
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(d[N]);
		}
	}
}
