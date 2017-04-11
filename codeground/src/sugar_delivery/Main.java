package sugar_delivery;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static final int INF = 123456789;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] d = new int[N+1];
		Arrays.fill(d, -1);
		d[0] = 0;
		
		for (int i=3; i<=N; i++) {
			int temp = INF;
			
			if (d[i-3] != -1) {
				temp = Math.min(temp, d[i-3] + 1);
			}
			
			if (i>=5 && d[i-5] != -1) {
				temp = Math.min(temp, d[i-5] + 1);
			}
			
			if (temp != INF) {
				d[i] = temp;
			}
		}
		
		System.out.println(d[N]);
	}
}
