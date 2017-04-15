package puyopuyo;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	int y;
	int x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	public static final int R = 12;
	public static final int C = 6;
	
	public static char[][] a = new char[R][C];
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<R && 0<=x && x<C);
	}
	
	public static boolean boom() {
		boolean flag = false;
		
		boolean[][] checked = new boolean[R][C];
		for (int i=R-1; i>=0; i--) {
			for (int j=0; j<C; j++) {
				if (!checked[i][j] && a[i][j] != '.') {
					flag |= go(checked, i, j);
//					System.out.println();
				}
			}
		}
		
		return flag;
	}
	
	public static boolean go(boolean[][] checked, int y, int x) {
		Queue<Pair> ret = new LinkedList<>();
		Queue<Pair> q = new LinkedList<>();
		checked[y][x] = true;
		q.offer(new Pair(y, x));
		
		int cnt = 0;
		while (!q.isEmpty()) {
			Pair now = q.poll();
//			System.out.println(now.y + ", " + now.x + ", " + cnt);
			cnt++;
			ret.offer(new Pair(now.y, now.x));
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
//				System.out.println(ny + ", " + nx + ": " + a[ny][nx]);
				
				if (a[ny][nx] == a[y][x]) {
					checked[ny][nx] = true;
					q.offer(new Pair(ny, nx));
				}
			}
		}
		
		if (cnt >= 4) {
			while (!ret.isEmpty()) {
				Pair temp = ret.poll();
				a[temp.y][temp.x] = '.';
			}
			return true;
		}
		
		return false;
	}
	
	public static void getDown() {
		for (int i=R-1; i>=0; i--) {
			for (int j=0; j<C; j++) {
				if (a[i][j] != '.') {
					int ni = i+1;
					while (ni < R && a[ni][j] == '.') {
						char temp = a[ni][j];
						a[ni][j] = a[ni-1][j];
						a[ni-1][j] = temp;
						ni++;
					}
				}
			}
		}
	}
	
	public static void print() {
		for (int k=0; k<R; k++) {
			for (int l=0; l<C; l++) {
				System.out.print(a[k][l]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		for (int i=0; i<R; i++) {
			String str = sc.next();
			for (int j=0; j<C; j++) {
				a[i][j] = str.charAt(j);
			}
		}
//		print();
		
		int ans = 0;
		while (boom()) {
			ans++;
			getDown();
//			print();
		}
		
		System.out.println(ans);
	}
}
