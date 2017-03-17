package good_number;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Controller {
	private static final int MIN_NUM = -100000;
	private static final int MAX_NUM = 100000;
	
	private Set<Integer> set1;
	private Set<Integer> set2;
	
	public Controller() {
		set1 = new HashSet<>();
		set2 = new HashSet<>();
	}
	
	public boolean isGoodNumber(int num) {
		for (Integer i : set1) {
			if (set2.contains(num - i)) {
				return true;
			}
		}
		return false;
	}
	
	public void updateSets(int num) {
		set1.add(num);
		
		for (Integer i : set1) {
			if (i + num <= MAX_NUM && i + num >= MIN_NUM) {
				set2.add(i + num);
			}
		}
	}
}

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC ; test_case++) {
			Controller controller = new Controller();
			
			int N = sc.nextInt();
			
			int ans = 0;
			for (int i=0; i<N; i++) {
				int num = sc.nextInt();
				if (controller.isGoodNumber(num)) {
					ans++;
				}
				controller.updateSets(num);
			}
			
			System.out.println("Case #" + test_case);
			System.out.println(ans);
		}
	}
}
