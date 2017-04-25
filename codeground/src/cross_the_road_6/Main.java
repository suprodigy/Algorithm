package cross_the_road_6;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pair {
	int y, x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Info implements Comparable<Info>{
	int y, x, cnt;
	
	public Info(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
	
	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return this.cnt - comp.cnt;
	}
}

public class Main {
	public static final int[] dy = new int[]{-1, 0, 1, 0};
	public static final int[] dx = new int[]{0, 1, 0, -1};
	
	public static int N, K, R;
	public static boolean[][][] isBlocked;
	public static int[][] a;
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public static int go(int y, int x, int idx) {
		int[] dist = new int[K+1];
		boolean[][] checked = new boolean[N][N];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		pq.offer(new Info(y, x, 0));
		
		int ret = 0;
		int total = 0;
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (checked[now.y][now.x]) {
				continue;
			}
			checked[now.y][now.x] = true;
			
			int nth = a[now.y][now.x];
			if (nth != 0) {
				total++;
				dist[nth] = now.cnt;
				
				if (dist[nth] > 0 && nth > idx) {
					ret++;
				}
			}
			
			if (total == K) {
				break;
			}
			
			for (int k=0; k<4; k++) {
				int ny = now.y + dy[k], nx = now.x + dx[k];
				int nextCnt = now.cnt;
				
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
				if (isBlocked[now.y][now.x][k]) {
					nextCnt++;
				}
				
				pq.offer(new Info(ny, nx, nextCnt));
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		R = sc.nextInt();
		
		isBlocked = new boolean[N][N][4];
		a = new int[N][N];
		
		for (int i=0; i<R; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			int w = sc.nextInt() - 1;
			int z = sc.nextInt() - 1;
			
			for (int k=0; k<4; k++) {
				int ny = u + dy[k], nx = v + dx[k];
				
				if (!inRange(ny, nx)) {
					continue;
				}
				
				if (ny == w && nx == z) {
					int r = k-2;
					if (r < 0) {
						r += 4;
					}
			
					isBlocked[u][v][k] = true;
					isBlocked[w][z][r] = true;
					
					break;
				}
			}
		}
		
		List<Pair> list = new ArrayList<>();
		for (int i=1; i<=K; i++) {
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			
			a[y][x] = i;
			list.add(new Pair(y, x));
		}
		
		int ans = 0;
		for (int i=0; i<K; i++) {
			int y = list.get(i).y;
			int x = list.get(i).x;
			
			ans += go(y, x, i+1);
		}
		System.out.println(ans);
	}
}
