package snake2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int L;
	public static int[] dx = new int[]{1, 0, -1, 0};
	public static int[] dy = new int[]{0, 1, 0, -1};
	
	public static boolean inRange(int x, int y) {
		return (-L<=x && x<=L && -L<=y && y<=L);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt(); 
		int N = sc.nextInt();
		int[] d = new int[2];
		
		int x = 0, y = 0, ans = 1, dir = 0;
		char prev1 = '0', prev2 = '0';
		for (int i=0; i<N; i++) {
			int a = sc.nextInt(); 
			char b = sc.next().charAt(0);
			
			int nx = x + dx[dir], ny = y + dy[dir];
			
			
		}
		
		System.out.println(ans);
	}
}
