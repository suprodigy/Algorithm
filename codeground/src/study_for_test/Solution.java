package study_for_test;

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
			int N, K;
			
			N = sc.nextInt();
			K = sc.nextInt();
			
			List<Integer> list = new ArrayList<>();
			for (int i=0; i<N; i++) {
				list.add(sc.nextInt());
			}
			
			Collections.sort(list, Collections.reverseOrder());
			
			int ans = 0;
			for (int i=0; i<K; i++) {
				ans += list.get(i);
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(ans);
		}
	}
}
