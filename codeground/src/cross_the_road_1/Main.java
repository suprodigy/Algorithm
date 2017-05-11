package cross_the_road_1;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int[] cow;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		cow = new int[11];
		Arrays.fill(cow,  -1);
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			int now = sc.nextInt();
			int pos = sc.nextInt();
			
			if (cow[now] == -1) {
				cow[now] = pos;
			} else if (pos == 0 && cow[now] == 1) {
				ans++;
				cow[now] = pos;
			} else if (pos == 1 && cow[now] == 0) {
				ans++;
				cow[now] = pos;
			}
		}
		System.out.println(ans);
	}
}
