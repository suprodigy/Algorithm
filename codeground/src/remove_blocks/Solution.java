package remove_blocks;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt();
			
			int[] a = new int[N];
			for (int i=0; i<N; i++) {
				a[i] = sc.nextInt();
			}
			
			int[] d = new int[N];
			d[0] = 1;
			for (int i=1; i<N; i++) {
				d[i] = Math.min(a[i], d[i-1] + 1);
			}
			
			int max = 1;
			d[N-1] = 1;
			for (int i=N-2; i>=0; i--) {
				int temp = Math.min(a[i], d[i+1] + 1);
				d[i] = Math.min(d[i], temp);
				max = Math.max(max, d[i]);
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(max);
		}
	}
}
