package game_2048;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static int ans = 0;
	public static int N;
	
	public static void go(int[][] a, int cnt) {
		if (cnt == 10) {
			return;
		}
		
		if (promising(a)) {
			// Case UP
			boolean flag = false;
			int[][] temp1 = new int[N][N];
			for (int i=0; i<N; i++) {
				System.arraycopy(a[i], 0, temp1[i], 0, a[i].length);
			}
			flag = mergeUp(temp1);
			if (flag) {
				go(temp1, cnt+1);
			}
			
			// Case DOWN
			int[][] temp2 = new int[N][N];
			for (int i=0; i<N; i++) {
				System.arraycopy(a[i], 0, temp2[i], 0, a[i].length);
			}
			flag = mergeDown(temp2);
			if (flag) {
				go(temp2, cnt+1);
			}
			
			// Case RIGHT
			int[][] temp3 = new int[N][N];
			for (int i=0; i<N; i++) {
				System.arraycopy(a[i], 0, temp3[i], 0, a[i].length);
			}
			flag = mergeRight(temp3);
			if (flag) {
				go(temp3, cnt+1);
			}
			
			// Case LEFT
			flag = false;
			int[][] temp4 = new int[N][N];
			for (int i=0; i<N; i++) {
				System.arraycopy(a[i], 0, temp4[i], 0, a[i].length);
			}
			flag = mergeLeft(temp4);
			if (flag) {
				go(temp4, cnt+1);
			}
		}
	}
	
	public static boolean promising(int[][] a) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] d = new int[30];
		Arrays.fill(d, 0);
		
		int num = 0;
		for (int i=0; i<30; i++) {
			map.put((int)Math.pow(2, num), num);
			num++;
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (a[i][j] != 0){
					d[map.get(a[i][j])]++;
				}
			}
		}
		
		int max = 0;
		for (int i=0; i<30; i++) {
			if (i != 30 && d[i] != 0) {
				d[i+1] += (d[i] / 2);
				d[i] %= 2;
			}
			
			if (d[i] != 0) {
				max = Math.max(max, i);
			}
		}
		
		if (max != 0 && ans < (int)Math.pow(2, max)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean mergeUp(int[][] a) {
		boolean flag = moveUp(a);
		for (int j=0; j<N; j++) {
			for (int i=0; i<=N-2; i++) {
				if (a[i][j] != 0 && a[i][j] == a[i+1][j]) {
					flag = true;
					a[i][j] += a[i+1][j];
					a[i+1][j] = 0;
					ans = Math.max(ans, a[i][j]);
				}
			}
		}
		if (flag) {
			moveUp(a);
		}
		
		return flag;
	}
	
	public static boolean moveUp(int[][] a) {
		boolean ret = false;
		
		for (int i=1; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (a[i][j] != 0) {
					int ny = i-1, nx = j;
					while (inRange(ny, nx) && a[ny][nx] == 0) {
						ret = true;
						a[ny][nx] = a[ny+1][nx];
						a[ny+1][nx] = 0;
						ny--;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static boolean mergeDown(int[][] a) {
		boolean flag = moveDown(a);
		for (int j=0; j<N; j++) {
			for (int i=N-1; i>0; i--) {
				if (a[i][j] != 0 && a[i][j] == a[i-1][j]) {
					flag = true;
					a[i][j] += a[i-1][j];
					a[i-1][j] = 0;
					ans = Math.max(ans, a[i][j]);
				}
			}
		}
		if (flag) {
			moveDown(a);
		}
		
		return flag;
	}
	
	public static boolean moveDown(int[][] a) {
		boolean ret = false;
		
		for (int i=N-2; i>=0; i--) {
			for (int j=0; j<N; j++) {
				if (a[i][j] != 0) {
					int ny = i+1, nx = j;
					while (inRange(ny, nx) && a[ny][nx] == 0) {
						ret = true;
						a[ny][nx] = a[ny-1][nx];
						a[ny-1][nx] = 0;
						ny++;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static boolean mergeRight(int[][] a) {
		boolean flag = moveRight(a);
		for (int i=0; i<N; i++) {
			for (int j=N-1; j>0; j--) {
				if (a[i][j] != 0 && a[i][j] == a[i][j-1]) {
					flag = true;
					a[i][j] += a[i][j-1];
					a[i][j-1] = 0;
					ans = Math.max(ans, a[i][j]);
				}
			}
		}
		if (flag) {
			moveRight(a);
		}
		
		return flag;
	}
	
	public static boolean moveRight(int[][] a) {
		boolean ret = false;
		
		for (int j=N-2; j>=0; j--) {
			for (int i=0; i<N; i++) {
				if (a[i][j] != 0) {
					int ny = i, nx = j+1;
					while (inRange(ny, nx) && a[ny][nx] == 0) {
						ret = true;
						a[ny][nx] = a[ny][nx-1];
						a[ny][nx-1] = 0;
						nx++;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static boolean mergeLeft(int[][] a) {
		boolean flag = moveLeft(a);
		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {
				if (a[i][j] != 0 && a[i][j] == a[i][j+1]) {
					flag = true;
					a[i][j] += a[i][j+1];
					a[i][j+1] = 0;
					ans = Math.max(ans, a[i][j]);
				}
			}
		}
		if (flag) {
			moveLeft(a);
		}
		
		return flag;
	}
	
	public static boolean moveLeft(int[][] a) {
		boolean ret = false;
		
		for (int j=1; j<N; j++) {
			for (int i=0; i<N; i++) {
				if (a[i][j] != 0) {
					int ny = i, nx = j-1;
					while (inRange(ny, nx) && a[ny][nx] == 0) {
						ret = true;
						a[ny][nx] = a[ny][nx+1];
						a[ny][nx+1] = 0;
						nx--;
					}
				}
			}
		}
		
		return ret;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public static void printArray(int[][] a) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		int[][] a = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				a[i][j] = sc.nextInt();
				ans = Math.max(ans, a[i][j]);
			}
		}	
		
		go(a, 0);
		
		System.out.println(ans);
	}
}
