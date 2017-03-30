package escape_maze;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class PosInfo {
	public int ry, rx, by, bx, cnt;
	
	public PosInfo(int ry, int rx, int by, int bx, int cnt) {
		this.ry = ry;
		this.rx = rx;
		this.by = by;
		this.bx = bx;
		this.cnt = cnt;
	}
}

public class Solution1 {
	public static int N, M, Y, X;
	public static char[][] a;
	
	public static int go(int ry, int rx, int by, int bx) {
		Queue<PosInfo> q = new LinkedList<>();
		q.offer(new PosInfo(ry, rx, by, bx, 0));
				
		while (!q.isEmpty()) {
			PosInfo now = q.poll();
			if (now.cnt > 10) {
				break;
			}
			
			System.out.println(now.ry + ", " + now.rx + ", " + now.by + ", " + now.bx + ", " + now.cnt);
			
			if (now.ry == Y && now.rx == X) {
				return now.cnt;
			}
			
			// Left
			int nextRY = now.ry, nextRX = now.rx-1, nextBY = now.by, nextBX = now.bx-1;
			boolean flag = true;
			while (a[nextRY][nextRX] == '.' || a[nextRY][nextRX] == 'O' || a[nextBY][nextBX] == '.') {
				if (a[nextRY][nextRX] == 'O') {
					return now.cnt+1;
				}
				if (a[nextBY][nextBX] == 'O') {
					flag = false;
					break;
				}
				if (a[nextRY][nextRX] == '.' || ((a[nextRY][nextRX] == 'B') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextRX--;
				}
				if (a[nextBY][nextBX] == '.' || ((a[nextBY][nextBX] == 'R') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextBX--;
				}
			}
			nextRX++; nextBX++;
			if (flag) {
				q.offer(new PosInfo(nextRY, nextRX, nextBY, nextBX, now.cnt+1));
			}
			
			// Right
			nextRY = now.ry; nextRX = now.rx+1; nextBY = now.by; nextBX = now.bx+1;
			flag = true;
			while (a[nextRY][nextRX] == '.' || a[nextRY][nextRX] == 'O' || a[nextBY][nextBX] == '.') {
				if (a[nextRY][nextRX] == 'O') {
					return now.cnt+1;
				}
				if (a[nextBY][nextBX] == 'O') {
					flag = false;
					break;
				}
				if (a[nextRY][nextRX] == '.' || ((a[nextRY][nextRX] == 'B') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextRX++;
				}
				if (a[nextBY][nextBX] == '.' || ((a[nextBY][nextBX] == 'R') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextBX++;
				}
			}
			nextRX--; nextBX--;
			if (flag) {
				q.offer(new PosInfo(nextRY, nextRX, nextBY, nextBX, now.cnt+1));
			}
			
			// Top
			nextRY = now.ry-1; nextRX = now.rx; nextBY = now.by-1; nextBX = now.bx;
			flag = true;
			while (a[nextRY][nextRX] == '.' || a[nextRY][nextRX] == 'O' || a[nextBY][nextBX] == '.') {
				if (a[nextRY][nextRX] == 'O') {
					return now.cnt+1;
				}
				if (a[nextBY][nextBX] == 'O') {
					flag = false;
					break;
				}
				if (a[nextRY][nextRX] == '.' || ((a[nextRY][nextRX] == 'B') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextRY--;
				}
				if (a[nextBY][nextBX] == '.' || ((a[nextBY][nextBX] == 'R') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextBY--;
				}
			}
			nextRY++; nextBY++;
			if (flag) {
				q.offer(new PosInfo(nextRY, nextRX, nextBY, nextBX, now.cnt+1));
			}
			
			// Bottom
			nextRY = now.ry+1; nextRX = now.rx; nextBY = now.by+1; nextBX = now.bx;
			flag = true;
			while (a[nextRY][nextRX] == '.' || a[nextRY][nextRX] == 'O' || a[nextBY][nextBX] == '.') {
				if (a[nextRY][nextRX] == 'O') {
					return now.cnt+1;
				}
				if (a[nextBY][nextBX] == 'O') {
					flag = false;
					break;
				}
				if (a[nextRY][nextRX] == '.' || ((a[nextRY][nextRX] == 'B') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextRY++;
				}
				if (a[nextBY][nextBX] == '.' || ((a[nextBY][nextBX] == 'R') && (nextRY != nextBY) && (nextRX != nextBX))) {
					nextBY++;
				}
			}
			nextRY--; nextBY--;
			if (flag) {
				q.offer(new PosInfo(nextRY, nextRX, nextBY, nextBX, now.cnt+1));
			}			
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int ry=0, rx=0, by=0, bx=0;
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new char[N][M];
		
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				a[i][j] = str.charAt(j);
				if (a[i][j] == 'R') {
					ry = i; rx = j;
				} else if (a[i][j] == 'B') {
					by = i; bx = j;
				} else if (a[i][j] == 'O') {
					Y = i; X = j;
				}
			}
		}
		
		System.out.println(go(ry, rx, by, bx));
	}
}
