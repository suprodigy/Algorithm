package robot;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Tuple {
	public int y;
	public int x;
	public int dir;
	public int cnt;
	
	public Tuple(int y, int x, int dir, int cnt) {
		this.y = y;
		this.x = x;
		this.dir = dir;
		this.cnt = cnt;
	}
}

public class Main {
	public static int[][] a;
	public static int[][][] d;
	public static int[] transDir = new int[]{0, 0, 2, 1, 3};
	public static int[] dy = new int[]{0, 1, 0, -1};
	public static int[] dx = new int[]{1, 0, -1, 0};
	public static int M, N, Y, X, DIR;
	
	public static int go(int y, int x, int dir) {
		Queue<Tuple> q = new LinkedList<>();
		q.offer(new Tuple(y, x, dir, 0));
		d[y][x][dir] = 0;
		
		while (!q.isEmpty()) {
			Tuple now = q.poll();
			
			if (now.y == Y && now.x == X && now.dir == DIR) {
				return now.cnt;
			}
			
			// 명령어1: Go 1~3
			int ny = now.y, nx = now.x;
			for (int i=0; i<3; i++) {
				ny+=dy[now.dir]; nx+=dx[now.dir];
				if (inRange(ny, nx) && a[ny][nx] != 1) {
					if (d[ny][nx][now.dir] == -1) {
						d[ny][nx][now.dir] = now.cnt+1;
						q.offer(new Tuple(ny, nx, now.dir, now.cnt+1));
					} 
				} else {
					break;
				}
			}
			
			// 명령어2: turn left
			int nextDir = now.dir - 1;
			if (nextDir < 0) {
				nextDir += 4;
			}
			if (d[now.y][now.x][nextDir] == -1) {
				d[now.y][now.x][nextDir] = now.cnt + 1;
				q.offer(new Tuple(now.y, now.x, nextDir, now.cnt+1));
			}
			
			// 명령어3: turn right
			nextDir = (now.dir + 1) % 4;
			if (d[now.y][now.x][nextDir] == -1) {
				d[now.y][now.x][nextDir] = now.cnt + 1;
				q.offer(new Tuple(now.y, now.x, nextDir, now.cnt+1));
			}
		}
		
		return 0;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<M && 0<=x && x<N);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		a = new int[M][N];
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		d = new int[M][N][4];
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<4; k++) {
					d[i][j][k] = -1;
				}
			}
		}
		
		int y = sc.nextInt() -1, x = sc.nextInt() -1, dir = transDir[sc.nextInt()];
		Y = sc.nextInt() - 1; X = sc.nextInt() - 1; DIR = transDir[sc.nextInt()]; 
		
		System.out.println(go(y, x, dir));
	}
}
