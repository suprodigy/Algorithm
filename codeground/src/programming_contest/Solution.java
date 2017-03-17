package programming_contest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC;
		int test_case;
		
		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt();
			List<Integer> list = new ArrayList<>();
			for (int i=0; i<N; i++) {
				list.add(sc.nextInt());
			}
			
			Collections.sort(list);
			
			int max = 0;
			for (int i=0; i<N; i++) {
				int now = list.get(i);
				list.set(i, now + N - i);
				max = Math.max(max, list.get(i));
			}
			
			int ans = 0;
			for (int i=0; i<N; i++) {
				if (list.get(i) >= max) {
					ans++;
				}
				list.set(i, list.get(i) - (1 + i));
				if (i != N-1) {
					list.set(i+1, list.get(i+1) + (1 + i));
					max = Math.max(max, list.get(i+1));
				}
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(ans);
		}
	}
}
