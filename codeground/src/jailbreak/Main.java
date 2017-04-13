package jailbreak;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info> {
	public int y, x;
	public int cnt;
	
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
	public static final int INF = 123456789;
	
	public static int N, M;
	public static char[][] a;
	public static int[][][] d;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static void go(Info start, int nth) {
		boolean[][] checked = new boolean[N+2][M+2];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(start);
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (checked[now.y][now.x]) {
				continue;
			}
			checked[now.y][now.x] = true;
			d[nth][now.y][now.x] = now.cnt;
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
				if (a[ny][nx] == '#') {
					pq.offer(new Info(ny, nx, now.cnt + 1));
				} else if (a[ny][nx] != '*') {
					pq.offer(new Info(ny, nx, now.cnt));
				}
			}
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N+2 && 0<=x && x<M+2);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test=0; test<T; test++) {
			N = sc.nextInt(); M = sc.nextInt();
			a = new char[N+2][M+2];
			d = new int[3][N+2][M+2];
			
			for (int i=0; i<N+2; i++) {
				Arrays.fill(a[i], '.');
			}
			
			Info[] infos = new Info[3];
			infos[0] = new Info(0, 0, 0);
			boolean flag = false;
			for (int i=1; i<=N; i++) {
				String str = sc.next();
				for (int j=1; j<=M; j++) {
					a[i][j] = str.charAt(j-1);
					
					if (a[i][j] == '$') {
						if (!flag) {
							flag = true;
							infos[1] = new Info(i, j, 0);
						} else {
							infos[2] = new Info(i, j, 0);
						}
					}
				}
			}
			
			for (int i=0; i<3; i++) {
				go (infos[i], i);
			}
			
			int ans = INF;
			for (int i=0; i<N+2; i++) {
				for (int j=0; j<M+2; j++) {
					if (a[i][j] == '*') {
						continue;
					}
					
					int temp = d[0][i][j] + d[1][i][j] + d[2][i][j];
					if (a[i][j] == '#') temp -= 2;
					ans = Math.min(ans, temp);
				}
			}
			
//			for (int k=0; k<3; k++) {
//				for (int i=0; i<=N+1; i++) {
//					for (int j=0; j<=M+1; j++) {
//						System.out.print(d[k][i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
			
			System.out.println(ans);
		}
	}
}