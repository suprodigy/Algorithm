package proctor;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] a = new int[N];
		for (int i=0; i<N; i++) {
			a[i] = sc.nextInt();
		}
		
		int B = sc.nextInt(), C = sc.nextInt();
		
		long ans = 0;
		for (int i=0; i<N; i++) {
			int temp = a[i] - B; ans++;
			if (temp > 0) {
				ans += Math.ceil((double)temp / C);
			}
		}
		System.out.println(ans);
	}
}
