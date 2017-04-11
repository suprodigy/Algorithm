package lilypad_pond;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


class Info implements Comparable<Info> {
	public int y, x, cnt;
	
	public Info(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return (this.cnt - comp.cnt);
	}
}

public class Main {
	public static final int INF = 123456789;
	public static int N, M, si, sj, ei, ej;
	public static int[][] a;
	public static long[][] c;
	public static int[][] d;
	public static int[] dy = {-2, -2, -1, -1, 1, 1, 2, 2};
	public static int[] dx = {-1, 1, -2, 2, -2, 2, -1, 1};
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void go(int y, int x) {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(y, x, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (now.cnt >= d[now.y][now.x]) {
				continue;
			}
			d[now.y][now.x] = now.cnt;
			
			if (a[now.y][now.x] == 3) {
				c[now.y][now.x] = 1;
			} else {
				c[now.y][now.x] = count(now.y, now.x);
			}
			
			if (now.y == ei && now.x == ej) {
				d[now.y][now.x]--;
				return;
			}
			
			for (int i=0; i<8; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				int nextCnt = now.cnt;
				
				if (!inRange(ny, nx) || a[ny][nx] == 2) {
					continue;
				} 
				
				if (a[ny][nx] != 1) {
					nextCnt++;
				}
				
				if (nextCnt < d[ny][nx]) {
					pq.offer(new Info(ny, nx, nextCnt));
				}
			}
		}
	}
	
	public static long count(int y, int x) {
		long ret = 0;
		for (int i=0; i<8; i++) {
			int ny = y + dy[i], nx = x + dx[i];
			if (inRange(ny, nx)) {
				ret += c[ny][nx];
			}
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				a[i][j] = sc.nextInt();
				
				if (a[i][j] == 3) {
					si = i; sj = j;
				} else if (a[i][j] == 4) {
					ei = i; ej = j;
				}
			}
		}
		
		c = new long[N][M];
		
		d = new int[N][M];
		for (int i=0; i<N; i++) {
			Arrays.fill(d[i], INF);
		}
		
		go(si, sj);
		
		if (c[ei][ej] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(d[ei][ej]);
			System.out.println(c[ei][ej]);
		}
	}
}
