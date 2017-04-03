package key;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	public int y, x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	public static int N, M, ans;
	public static char[][] a;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static void go() {
		boolean[][] checked = new boolean[N+2][M+2];
		boolean[][] reachable = new boolean[N+2][M+2];
		
		for (int i=0; i<N+2; i++) {
			Arrays.fill(checked[i], false);
			Arrays.fill(reachable[i], false);
		}
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));
		checked[0][0] = true;
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			if (a[now.y][now.x] == '$') {
				ans++;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				if (!inRange(ny, nx) || checked[ny][nx] || a[ny][nx] == '*') {
					continue;
				}
				checked[ny][nx] = true;
				
				if (isDoor(ny, nx)) {
					reachable[ny][nx] = true;
				} else if (isKey(ny, nx)) {
					open(q, reachable, a[ny][nx]);
					q.offer(new Pair(ny, nx));
				} else if (a[ny][nx] != '*') {
					q.offer(new Pair(ny, nx));
				}
			}
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N+2 && 0<=x && x<M+2);
	}
	
	public static void open(String keys) {
		boolean[] temp = new boolean['Z' - 'A' + 1];
		Arrays.fill(temp, false);
		for (int i=0; i<keys.length(); i++) {
			temp[keys.charAt(i) - 'a'] = true;
		}
		
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<M+1; j++) {
				if (isDoor(i, j) && temp[a[i][j] - 'A']) {
					a[i][j] = '.';
				}
			}
		}
	}
	
	public static void open(Queue<Pair> q, boolean[][] reachable, char key) {
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<M+1; j++) {
				if (isDoor(i, j) && ((a[i][j] - 'A') == (key - 'a'))){
					if (reachable[i][j]) {
						q.offer(new Pair(i, j));
					}
					a[i][j] = '.';
				}
			}
		}
	}
	
	public static boolean isDoor(int y, int x) {
		return ('A' <= a[y][x] && a[y][x] <= 'Z');
	}
	
	public static boolean isKey(int y, int x) {
		return ('a' <= a[y][x] && a[y][x] <= 'z');
	}
	
	public static void printArray() {
		for (int i=0; i<N+2; i++) {
			for (int j=0; j<M+2; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t=0; t<T; t++) {
			N = sc.nextInt(); M = sc.nextInt(); ans = 0;
			
			a = new char[N+2][M+2];
			for (int j=0; j<N+2; j++) {
				Arrays.fill(a[j], '.');
			}
			
			for (int j=1; j<N+1; j++) {
				String str = sc.next();
				for (int k=1; k<M+1; k++) {
					a[j][k] = str.charAt(k-1);
				}
			}
			
//			printArray();
			
			String keys = sc.next();
			if (!keys.equals("0")) {
				open(keys);
			}
			
			go();
			System.out.println(ans);
		}
	}
}
