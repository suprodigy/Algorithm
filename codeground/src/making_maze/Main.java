package making_maze;

import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info>{
	int y, x;
	int change;
	
	public Info(int y, int x, int change) {
		this.y = y;
		this.x = x;
		this.change = change;
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return this.change - comp.change;
	}
}

public class Main {
	public static int N;
	public static boolean[][] a;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go() {
		boolean[][][] checked = new boolean[N][N][2*N];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (checked[now.y][now.x][now.change]) {
				continue;
			}
			checked[now.y][now.x][now.change] = true;
			
			if (now.y == N-1 && now.x == N-1) {
				return now.change;
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				int nextChange = now.change;
				
				if (!inRange(ny, nx)) {
					continue;
				}
				
				if (!a[ny][nx]) {
					nextChange++;
				}
				
				if (checked[ny][nx][nextChange]) {
					continue;
				}
				
				pq.offer(new Info(ny, nx, nextChange));
			}
		}
		
		return -1;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		a = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<N; j++) {
				a[i][j] = str.charAt(j) == '1' ? true : false;
			}
		}
	
		System.out.println(go());
	}
}
