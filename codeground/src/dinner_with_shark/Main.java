package dinner_with_shark;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Shark {
	int size, speed, intel;
	
	public Shark(int size, int speed, int intel) {
		this.size = size;
		this.speed = speed;
		this.intel = intel;
	}
}

public class Main {
	
	public static final int MAX_N = 1002;
	
	public static int N;
	public static int visitCnt = 1;
	public static Shark[] sharks = new Shark[MAX_N];
	public static boolean[][] adj = new boolean[MAX_N][MAX_N];
	public static int[] aMatch = new int[MAX_N];
	public static int[] bMatch = new int[MAX_N];
	public static int[] visit = new int[MAX_N];
	
	public static boolean dfs(int now) {
		if (visit[now] == visitCnt) {
			return false;
		}
		
		visit[now] = visitCnt;
		for (int next=0; next<N; next++) {
			if (adj[now][next]) {
				if (bMatch[next] == -1 || dfs(bMatch[next])) {
					aMatch[now] = next;
					bMatch[next] = now;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static int bipartiteMatch() {
		int size = 0;
		
		Arrays.fill(aMatch, -1);
		Arrays.fill(bMatch, -1);
		
		for (int i=0; i<N; i++) {
			visitCnt++;
			if (dfs(i)) {
				size++;
			}
			visitCnt++;
			if (dfs(i)) {
				size++;
			}
		}
		
		return size;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for (int i=0; i<N; i++) {
			int size = sc.nextInt();
			int speed = sc.nextInt();
			int intel = sc.nextInt();
			sharks[i] = new Shark(size, speed, intel);
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (i == j) {
					continue;
				}
				
				if (sharks[i].size == sharks[j].size && sharks[i].speed == sharks[j].speed
						&& sharks[i].intel == sharks[j].intel && i > j) {
					continue;
				}
				
				if (sharks[i].size >= sharks[j].size && sharks[i].speed >= sharks[j].speed
						&& sharks[i].intel >= sharks[j].intel) {
					adj[i][j] = true;
				}
			}
		}
		
		int cnt = bipartiteMatch();
		
		System.out.println(N - cnt);
	}
	
}
