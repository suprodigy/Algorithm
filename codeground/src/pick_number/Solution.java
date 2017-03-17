package pick_number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int TC;
		int test_case;
		
		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			int N;
			N = sc.nextInt();
			
			Map<Integer, Integer> map = new HashMap<>();
			
			for (int i=0; i<N; i++) {
				int a;
				a = sc.nextInt();
				if (map.containsKey(a)) {
					map.put(a, map.get(a) + 1);
				} else {
					map.put(a, 1);
				}
			}
			
			List<Integer> oddList = new ArrayList<Integer>();
			
			for (int key : map.keySet()) {
				if (map.get(key) % 2 == 1) {
					oddList.add(key);
				}
			}
			
			int ans = 0;
			for (int number : oddList) {
				ans ^= number;
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(ans);
		}
		
		sc.close();
	}
}
