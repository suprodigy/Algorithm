package monthly_expense;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int N, M;
	public static int[] a;
	
	public static int solve(int lo, int hi) {
		if (lo >= hi) {
			return lo;
		}
		
		int mid = (lo + hi) / 2;
		if (canLowerExpense(mid)) {
			return solve(lo, mid);
		} else {
			return solve(mid+1, hi);
		}
	}
	
	public static boolean canLowerExpense(int withdraw) {
		int cnt = 0, now = 0;
		for (int i=0; i<N; i++) {
			now += withdraw;
			now -= a[i];
			while (++i < N && now >= a[i]) {
				now -= a[i];
			}
			
			i--;
			cnt++;
			now = 0;
		}
		
		if (cnt <= M) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new int[N];
		
		int lo = 0, hi = 0;
		for (int i=0; i<N; i++) {
			a[i] = sc.nextInt();
			lo = Math.max(lo, a[i]);
			hi += a[i];
		}
		
		System.out.println(solve(lo, hi));
	}
}
