package cross_the_road_2;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		boolean[] checked = new boolean[26];
		List<Character> list = new ArrayList<>();
		
		int ans = 0;
		String str = sc.next();
		for (int i=0; i<str.length(); i++) {
			char now = str.charAt(i);
			
			if (!checked[now - 'A']) {
				checked[now - 'A'] = true;
				list.add(now);
			} else {
				int idx = list.size() - 1;
				while (list.get(idx) != now) {
					ans++;
					idx--;
				}
				list.remove(idx);
			}
		}
		System.out.println(ans);
	}
}
