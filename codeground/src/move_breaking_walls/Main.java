package move_breaking_walls;

import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info> {
	int y, x;
	int moveCnt;
	int breakCnt;
	
	public Info(int y, int x, int moveCnt, int breakCnt) {
		this.y = y;
		this.x = x;
		this.moveCnt = moveCnt;
		this.breakCnt = breakCnt;
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return this.moveCnt - comp.moveCnt;
	}
}

public class Main {
	public static int N, M, K;
	public static boolean[][] a;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go() {
		boolean[][][] checked = new boolean[N][M][K+1];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, 1, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (checked[now.y][now.x][now.breakCnt]) {
				continue;
			}
			checked[now.y][now.x][now.breakCnt] = true;
			
			if (now.y == N-1 && now.x == M-1) {
				return now.moveCnt;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				int nextBreakCnt = now.breakCnt;
				
				if (!inRange(ny, nx)) {
					continue;
				}
				
				if (a[ny][nx] && now.breakCnt < K) {
					nextBreakCnt++;
				} else if (a[ny][nx]) {
					continue;
				}
				
				if (!checked[ny][nx][nextBreakCnt]) {
					pq.offer(new Info(ny, nx, now.moveCnt + 1, nextBreakCnt));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = 1;
		
		a = new boolean[N][M];
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				a[i][j] = (str.charAt(j) == '1' ? true : false);
			}
		}
		
		int ans = go();
		System.out.println(ans);
	}
}
