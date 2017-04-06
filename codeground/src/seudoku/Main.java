package seudoku;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int[][] a = new int[9][9];
	public static boolean ended = false;
	
	public static void go(int i, int j) {
		if (i == 8 && j == 9) {
			ended = true;
			print();
			return;
		}
		
		if (j == 9) {
			go(i+1, 0);
		} else if (a[i][j] != 0) {
			go(i, j+1);
		} else {
			boolean[] checked = new boolean[10];
			
			// 가로 검사
			for (int k=0; k<9; k++) {
				checked[a[i][k]] = true;
			}
			
			// 세로 검사
			for (int k=0; k<9; k++) {
				checked[a[k][j]] = true;
			}
			
			// 작은 네모 검사
			int row =  3 * (i/3), col = 3 * (j/3);
			for (int k=0; k<3; k++) {
				for (int l=0; l<3; l++) {
					checked[a[row+k][col+l]] = true;
				}
			}
			
			for (int k=1; k<10; k++) {
				if (!checked[k]) {
					a[i][j] = k;
					go(i, j+1);
					a[i][j] = 0;
					
					if (ended) {
						break;
					}
				}
			}
		}
		
	}
	
	public static void print() {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		go(0, 0);
	}
}
