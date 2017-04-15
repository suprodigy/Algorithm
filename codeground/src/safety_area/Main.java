package safety_area;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int N;
	public static int[][] a;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static void go(boolean[][] checked, int y, int x, int h) {
		for (int i=0; i<4; i++) {
			int ny = y + dy[i], nx = x + dx[i];
			
			if (!inRange(ny, nx) || checked[ny][nx] || a[ny][nx] <= h) {
				continue;
			}
			
			checked[ny][nx] = true;
			go(checked, ny, nx, h);
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		a = new int[N][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		int ans = 1;
		for (int h=1; h<=100; h++) {
			int cnt = 0;
			boolean[][] checked = new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (a[i][j] <= h) {
						continue;
					}
					
					if (!checked[i][j]) {
						checked[i][j] = true;
						go(checked, i, j, h);
						cnt++;
					}
				}
			}
			
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);
	}
}
