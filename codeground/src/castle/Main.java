package castle;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int N, M;
	public static int[][] a;
	public static int[][] d;
	public static int[] dy = new int[]{0, -1, 0, 1};
	public static int[] dx = new int[]{-1, 0, 1, 0};
	
	public static void go(int y, int x, int cnt) {
		int ret = d[y][x];
		if (ret != -1) {
			return;
		}
		d[y][x] = cnt;
		
		for (int i=0; i<4; i++) {
			if ((a[y][x] & (1<<i)) != 0) {
				continue;
			}
			
			int ny = y + dy[i], nx = x + dx[i];
			if (!inRange(ny, nx)) {
				continue;
			}
			go(ny, nx, cnt);
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt(); N = sc.nextInt();
		a = new int[N][M];
		d = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				a[i][j] = sc.nextInt();
				d[i][j] = -1;
			}
		}
		
		int cnt = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (d[i][j] == -1) {
					go(i, j, cnt);
					cnt++;
				}
			}
		}
		
		int[] size = new int[cnt];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				size[d[i][j]]++;
			}
		}
		
		int maxSize = 0;
		for (int i=1; i<cnt; i++) {
			maxSize = Math.max(maxSize, size[i]);
		}
		
		int maxMerge = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				for (int k=0; k<4; k++) {
					int ny = i + dy[k], nx = j + dx[k];
					if (!inRange(ny, nx)) {
						continue;
					}
					if (d[ny][nx] != d[i][j]) {
						maxMerge = Math.max(maxMerge, size[d[ny][nx]] + size[d[i][j]]);
					}
				}
			}
		}
		
		System.out.println(cnt-1);
		System.out.println(maxSize);
		System.out.println(maxMerge);
	}
}
