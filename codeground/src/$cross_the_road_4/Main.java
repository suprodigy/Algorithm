package $cross_the_road_4;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class ChickenInfo implements Comparable<ChickenInfo>{
	int time;
	int number;
	int helpCnt;
	
	public ChickenInfo(int time, int number, int helpCnt) {
		this.time = time;
		this.number = number;
		this.helpCnt = helpCnt;
	}
	
	@Override
	public int compareTo(ChickenInfo comp) {
		// TODO Auto-generated method stub
		if (this.time == comp.time) {
			return this.number - comp.number;
		} else {
			return this.time - comp.time;
		}
	}
}

class CowInfo {
	int from, to;
	
	public CowInfo(int from, int to) {
		this.from = from;
		this.to = to;
	}
}

public class Main {
	public static final int INF = 1000000000;
	
	public static int C, N;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		C = sc.nextInt();
		N = sc.nextInt();
		
		boolean[] checked = new boolean[C+1];
		
		TreeSet<ChickenInfo> chickenSet = new TreeSet<>();
		for (int i=1; i<=C; i++) {
			int ti = sc.nextInt();
			chickenSet.add(new ChickenInfo(ti, i, 0));
		}
		
		List<CowInfo> cowList = new ArrayList<>();
		for (int i=1; i<=N; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			Set<ChickenInfo> subSet = chickenSet.subSet(new ChickenInfo(from, 0, 0), 
					true, new ChickenInfo(to, INF, 0), true);
			for (ChickenInfo now : subSet) {
				now.helpCnt++;
			}
			
			cowList.add(new CowInfo(from, to));
		}
		
		int ans = 0;
		for (int i=0; i<N; i++) {
			int from = cowList.get(i).from;
			int to = cowList.get(i).to;
			
			Set<ChickenInfo> subSet = chickenSet.subSet(new ChickenInfo(from, 0, 0), 
					true, new ChickenInfo(to, INF, 0), true);
			
			List<ChickenInfo> list = new ArrayList<>(subSet);
			Collections.sort(list, new Comparator<ChickenInfo>(){
				@Override
				public int compare(ChickenInfo o1, ChickenInfo o2) {
					return o1.helpCnt - o2.helpCnt;
				}
			});
			
			for (ChickenInfo now : list) {
				int idx = now.number;
				
				if (!checked[idx]) {
					ans++;
					checked[idx] = true;
					break;
				} 
			}
			
			if (ans == C) {
				break;
			}
		}
		
		System.out.println(ans);
	}
}
