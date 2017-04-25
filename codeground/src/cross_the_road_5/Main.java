package cross_the_road_5;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int N, K, B;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		B = sc.nextInt();
		
		int ans = 0;
		boolean[] broken = new boolean[N];
		for (int i=0; i<B; i++) {
			int now = sc.nextInt() - 1;
			broken[now] = true;
			
			if (now <= K-1) {
				ans++;
			}
		}
		
		int prev = ans;
		for (int i=1; i<=N-K; i++) {
			int to = i+K-1;
			int temp = prev;
			
			if (broken[i-1]) {
				temp--;
			}
			
			if (broken[to]) {
				temp++;
			}
			
			prev = temp;
			ans = Math.min(ans, temp);
		}
		
		System.out.println(ans);
	}
	
}
