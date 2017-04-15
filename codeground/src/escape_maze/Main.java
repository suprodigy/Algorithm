package escape_maze;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Info {
	int y1, x1;
	int y2, x2;
	int cnt;
	
	public Info(int y1, int x1, int y2, int x2, int cnt) {
		this.y1 = y1; this.x1 = x1;
		this.y2 = y2; this.x2 = x2;
		this.cnt = cnt;
	}
}

public class Main {
	public static final int MAX = 10;
	
	public static int N, M;
	public static int EY, EX;
	public static char[][] a;
	
	public static int go(int ry, int rx, int by, int bx) {
		boolean[][][][] checked = new boolean[N][M][N][M];
		checked[ry][rx][by][bx] = true;
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(ry, rx, by, bx, 0));
		
		while (!q.isEmpty()) {
			Info now = q.poll();
//			System.out.println(now.y1 + ", " + now.x1 + ", " + now.y2 + ", " + now.x2);
			
			if (now.y2 == EY && now.x2 == EX) {
				continue;
			}
			if (now.y1 == EY && now.x1 == EX) {
				return now.cnt;
			}
			
			if (now.cnt == MAX) {
				continue;
			}
			
			for (int i=0; i<4; i++) {
				Info next = new Info(now.y1, now.x1, now.y2, now.x2, now.cnt+1);
				
				switch (i) {
				case 0:		// 오른쪽으로 기울이기
					slideToRight(next);
					break;
				case 1:		// 왼쪽으로 기울이기
					slideToLeft(next);
					break;
				case 2:		// 아래로 기울이기
					slideToDown(next);
					break;
				case 3:		// 위로 기울이기
					slideToUp(next);
					break;
				}
				
				if (!checked[next.y1][next.x1][next.y2][next.x2]) {
					checked[next.y1][next.x1][next.y2][next.x2] = true;
					q.offer(next);
				}
			}
		}
		
		return -1;
	}
	
	private static void slideToRight(Info next) {
		if (next.x1 < next.x2) {
			int ny = next.y2, nx = next.x2;
			while (a[ny][nx] != '#') {
				nx++;
				if (a[ny][nx-1] == 'O') {
					break;
				}
			}
			next.x2 = nx - 1;
			
			ny = next.y1; nx = next.x1;
			while (a[ny][nx] != '#') {
				nx++;
				if (a[ny][nx-1] == 'O') {
					break;
				}
				
				if (ny == next.y2 && nx == next.x2) {
					break;
				}
			}
			next.x1 = nx - 1;
		} else {
			int ny = next.y1, nx = next.x1;
			while (a[ny][nx] != '#') {
				nx++;
				if (a[ny][nx-1] == 'O') {
					break;
				}
			}
			next.x1 = nx - 1;
			
			ny = next.y2; nx = next.x2;
			while (a[ny][nx] != '#') {
				nx++;
				if (a[ny][nx-1] == 'O') {
					break;
				}
				
				if (!(ny == EY && nx == EX) && (ny == next.y1 && nx == next.x1)) {
					break;
				}
			}
			next.x2 = nx - 1;
		}
	}
	
	public static void slideToLeft(Info next) {
		if (next.x1 > next.x2) {
			int ny = next.y2, nx = next.x2;
			while (a[ny][nx] != '#') {
				nx--;
				if (a[ny][nx+1] == 'O') {
					break;
				}
			}
			next.x2 = nx + 1;
			
			ny = next.y1; nx = next.x1;
			while (a[ny][nx] != '#') {
				nx--;
				if (a[ny][nx+1] == 'O') {
					break;
				}
				
				if (!(ny == EY && nx == EX) && (ny == next.y2 && nx == next.x2)) {
					break;
				}
			}
			next.x1 = nx + 1;
		} else {
			int ny = next.y1, nx = next.x1;
			while (a[ny][nx] != '#') {
				nx--;
				if (a[ny][nx+1] == 'O') {
					break;
				}
			}
			next.x1 = nx + 1;
			
			ny = next.y2; nx = next.x2;
			while (a[ny][nx] != '#') {
				nx--;
				if (a[ny][nx+1] == 'O') {
					break;
				}
				
				if (!(ny == EY && nx == EX) && (ny == next.y1 && nx == next.x1)) {
					break;
				}
			}
			next.x2 = nx + 1;
		}
	}
	
	public static void slideToDown(Info next) {
		if (next.y1 < next.y2) {
			int ny = next.y2, nx = next.x2;
			while (a[ny][nx] != '#') {
				ny++;
				if (a[ny-1][nx] == 'O') {
					break;
				}
			}
			next.y2 = ny - 1;
			
			ny = next.y1; nx = next.x1;
			while (a[ny][nx] != '#') {
				ny++;
				if (a[ny-1][nx] == 'O') {
					break;
				}
				
				if (ny == next.y2 && nx == next.x2) {
					break;
				}
			}
			next.y1 = ny - 1;
		} else {
			int ny = next.y1, nx = next.x1;
			while (a[ny][nx] != '#') {
				ny++;
				if (a[ny-1][nx] == 'O') {
					break;
				}
			}
			next.y1 = ny - 1;
			
			ny = next.y2; nx = next.x2;
			while (a[ny][nx] != '#') {
				ny++;
				if (a[ny-1][nx] == 'O') {
					break;
				}
				
				if (!(ny == EY && nx == EX) && (ny == next.y1 && nx == next.x1)) {
					break;
				}
			}
			next.y2 = ny - 1;
		}
	}
	
	public static void slideToUp(Info next) {
		if (next.y1 > next.y2) {
			int ny = next.y2, nx = next.x2;
			while (a[ny][nx] != '#') {
				ny--;
				if (a[ny+1][nx] == 'O') {
					break;
				}
			}
			next.y2 = ny + 1;
			
			ny = next.y1; nx = next.x1;
			while (a[ny][nx] != '#') {
				ny--;
				if (a[ny+1][nx] == 'O') {
					break;
				}
				
				if (ny == next.y2 && nx == next.x2) {
					break;
				}
			}
			next.y1 = ny + 1;
		} else {
			int ny = next.y1, nx = next.x1;
			while (a[ny][nx] != '#') {
				ny--;
				if (a[ny+1][nx] == 'O') {
					break;
				}
			}
			next.y1 = ny + 1;
			
			ny = next.y2; nx = next.x2;
			while (a[ny][nx] != '#') {
				ny--;
				if (a[ny+1][nx] == 'O') {
					break;
				}
				
				if (!(ny == EY && nx == EX) && (ny == next.y1 && nx == next.x1)) {
					break;
				}
			}
			next.y2 = ny + 1;
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new char[N][M];
		
		int ry=0, rx=0, by=0, bx=0;
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				char ch = str.charAt(j);
				if (ch == 'R') {
					ry = i; rx = j;
					a[i][j] = '.';
				} else if (ch == 'B') {
					by = i; bx = j;
					a[i][j] = '.';
				} else if (ch == 'O') {
					EY = i; EX = j;
					a[i][j] = ch;
				} else {
					a[i][j] = ch;
				}
			}
		}
		
		int ans = go(ry, rx, by, bx);
		System.out.println(ans == -1 ? 0 : 1);
	}
}