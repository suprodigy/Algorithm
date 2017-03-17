package dart_game;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class DartBoard {
	private int A, B, C, D, E;
	
	private ArrayList<Integer> mScores = new ArrayList<>(Arrays.asList(20, 1, 18, 4, 13, 6, 10, 15, 2, 17, 
													3, 19, 7, 16, 8, 11, 14, 9, 12, 5));
	
	public DartBoard(int A, int B, int C, int D, int E) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.E = E;
	}
	
	private double getDegree(int x, int y) {
		return Math.atan2(x, y) * (180 / Math.PI);
	}
	
	private int getScope(double degree) {
		if (degree < 0) {
			degree += 360.0;
		}
		
		int idx = (int) Math.round(degree / 18.0);
		if (idx == 20) idx = 0;
		return mScores.get(idx);
	}
	
	private double getDist(int x, int y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public int getScore(int x, int y) {
		// 임의의 점 x와 기준점 사시의 각도를 구한다.
		double degree = getDegree(x, y);
		int scope = getScope(degree);
		
		double dist = getDist(x, y);
		
		if (dist <= A) {
			return 50;
		} else if (dist >= B && dist <= C) {
			return scope * 3;
		} else if (dist >= D && dist <= E) {
			return scope * 2;
		} else if (dist > E) {
			return 0;
		} else {
			return scope;
		}
	}
}

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC;
		int test_case;
		
		TC = sc.nextInt();
		for (test_case=1; test_case<=TC; test_case++) {
			DartBoard myBoard = new DartBoard(sc.nextInt(), sc.nextInt(), sc.nextInt(), 
					sc.nextInt(), sc.nextInt());
			
			int ans = 0;
			int N = sc.nextInt();
			for (int i=0; i<N; i++) {
				int x = sc.nextInt(), y = sc.nextInt();
				ans += myBoard.getScore(x, y);
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(ans);
		}		
	}
}
