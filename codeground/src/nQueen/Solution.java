package nQueen;

import java.util.Scanner;

public class Solution {
	public static int N;
	
	public static int go(boolean[][] board, int row) {
		if (row == N) {
			return 1;
		}
		
		int ret = 0;
		for (int i=0; i<N; i++) {
			if (Promising(board, row, i)) {
				board[row][i] = true;
				ret += go(board, row+1);
				board[row][i] = false;
			}
		}
		
		return ret;
	}
	
	private static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	private static boolean Promising(boolean[][] board, int y, int x) {
		for (int i=0; i<y; i++) {
			if (board[i][x]) {
				return false;
			}
		}
		
		int ny = y, nx = x;
		while(inRange(ny, nx)) {
			if (board[ny][nx]) return false;
			ny--; nx++;
		}
		
		ny = y; nx = x;
		while(inRange(ny, nx)) {
			if(board[ny][nx]) return false;
			ny--; nx--;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		boolean[][] board = new boolean[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				board[i][j] = false;
			}
		}
		
		int ans = go(board, 0);
		System.out.println(ans);
	}
}
