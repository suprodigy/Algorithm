package subarray;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt();
			int S = sc.nextInt();
			
			int[] a = new int[N];
			for (int i=0; i<N; i++) {
				a[i] = sc.nextInt();
			}
			
			int sum = 0, ans = N;
			Queue<Integer> q = new LinkedList<>();
			for (int i=0; i<N; i++) {
				q.offer(a[i]);
				sum += a[i];
				
				while (sum - q.peek() > S) {
					int num = q.poll();
					sum -= num;
					ans = Math.min(ans, q.size());
				}
			}
			
			if (ans == N && sum < S) {
				ans = 0;
			}
			
			System.out.println("#testcase" + test_case);
			System.out.println(ans);
		}
	}
}
