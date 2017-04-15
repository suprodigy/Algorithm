package cheese;

import java.io.FileInputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info>{
	boolean isCheese;
	int y, x, hour;
	
	public Info(boolean isCheese, int y, int x, int hour) {
		this.isCheese = isCheese;
		this.y = y;
		this.x = x;
		this.hour = hour;
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return this.hour - comp.hour;
	}
}

public class Main {
	public static int R, C, TOTAL;
	public static int[][] d;
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go() {
		boolean[][] checked = new boolean[R][C];
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(false, 0, 0, 0));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
//			System.out.println(now.y + ", " + now.x);
			
			if (checked[now.y][now.x]) {
				continue;
			}
			checked[now.y][now.x] = true;
			
			if (now.isCheese) {
				TOTAL--;
				if (TOTAL == 0) {
					return now.hour;
				}
			}
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[i], nx = now.x + dx[i];
				
				if (!inRange(ny, nx) || checked[ny][nx]) {
					continue;
				}
				
				if (d[ny][nx] != -10) {
					d[ny][nx]--;
					if (d[ny][nx] == 0) {
						pq.offer(new Info(true, ny, nx, now.hour + 1));
					}
				} else {
					pq.offer(new Info(false, ny, nx, now.hour));
				}
			}
		}
		
		
		
		return -1;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<R && 0<=x && x<C);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt(); C = sc.nextInt();
		d = new int[R][C];
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				switch (sc.nextInt()) {
				case 1:
					d[i][j] = 2;
					TOTAL++;
					break;
				case 0:
					d[i][j] = -10;
					break;
				}
			}
		}
		
		System.out.println(go());
	}
}
