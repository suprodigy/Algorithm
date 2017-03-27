package pieces_of_paper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int N, M, ans=0;
	public static int[][] a, d;
	
	public static void go(int i, int j, int div) {
		if (i == N-1 && j == M) {
			ans = Math.max(ans, getScore());
			return;
		}
		
		if (j == M) {
			go (i+1, 0, div);
			return;
		}
		
		// 왼쪽으로 합치기
		if (j != 0) {
			d[i][j] = d[i][j-1];
			go(i, j+1, div);
		}
		
		// 위쪽으로 합치기
		if (i != 0) {
			d[i][j] = d[i-1][j];
			go(i, j+1, div);
		}
		
		// 새로운 구간 생성
		d[i][j] = div++;
		go(i, j+1, div);
	}
	
	public static int getScore() {
		int ret = 0;
		boolean[][] checked = new boolean[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				checked[i][j] = false;
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!checked[i][j]) {
					checked[i][j] = true;
					List<Integer> list = new ArrayList<>();
					list.add(a[i][j]);
					int score = 0, ni, nj;
					
					// 오른쪽으로
					ni = i; nj = j+1;
					while(inRange(ni, nj) && !checked[ni][nj] && d[i][j] == d[ni][nj]) {
						checked[ni][nj] = true;
						list.add(a[ni][nj]);
						nj++;
					}
					score = Math.max(score, count(list));
					
					ni = i+1; nj = j;
					list.clear();
					list.add(a[i][j]);
					while(inRange(ni, nj) && !checked[ni][nj] && d[i][j] == d[ni][nj]) {
						checked[ni][nj] = true;
						list.add(a[ni][nj]);
						ni++;
					}
					score = Math.max(score, count(list));
					
					ret += score;
				}
			}
		}
		
		return ret;
	}
	
	public static boolean inRange(int i, int j) {
		return (0<=i && i<N && 0<=j && j<M);
	}
	
	public static int count(List<Integer> list) {
		int ret = 0;
		int len = list.size();
		
		for (int i=0; i<len; i++) {
			ret += (list.get(i) * (int)Math.pow(10, len-1-i));
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); M = sc.nextInt();
		a = new int[N][M];
		d = new int[N][M];
		for (int i=0; i<N; i++) {
			String str = sc.next();
			for (int j=0; j<M; j++) {
				a[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		go(0, 0, 0);
		
		System.out.println(ans);
	}
}
