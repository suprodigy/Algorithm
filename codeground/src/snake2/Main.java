package snake2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int L;
	public static int[] dx = new int[]{1, 0, -1, 0};
	public static int[] dy = new int[]{0, -1, 0, 1};
	
	public static boolean inRange(int x, int y) {
		return (-L<=x && x<=L && -L<=y && y<=L);
	}
	
	public static int isOrthogonal(int x1, int y1, int x2, int y2, 
			int x3, int y3, int x4, int y4) {
		if (x3 == x4) {
			if (Math.min(y3, y4) <= y1 && y1 <= Math.max(y3, y4)
					&& Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2)) {
				if (y3 > y4) {
					return y3 - y1;
				} else {
					return y1 - y4;
				}
			}
		} else {
			if (Math.min(x3, x4) <= x1 && x1 <= Math.max(x3, x4)
					&& Math.min(y1, y2) <= y3 && y3 <= Math.max(y1, y2)) {
				if (x3 > x4) {
					return x3 - x1;
				} else {
					return x1 - x3;
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt(); 
		int N = sc.nextInt();
		int[][] d = new int[N+1][2];
		d[0][0] = 0; d[0][1] = 0;
		
		int x = 0, y = 0, ans = 1, dir = 0;
		for (int i=0; i<N; i++) {
			int a = sc.nextInt(); 
			char b = sc.next().charAt(0);
//			System.out.println(a + ", " + b);
			
			int nx = x + a * dx[dir], ny = y + a * dy[dir];
//			System.out.println(nx + ", " + ny);
			
			boolean flag = false;
			for (int j=i-2; j>=0; j--) {
				int x1 = d[j][0], y1 = d[j][1];
				int x2 = d[j+1][0], y2 = d[j+1][1];
				
				int x3 = d[i][0], y3 = d[i][1];
				
				int temp = isOrthogonal(x1, y1, x2, y2, x3, y3, nx, ny);
//				System.out.println(temp);
				if (temp != -1) {
					flag = true;
					ans += (temp-1);
					break;
				}
			}
			if (flag) {
				break;
			}
			
			if (!inRange(ny, nx)) {
				switch (dir) {
				case 0:
					ans += (L - x);
					break;
				case 1:
					ans += (y + L);
					break;
				case 2:
					ans += (x + L);
					break;
				case 3:
					ans += (L - y);
					break;
				}
				
				break;
			}
			
			d[i+1][0] = nx; d[i+1][1] = ny;
			x = nx; y = ny;
			ans += Math.abs(a * dx[dir] + a * dy[dir]);
//			System.out.println(ans);
//			System.out.println();
			
			switch (b) {
			case 'L':
				dir--;
				if (dir < 0) dir += 4;
				break;
			case 'R':
				dir = (dir+1) % 4;
				break;
			}
		}
		
		System.out.println(ans);
	}
}