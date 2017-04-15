package labudovi;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Pair implements Comparable<Pair> {
	int y, x;
	int cnt;
	
	public Pair(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Pair comp) {
		// TODO Auto-generated method stub
		return this.cnt - comp.cnt;
	}
	
}

public class Main {
	public static int R, C;
	public static char[][] a;
	public static int[][] d;
	public static int ly1=-1, lx1, ly2, lx2;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static void go() {
		Queue<Pair> q = new LinkedList<>();
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (a[i][j] != 'X') {
					d[i][j] = 0;
					q.offer(new Pair(i, j, 0));
				}
			}
		}
		
		int DAY = 0;
		while (!q.isEmpty()) {
			Pair now = q.poll();
			
			a[now.y][now.x] = '.';
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				
				if (!inRange(ny, nx) || d[ny][nx] != -1) {
					continue;
				}
				
				if (a[ny][nx] == 'X') {
					d[ny][nx] = now.cnt + 1;
					q.offer(new Pair(ny, nx, now.cnt + 1));
				}
			}
		}
	}
	
	public static int getAnswer() {
		boolean[][] checked = new boolean[R][C];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(ly1, lx1, 0));
		
		int ret = 0;
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			
			if (checked[now.y][now.x]) {
				continue;
			}
			checked[now.y][now.x] = true;
			ret = Math.max(ret, now.cnt);
			
			if (now.y == ly2 && now.x == lx2) {
				break;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
				pq.offer(new Pair(ny, nx, d[ny][nx]));
			}
		}
		
		return ret;
	}
	
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<R && 0<=x && x<C);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt(); C = sc.nextInt();
		a = new char[R][C];
		d = new int[R][C];
		for (int i=0; i<R; i++) {
			String str = sc.next();
			for (int j=0; j<C; j++) {
				a[i][j] = str.charAt(j);
				d[i][j] = -1;
				
				if (a[i][j] == 'L') {
					if (ly1 == -1) {
						ly1 = i; lx1 = j;
					} else {
						ly2 = i; lx2 = j;
					}
				}
			}
		}
		
		go();
		System.out.println(getAnswer());
	}
}
