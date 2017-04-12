package rising_moon;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Info {
	int y, x, cnt;
	int key;
	
	public Info(int y, int x, int cnt, int key) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.key = key;
	}
}

public class Main {
	public static int N, M;
	public static char[][] a;
	public static int[] dy = new int[]{0, 0, -1, 1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go(int sy, int sx) {
		Queue<Info> q = new LinkedList<>();
		boolean[][][] checked = new boolean[(1<<6)][N][M];
		checked[0][sy][sx] = true;
		q.offer(new Info(sy, sx, 0, 0));
		
		while(!q.isEmpty()) {
			Info now = q.poll();
//			System.out.println(now.y + ", " + now.x + ", " + now.cnt + ", " + Integer.toBinaryString(now.key));
			
			if (a[now.y][now.x] == '1') {
				return now.cnt;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				int nextKey = now.key;
				if (!inRange(ny, nx)) {
					continue;
				}
				
				if (isKey(a[ny][nx])) {
					nextKey |= (1 << (a[ny][nx] - 'a'));
				} else if (!canGo(a[ny][nx], now.key)) {
					continue;
				}
				
				if (checked[nextKey][ny][nx]) 
					continue;
				
				checked[now.key][ny][nx] = true;
				q.offer(new Info(ny, nx, now.cnt+1, nextKey));
			}
		}
		
		return -1;
	}
	
	public static boolean isKey(char ch) {
		return ('a' <= ch && ch <= 'f');
	}
	
	public static boolean canGo(char ch, int key) {
		if (isDoor(ch) && (key & (1<<(ch - 'A'))) != 0) {
			return true;
		} else if (isDoor(ch) || isWall(ch)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isDoor(char ch) {
		return ('A' <= ch && ch <= 'F');
	}
	
	public static boolean isWall(char ch) {
		return (ch == '#');
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new char[N][M];
		
		int y=0, x=0;
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				a[i][j] = str.charAt(j);
				if (a[i][j] == '0') {
					y = i; x = j;
				}
			}
		}
		
		int ans = go(y, x);
		System.out.println(ans);
	}
}