package fire;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	public int y, x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Tuple {
	public int y, x;
	public int cnt;
	
	public Tuple(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class Main {
	public static int[] dy = {0, 0, 1, -1};
	public static int[] dx = {1, -1, 0, 0};
	public static int N, M;
	public static char[][] a;
	
	public static int go(int y, int x) {
		boolean[][] checked = new boolean[N][M];
		for (int i=0; i<N; i++) {
			Arrays.fill(checked[i], false);
		}
		
		Queue<Tuple> q = new LinkedList<>();
		q.offer(new Tuple(y, x, 0));
		checked[y][x] = true;
		
		Queue<Pair> firePos = new LinkedList<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (a[i][j] == '*') {
					firePos.offer(new Pair(i, j));
				}
			}
		}
		
		int cnt = 0;
		while (!q.isEmpty()) {
			Tuple now = q.poll();
			
			if (cnt == now.cnt) {
				// 불 번짐
				Queue<Pair> temp = new LinkedList<>();
				while (!firePos.isEmpty()) {
					Pair p = firePos.poll();
					for (int i=0; i<4; i++) {
						int ny = p.y + dy[i], nx = p.x + dx[i];
						if (inRange(ny, nx) && a[ny][nx] == '.') {
							a[ny][nx] = '*';
							temp.offer(new Pair(ny, nx));
						}
					}
				}
				firePos = temp;
				cnt = now.cnt + 1;
				
//				// test
//				System.out.println(cnt);
//				printArray();
			}
			
			// 상근이 이동
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				if (!inRange(ny, nx)) {
					return now.cnt + 1;
				} else if (!checked[ny][nx] && a[ny][nx] == '.') {
					checked[ny][nx] = true;
					q.offer(new Tuple(ny, nx, now.cnt + 1));
				}
			}
		}
		
		return -1;
	}
	
	public static void printArray() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 0; test_case < TC; test_case++) {
			M = sc.nextInt(); N = sc.nextInt();
			a = new char[N][M];
			int y = 0, x = 0;
			for (int i=0; i<N; i++) {
				String str = sc.next();
				for (int j=0; j<M; j++) {
					a[i][j] = str.charAt(j);
					if (a[i][j] == '@') {
						y = i; x = j;
					}
				}
			}
			
			int ans = go(y, x);
			if (ans == -1) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(ans);
			}
		}
	}
}
