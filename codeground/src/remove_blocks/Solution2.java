package remove_blocks;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution2 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt();
			
			int[][] a = new int[2][N+2];
			a[0][0] = a[0][N+1] = 0;
			long total = 0;
			
			for (int i=1; i<=N; i++) {
				a[0][i] = sc.nextInt();
				total += a[0][i];
			}
			
			int cnt = 0;
			while(true) {
				int[] r = new int[N+2];
				
				a[(cnt+1)%2][0] = a[(cnt+1)%2][N+1] = 0;
				
				for (int i=1; i<=N; i++) {
					int temp = 0;
					
					if (a[cnt%2][i] != 0) {
						temp = 1;
						if (a[cnt%2][i-1] == 0 || a[cnt%2][i+1] == 0) {
							temp = a[cnt%2][i];
						} else {
							if (a[cnt%2][i] - a[cnt%2][i-1] >= 0) {
								temp = Math.max(temp, a[cnt%2][i] - a[cnt%2][i-1]);
							}
							
							if (a[cnt%2][i] - a[cnt%2][i+1] >= 0) {
								temp = Math.max(temp, a[cnt%2][i] - a[cnt%2][i+1]);
							}
						}
					}
					
					total -= temp;
					r[i] = temp;
					a[(cnt+1)%2][i] = a[cnt%2][i] - r[i];
				}
				
				cnt++;
				if (total == 0) {
					break;
				}
				
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(cnt);
		}
	}
}
