package downhill_road;

import java.util.Scanner;

public class Main {
	public static int N, M;
	public static int[][] a;
	public static int[][] d;
	public static int[] dy = new int[]{1, -1, 0, 0};
	public static int[] dx = new int[]{0, 0, 1, -1};
	
	public static int go(int y, int x) {
		if (y == (N-1) && x == (M-1)) {
			return 1;
		} 
//		System.out.println(y + ", " + x);
		
		int ret = d[y][x];
		if (ret != -1) {
			return ret;
		}
		ret = 0;
		for (int i=0; i<4; i++) {
			int ny = y + dy[i], nx = x + dx[i];
			if (inRange(ny, nx) && a[y][x] > a[ny][nx]) {
				ret += go(ny, nx);
			}
		}
		d[y][x] = ret;
		return ret;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new int[N][M];
		d = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				a[i][j] = sc.nextInt();
				d[i][j] = -1;
			}
		}
		
		System.out.println(go(0, 0));
	}
}
