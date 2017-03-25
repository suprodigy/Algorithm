package collatz;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static final int MAX = 1000000;
	public static final int INF = 123456789;
	public static final int[] d = new int[MAX+1];
	public static final int[] ans = new int[64];
	
	public static BigInteger pow(int k) {
		BigInteger ret = new BigInteger("1");
		
		for (int i=0; i<k; i++) {
			ret = ret.multiply(new BigInteger("2"));
		}
		
		return ret;
	}
	
	public static int go(int n) {
		if (n == 1) {
			return 0;
		} else if (n > MAX) {
			return INF;
		}
		
		if (d[n] != -1) {
			return d[n];
		}
		
		if (n % 2 == 0) {
			d[n] = go(n/2) + 1;
		} else {
			d[n] = go(3 * n + 1) + 1;
		}
		
		return d[n];
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		Arrays.fill(d, -1);
		Arrays.fill(ans, -1);
		d[1] = 0;
		for (int i=1; i<=MAX; i++) {
			go(i);
		}
		
		for (int i=1; i<=MAX; i++) {
			if (d[i] < 64 && ans[d[i]] == -1) {
				ans[d[i]] = i;
			}
		}
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int K = sc.nextInt();	
			
			System.out.println("Case #" + test_case);
			System.out.println(ans[K] + " " + pow(K));
		}
	}
}
