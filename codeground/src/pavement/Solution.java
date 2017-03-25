package pavement;

import java.io.FileInputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pair implements Comparable {
	public Integer first;
	public Integer second;
	public Integer third;
	
	public Pair(int first, int second, int third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		Pair cmp = (Pair)obj;
		return this.second.compareTo(cmp.second);
	}
	
	
}

public class Solution {
	private static final int INF = 123456789;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(), M = sc.nextInt(), K = sc.nextInt();
		
		List<List<Map.Entry<Integer, Integer>>> adj = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();	
			adj.get(u).add(new AbstractMap.SimpleEntry(v, w));
			adj.get(v).add(new AbstractMap.SimpleEntry(u, w));
		}
		
		int[][] d = new int[N+1][K+1];
		for (int i=0; i<=N; i++) {
			for (int j=0; j<=K; j++) {
				d[i][j] = INF;
			}
		}
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.offer(new Pair(1, 0, 0));
		
		while (!pq.isEmpty()) {
			Pair temp = pq.poll();
			int now = temp.first;
			int dist = temp.second;
			int pavement = temp.third;
			
			d[now][pavement] = Math.min(d[now][pavement], dist);
			
			for (int i=0; i<adj.get(now).size(); i++) {
				Map.Entry<Integer, Integer> nextInfo = adj.get(now).get(i);
				int next = nextInfo.getKey();
				int nextDist = nextInfo.getValue();
				
				if (pavement < K && 
						(d[next][pavement+1] == INF || dist < d[next][pavement+1])) {
					pq.offer(new Pair(next, dist, pavement+1));
				}
				
				if (d[next][pavement] == INF || dist < d[next][pavement]){
					pq.offer(new Pair(next, dist + nextDist, pavement));
				}
			}
		}
		
		int ans = INF;
		for (int i=0; i<=K; i++) {
			ans = Math.min(ans, d[N][i]);
		}
		
		System.out.println(ans);
	}
}
