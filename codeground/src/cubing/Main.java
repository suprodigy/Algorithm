package cubing;

import java.util.Scanner;

class Cube {
	public static final char[] SEC = new char[]{'o', 'w', 'g', 'b', 'y', 'r'};
	
	public char[][][] board = new char[6][3][3];
	
	public Cube() {
		for (int k=0; k<6; k++) {
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					board[k][i][j] = SEC[k];
				}
			}
		}
	}
	
	public void rotate(char face, char dir) {
		switch (face) {
		case 'U':
			rotateFace(1, dir);
			rotateUp(dir);
			break;
		case 'D':
			rotateFace(4, dir);
			rotateDown(dir);
			break;
		case 'F':
			rotateFace(5, dir);
			rotateFor(dir);
			break;
		case 'B':
			rotateFace(0, dir);
			rotateBack(dir);
			break;
		case 'L':
			rotateFace(2, dir);
			rotateLeft(dir);
			break;
		case 'R':
			rotateFace(3, dir);
			rotateRight(dir);
			break;
		}
	}
	
	public void rotateFace(int nth, char dir) {
		char[][] temp = new char[3][3];
		temp[1][1] = board[nth][1][1];
		
		switch (dir) {
		case '+':
			copy_reverse(temp[0], board[nth], 0);
			copy(temp, board[nth][2], 0);
			copy_reverse(temp[2], board[nth], 2);
			copy(temp, board[nth][0], 2);
			break;
		case '-':
			copy(temp[0], board[nth], 2);
			copy_reverse(temp, board[nth][2], 2);
			copy(temp[2], board[nth], 0);
			copy_reverse(temp, board[nth][0], 0);
			break;
		}
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				board[nth][i][j] = temp[i][j];
			}
		}
	}
	
	public void rotateUp(char dir) {
		switch (dir) {
		case '+':
			char[][] temp1 = new char[3][3];
			copy(temp1, board[3], 0, 0);
			copy(board[3], board[0][2], 0);
			copy_reverse(board[0][2], board[2], 2);
			copy(board[2], board[5][0], 2);
			copy_reverse(board[5][0], temp1, 0);
			break;
		case '-':
			char[][] temp2 = new char[3][3];
			copy(temp2, board[3], 0, 0);
			copy_reverse(board[3], board[5][0], 0);
			copy(board[5][0], board[2], 2);
			copy_reverse(board[2], board[0][2], 2);
			copy(board[0][2], temp2, 0);
			break;
		}
	}
	
	public void rotateDown(char dir) {
		switch (dir) {
		case '+':
			char[] temp1 = new char[3];
			copy(temp1, board[5][2]);
			copy(board[5][2], board[2], 0);
			copy_reverse(board[2], board[0][0], 0);
			copy(board[0][0], board[3], 2);
			copy_reverse(board[3][2], temp1);
			break;
		case '-':
			char[] temp2 = new char[3];
			copy(temp2, board[5][2]);
			copy_reverse(board[5][2], board[3], 2);
			copy(board[3], board[0][0], 2);
			copy_reverse(board[0][0], board[2], 0);
			copy(board[2], temp2, 0);
			break;
		}
	}
	
	public void rotateFor(char dir) {
		switch (dir) {
		case '+':
			char[] temp1 = new char[3];
			copy(temp1, board[3][2]);
			copy(board[3][2], board[1][2]);
			copy(board[1][2], board[2][2]);
			copy_reverse(board[2][2], board[4][0]);
			copy_reverse(board[4][0], temp1);
			break;
		case '-':
			char[] temp2 = new char[3];
			copy(temp2, board[2][2]);
			copy(board[2][2], board[1][2]);
			copy(board[1][2], board[3][2]);
			copy_reverse(board[3][2], board[4][0]);
			copy_reverse(board[4][0], temp2);
			break;
		}
	}
	
	public void rotateBack(char dir) {
		switch (dir) {
		case '+':
			char[] temp1 = new char[3];
			copy(temp1, board[2][0]);
			copy(board[2][0], board[1][0]);
			copy(board[1][0], board[3][0]);
			copy_reverse(board[3][0], board[4][2]);
			copy_reverse(board[4][2], temp1);
			break;
		case '-':
			char[] temp2 = new char[3];
			copy(temp2, board[3][0]);
			copy(board[3][0], board[1][0]);
			copy(board[1][0], board[2][0]);
			copy_reverse(board[2][0], board[4][2]);
			copy_reverse(board[4][2], temp2);
			break;
		}
	}
	
	public void rotateLeft(char dir) {
		switch (dir) {
		case '+':
			char[][] temp1 = new char[3][3];
			copy(temp1, board[0], 0, 0);
			copy(board[0], board[4], 0, 0);
			copy(board[4], board[5], 0, 0);
			copy(board[5], board[1], 0, 0);
			copy(board[1], temp1, 0, 0);
			break;
		case '-':
			char[][] temp2 = new char[3][3];
			copy(temp2, board[0], 0, 0);
			copy(board[0], board[1], 0, 0);
			copy(board[1], board[5], 0, 0);
			copy(board[5], board[4], 0, 0);
			copy(board[4], temp2, 0, 0);
			break;
		}
	}
	
	public void rotateRight(char dir) {
		switch (dir) {
		case '+':
			char[][] temp1 = new char[3][3];
			copy(temp1, board[0], 2, 2);
			copy(board[0], board[1], 2, 2);
			copy(board[1], board[5], 2, 2);
			copy(board[5], board[4], 2, 2);
			copy(board[4], temp1, 2, 2);
			break;
		case '-':
			char[][] temp2 = new char[3][3];
			copy(temp2, board[4], 2, 2);
			copy(board[4], board[5], 2, 2);
			copy(board[5], board[1], 2, 2);
			copy(board[1], board[0], 2, 2);
			copy(board[0], temp2, 2, 2);
			break;
		}
	}
	
	public void print() {
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(board[1][i][j]);
			}
			System.out.println();
		}
	}
	
	private void copy(char[] to, char[] from) {
		for (int i=0; i<3; i++) {
			to[i] = from[i];
		}
	}
	
	private void copy_reverse(char[] to, char[] from) {
		for (int i=0; i<3; i++) {
			to[2-i] = from[i];
		}
	}
	
	private void copy(char[][] to, char[][] from, int col1, int col2) {
		for (int i=0; i<3; i++) {
			to[i][col1] = from[i][col2];
		}
	}
	
//	private void copy_reverse(char[][] to, char[][] from, int col1, int col2) {
//		for (int i=0; i<3; i++) {
//			to[2-i][col1] = from[i][col2];
//		}
//	}
	
	private void copy(char[][] to, char[] from, int col) {
		for (int i=0; i<3; i++) {
			to[i][col] = from[i];
		}
	}
	
	private void copy_reverse(char[][] to, char[] from, int col) {
		for (int i=0; i<3; i++) {
			to[2-i][col] = from[i];
		}
	}
	
	private void copy(char[] to, char[][] from, int col) {
		for (int i=0; i<3; i++) {
			to[i] = from[i][col];
		}
	}
	
	private void copy_reverse(char[] to, char[][] from, int col) {
		for (int i=0; i<3; i++) {
			to[2-i] = from[i][col];
		}
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test=0; test<T; test++) {
			Cube cube = new Cube();
			int N = sc.nextInt();

			for (int i=0; i<N; i++) {
				String str = sc.next();
				char ch1 = str.charAt(0);
				char ch2 = str.charAt(1);
//				System.out.println(ch1 + ", " + ch2);
				cube.rotate(ch1, ch2);
//				cube.print();
//				System.out.println();
			}
			
			cube.print();
		}
	}
}
