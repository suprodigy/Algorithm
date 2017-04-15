package sunday_morning;

import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info>{
	int y, x;
	int cnt1, cnt2;
	
	public Info(int y, int x, int cnt1, int cnt2) {
		this.y = y;
		this.x = x;
		this.cnt1 = cnt1;
		this.cnt2 = cnt2;
	}
	
	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		if (this.cnt1 != comp.cnt1) {
			return this.cnt1 - comp.cnt1;
		} else {
			return this.cnt2 - comp.cnt2;
		}
	}
}

public class Main {
	public static int N, M;
	public static char[][] a;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static void go(int sy, int sx) {
		boolean[][] checked = new boolean[N][M];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(sy, sx, 0, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (checked[now.y][now.x]) {
				continue;
			}
			checked[now.y][now.x] = true;
			
			if (a[now.y][now.x] == 'F') {
				System.out.println(now.cnt1 + " " + now.cnt2);
				return;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				int nextCnt1 = now.cnt1, nextCnt2 = now.cnt2;
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
				if (a[ny][nx] == 'g') {
					nextCnt1++;
				} else if (a[ny][nx] == '!') {
					nextCnt2++;
				}
				
				pq.offer(new Info(ny, nx, nextCnt1, nextCnt2));
			}
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); 
		M = sc.nextInt();
		
		a = new char[N][M];
		
		int y=0, x=0;
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				a[i][j] = str.charAt(j);
				
				if (a[i][j] == 'S') {
					y = i; x = j;
				} 
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (a[i][j] == 'g') {
					for (int k=0; k<4; k++) {
						int ny = i + dy[k], nx = j + dx[k];
						if (!inRange(ny, nx)) {
							continue;
						}
						if (a[ny][nx] == '.') {
							a[ny][nx] = '!';
						}
					}
				}
			}
		}
		
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<M; j++) {
//				System.out.print(a[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		go(y, x);
	}
}
