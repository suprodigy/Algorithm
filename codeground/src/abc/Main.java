package abc;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int N, K;
	public static int[] d = new int[3];
	
	public static String go(String now, int len, int cnt) {
		if (len == N && cnt == K) {
			return now;
		}
		
		if (cnt > K) {
			return null;
		}
		
		String ret;
		for (int i=0; i<3; i++) {
			String next = now + (char)('A' + i);
			int nextCnt = cnt;
			for (int j=i-1; j>=0; j--) {
				cnt += d[j];
			}
			d[i] += 1;
			ret = go(next, len+1, nextCnt);
			if (!ret.equals(null)) {
				return ret;
			}
			d[i] -= 1;
		}
		
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); K = sc.nextInt();
		
		Arrays.fill(d, 0);
		String ans = go("", 0, 0);
		if (ans == null) {
			ans = "-1";
		}
		System.out.println(ans);
	}
}
