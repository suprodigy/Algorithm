package roll_the_dice;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int N, M, K;
	public static int[][] a;
	public static int[][] d = new int[4][3];
	public static int[] dy = new int[]{0, 0, -1, 1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<M);
	}
	
	public static void rollEast() {
		int temp = d[3][1];
		d[3][1] = d[1][2];
		
		for (int j=2; j>=1; j--) {
			d[1][j] = d[1][j-1];
		}
		
		d[1][0] = temp;
	}
	
	public static void rollWest() {
		int temp = d[3][1];
		d[3][1] = d[1][0];
		
		for (int j=0; j<=1; j++) {
			d[1][j] = d[1][j+1];
		}
		
		d[1][2] = temp;
	}
	
	public static void rollNorth() {
		int temp =  d[0][1];
		for (int i=0; i<=2; i++) {
			d[i][1] = d[i+1][1];
		}
		d[3][1] = temp;
	}
	
	public static void rollSouth() {
		int temp = d[3][1];
		for (int i=3; i>=1; i--) {
			d[i][1] = d[i-1][1];
		}
		d[0][1] = temp;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int y, x;
		
		N = sc.nextInt(); M = sc.nextInt(); 
		y = sc.nextInt(); x = sc.nextInt(); K = sc.nextInt();
		
		a = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		for (int i=0; i<K; i++) {
			int op = sc.nextInt();
			
			y += dy[op-1];
			x += dx[op-1];
			
			if (!inRange(y, x)) {
				y -= dy[op-1];
				x -= dx[op-1];
				continue;
			}
			
			switch(op) {
			case 1:
				rollEast();
				break;
			case 2:
				rollWest();
				break;
			case 3:
				rollNorth();
				break;
			case 4:
				rollSouth();
				break;
			}
			
			System.out.println(d[1][1]);
			
			if (a[y][x] == 0) {
				a[y][x] = d[3][1];
			} else {
				d[3][1] = a[y][x];
				a[y][x] = 0;
			}
		}
	}
}
