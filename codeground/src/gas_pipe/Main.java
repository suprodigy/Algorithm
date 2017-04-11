package gas_pipe;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int R, C;
	public static char[][] a;
	public static boolean[][] checked;
	public static int ansY, ansX;
	public static char what = '?';
	public static boolean isEnded;
	
	public static int[] dy = {0, 1, 0, -1};
	public static int[] dx = {1, 0, -1, 0};
	public static char[] p = {'|', '-', '1', '2', '3', '4', '+'};
	
	public static void go(int y, int x, int dir) {
		if (!inRange(y, x)) {
			return;
		}
		
		checked[y][x] = true;
		
//		System.out.println(y + ", " + x);
//		print();
		
		if (a[y][x] == 'Z') {
			if (isAnswer()) {
				isEnded = true;
			} else {
				checked[y][x] = false;
			}
			return;
		}
		
		if (dir == -1) {
			for (int k=0; k<4; k++) {
				int ny = y + dy[k], nx = x + dx[k];
				
				if (!inRange(ny, nx)) {
					continue;
				}
				
				if (a[ny][nx] != '.') {
					int nextDir = k;
					go(ny, nx, nextDir);
					if (isEnded) {
						return;
					}
				}
			}
			for (int k=0; k<4; k++) {
				int ny = y + dy[k], nx = x + dx[k];
				go(ny, nx, k);
				if(isEnded) {
					return;
				}
			}
		} else if (a[y][x] == '.'){
			if (what != '?') {
				checked[y][x] = false;
				return;
			}
			
			for (int k=0; k<p.length; k++) {
				a[y][x] = p[k];
				ansY = y; ansX = x; what = p[k];
				go(y, x, dir);
				if (isEnded) {
					return;
				}
				a[y][x] = '.';
				what = '?';
			}
		} else {
			int nextDir = pipeCheck(y, x, dir);
			if (nextDir == -1) {
				return;
			}
			int ny = y + dy[nextDir], nx = x + dx[nextDir];
			go(ny, nx, nextDir);
			if(isEnded) {
				return;
			}
		}
		
		checked[y][x] = false;
	}
	
	public static boolean isAnswer() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (a[i][j] != '.' && !checked[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int pipeCheck(int y, int x, int dir) {	
		switch(a[y][x]) {
		case '|':
			if (dir == 1 || dir == 3) {
				return dir;
			} else {
				return -1;
			}
		case '-':
			if (dir == 0 || dir == 2) {
				return dir;
			} else {
				return -1;
			}
		case '+':
			return dir;
		case '1':
			if (dir == 2) {
				return 1;
			} else if (dir == 3) {
				return 0;
			} else {
				return -1;
			}
		case '2':
			if (dir == 1) {
				return 0;
			} else if (dir == 2) {
				return 3;
			} else {
				return -1;
			}
		case '3':
			if (dir == 1) {
				return 2;
			} else if (dir == 0) {
				return 3;
			} else {
				return -1;
			}
		case '4':
			if (dir == 0) {
				return 1;
			} else if (dir == 3) {
				return 2;
			} else {
				return -1;
			}
		default:
			return -1;
		}
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<R && 0<=x && x<C);
	}
	
	public static void print() {
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(checked[i][j] ? 1 : 0);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt(); C = sc.nextInt();
		a = new char[R][C];
		checked = new boolean[R][C];
		
		int y=0, x=0;
		for (int i=0; i<R; i++) {
			String str = sc.next();
			for (int j=0; j<C; j++) {
				a[i][j] = str.charAt(j);
				if (a[i][j] == 'M') {
					checked[i][j] = true;
					y = i; x = j;
				} else if (a[i][j] == 'Z') {
					checked[i][j] = true;
				}
			}
		}
		
		go(y, x, -1);
		System.out.println((ansY+1) + " " + (ansX+1) + " " + what);
	}
}
