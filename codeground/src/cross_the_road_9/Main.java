package cross_the_road_9;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		boolean[] checked = new boolean[N+1];
		List<Integer> list = new ArrayList<>();
		
		int ans = 0;
		for (int i=0; i<2*N; i++) {
			int now = sc.nextInt();
			
			if (!checked[now]) {
				checked[now] = true;
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
