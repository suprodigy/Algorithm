package route_on_the_chessboard;

import java.io.FileInputStream;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static final int MOD = 1000000007;
	public static int N, M;
	public static Set<Map.Entry<Integer, Integer>> obstacles;
	
	public static int go(int y, int x) {
//		System.out.println(y + ", " + x);
		
		if (outOfRange(y, x)) {
			return 0;
		} else if (obstacles.contains(new AbstractMap.SimpleEntry<>(y, x))) {
			return 0;
		} else if (y == N && x == M) {
			return 1;
		}
		
		long ret = 0;
		ret += go(y, x+1);
		ret %= MOD;
		ret += go(y+1, x);
		ret %= MOD;
		
		return (int)ret;
	}
	
	public static boolean outOfRange(int y, int x) {
		return !(y >= 1 && y <= N && x >= 1 && x <= M);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			obstacles = new HashSet<>();
			
			int K = sc.nextInt();
			for (int i=0; i<K; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				Map.Entry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<>(y, x);
				obstacles.add(newPair);
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(go(1, 1));
		}
	}
}
