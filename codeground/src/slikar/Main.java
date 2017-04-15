package slikar;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Info {
	boolean isWater;
	int y, x, cnt;
	
	public Info(boolean isWater, int y, int x, int cnt) {
		this.isWater = isWater;
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class Main {
	public static int R, C, EY, EX;
	public static char[][] a; 
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go(int sy, int sx) {
		boolean[][] checked = new boolean[R][C];
		Queue<Info> q = new LinkedList<>();
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (a[i][j] == '*') {
					checked[i][j] = true;
					q.offer(new Info(true, i, j, -1));
				}
			}
		}
		
		checked[sy][sx] = true;
		q.offer(new Info(false, sy, sx, 0));
		
		while (!q.isEmpty()) {
			Info now = q.poll();
			
			if (!now.isWater && isEnded(now.y, now.x)) {
				return now.cnt;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
				if (now.isWater && a[ny][nx] == 'D') {
					continue;
				}
				
				if (a[ny][nx] != 'X') {
					checked[ny][nx] = true;
					q.offer(new Info(now.isWater, ny, nx, now.isWater ? -1 : now.cnt + 1));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isEnded(int y, int x) {
		return (y == EY && x == EX);
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<R && 0<=x && x<C);
	}
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt(); C = sc.nextInt();
		a = new char[R][C];
		int sy=0, sx=0;
		
		for (int i=0; i<R; i++) {
			String str = sc.next();
			for (int j=0; j<C; j++) {
				a[i][j] = str.charAt(j);
				
				if (a[i][j] == 'S') {
					sy = i; sx = j;
				} else if (a[i][j] == 'D') {
					EY = i; EX = j;
				}
			}
		}
		
		int ans = go(sy, sx);
		System.out.println(ans == -1 ? "KAKTUS" : ans);
	}
}
