package cross_the_road_7;

import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info> {

	int y, x;
	int cnt;
	int time;
	
	public Info(int y, int x, int cnt, int time) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.time = time;
	}
	
	@Override
	public int compareTo(Info comp) {
		return this.time - comp.time;
	}
	
	@Override
	public String toString() {
		return y + ", " + x + ", " + time + ", " + cnt;
	}
	
}

public class Main {
	public static final int[] dy = new int[]{0, 0, 1, -1};
	public static final int[] dx = new int[]{1, -1, 0, 0};
	
	public static int N, T;
	public static int[][] a;
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public static int go() {
		boolean[][][] checked = new boolean[N][N][4];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, 0, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
//			System.out.println(now);
			
			if (now.cnt > 3) {
				continue;
			}
			
			if (checked[now.y][now.x][now.cnt]) {
				continue;
			}
			checked[now.y][now.x][now.cnt] = true;
			
			if (now.y == N-1 && now.x == N-1) {
				return now.time;
			}
			
			for (int k=0; k<4; k++) {
				int ny = now.y + dy[k], nx = now.x + dx[k];
				int nextT = now.time + T;
				
				if (!inRange(ny, nx)) {
					continue;
				}
				
				// 안 먹고 넘어가기
				if (now.cnt < 2 && !checked[ny][nx][now.cnt+1]) {
					pq.offer(new Info(ny, nx, now.cnt + 1, nextT));
				}
				
				// 먹고 넘어가기
				if (now.cnt == 2 && !checked[ny][nx][0]) {
					pq.offer(new Info(ny, nx, 0, nextT + a[ny][nx]));
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		T = sc.nextInt();
		
		a = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(go());
	}
}
