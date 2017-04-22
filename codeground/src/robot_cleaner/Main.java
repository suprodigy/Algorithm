package robot_cleaner;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static final int[] dy = new int[]{-1, 0, 1, 0};
	public static final int[] dx = new int[]{0, 1, 0, -1};
	
	public static int N, M;
	public static boolean[][] a, checked;
	
	public static void go(int y, int x, int dir) {
		checked[y][x] = true;
		
//		print();
		
		for (int k=1; k<=4; k++) {
			int nextDir = dir - k;
			if (nextDir < 0) {
				nextDir+=4;
			}
			
			int ny = y + dy[nextDir], nx = x + dx[nextDir];
			
			if (!inRange(ny, nx)) {
				continue;
			}
			
			if (a[ny][nx] && !checked[ny][nx]) {
				go (ny, nx, nextDir);
				return;
			}
		}
		
		int nextDir = dir - 2;
		if (nextDir < 0) {
			nextDir += 4;
		}
		
		int ny = y + dy[nextDir], nx = x + dx[nextDir];
		
		if (inRange(ny, nx) && a[ny][nx]) {
			go(ny, nx, dir);
			return;
		}
	}
	
	public static void print() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(checked[i][j] ? 1 : 0);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		int y = sc.nextInt(), x = sc.nextInt(), dir = sc.nextInt();
		
		a = new boolean[N][M];
		checked = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				a[i][j] = (sc.nextInt() == 1 ? false : true);
			}
		}
		
		go(y, x, dir);
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (checked[i][j]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}