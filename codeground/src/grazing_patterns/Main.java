package grazing_patterns;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Info {
	int y1, x1;
	int y2, x2;
	int checked;
	
	public Info(int y1, int x1, int y2, int x2, int checked) {
		this.y1 = y1;
		this.x1 = x1;
		this.y2 = y2;
		this.x2 = x2;
		this.checked = checked;
	}
	
	public boolean isEnded() {
		return (y1 == y2) && (x1 == x2);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return y1 + ", " + x1 + " || " + y2 + ", " + x2 + " || " + Integer.toBinaryString(checked);
	}
}

public class Main {
	public static boolean[][] a = new boolean[5][5];
	public static int[] dy = new int[]{0, 0, 1, -1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go() {
		Queue<Info> q = new LinkedList<>();
		int checked = 0;
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (a[i][j]) {
					checked |= (1 << (5*i + j));
				}
			}
		}
		checked |= (1 << 0);
		checked |= (1 << 24);
		q.offer(new Info(0, 0, 4, 4, checked));
		
		int ans = 0;
		while(!q.isEmpty()) {
			Info now = q.poll();
			
			if (now.isEnded()) {
//				System.out.println(now);
				if (now.checked == ((1<<25) - 1)) ans ++;
				continue;
			}
			
			for (int i=0; i<4; i++) {
				int ny1 = now.y1 + dy[i], nx1 = now.x1 + dx[i];
				if (!inRange(ny1, nx1) || ((now.checked & (1 << (5*ny1 + nx1))) != 0)) {
					continue;
				}
				
				for (int j=0; j<4; j++) {
					int ny2 = now.y2 + dy[j], nx2 = now.x2 + dx[j];
					if (!inRange(ny2, nx2) || ((now.checked & (1 << (5*ny2 + nx2))) != 0)) {
						continue;
					}
					int next = now.checked;
					next |= (1 << (5*ny1 + nx1));
					next |= (1 << (5*ny2 + nx2));
					q.offer(new Info(ny1, nx1, ny2, nx2, next));
				}
			}
		}
		
		return ans;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<5 && 0<=x && x<5);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();
		for (int i=0; i<K; i++) {
			int y = sc.nextInt(), x = sc.nextInt();
			a[y-1][x-1] = true;
		}
		
		int ans = go();
		System.out.println(ans);
	}
}
