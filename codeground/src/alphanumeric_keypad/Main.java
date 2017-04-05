package alphanumeric_keypad;

import java.io.FileInputStream;
import java.util.Scanner;

class Int {
	public int val;
	
	public Int(int val) {
		this.val = val;
	}
}

class Tuple {
	int y, x, nth;
	
	public Tuple(int y, int x, int nth) {
		this.y = y;
		this.x = x;
		this.nth = nth;
	}
}

public class Main {
	public static int N, M, K;
	public static int[][] a;
	public static String to;
	public static int[] dy = new int[]{1, -1, 0, 0};
	public static int[] dx = new int[]{0, 0, 1, -1};
	public static Int[][][] d;
	
	public static int go(int y, int x, int nth) {
		if (nth == to.length()) {
			return 1;
		}
		
		Int ret = d[y][x][nth];
		if (ret.val != -1) {
			return ret.val;
		}
		
		ret.val = 0;
		for (int i=0; i<4; i++) {
			for (int j=1; j<=K; j++) {
				int ny = y + j * dy[i], nx = x + j * dx[i];
				if (inRange(ny, nx) && a[ny][nx] == to.charAt(nth)) {
					ret.val += go(ny, nx, nth + 1);
				}
			}
		}
		
		return ret.val;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
		a = new int[N][M];
		d = new Int[N][M][80];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				for (int k=0; k<80; k++) {
					d[i][j][k] = new Int(-1);
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				a[i][j] = str.charAt(j);
			}
		}
		to = sc.next();
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (a[i][j] == to.charAt(0)) {
					ans += go(i, j, 1);
				}
			}
		}
		
		System.out.println(ans);
	}
}
