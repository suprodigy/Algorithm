package cross_the_road_3;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Info implements Comparable<Info>{
	int start, duration;
	
	public Info (int start, int duration) {
		this.start = start;
		this.duration = duration;
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		if (this.start == comp.start) {
			return this.duration - comp.duration;
		} else {
			return this.start - comp.start;
		}
		
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		List<Info> list = new ArrayList<>();
		for (int i=0; i<N; i++) {
			list.add(new Info(sc.nextInt(), sc.nextInt()));
		}
		
		Collections.sort(list);
		
		int ans = 0;
		for (Info now : list) {
			if (now.start > ans) {
				ans = now.start;
			}
			ans += now.duration;
		}
		System.out.println(ans);
	}
}
