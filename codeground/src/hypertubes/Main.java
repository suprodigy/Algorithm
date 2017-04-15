package hypertubes;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info>{
	int pos;
	int cnt;
	
	public Info(int pos, int cnt) {
		this.pos = pos;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return this.cnt - comp.cnt;
	}
}

public class Main {
	public static final int INF = 123456789;
	
	public static int N, M, K;
	public static List<List<Integer>> adj;
	public static int[] d;
	
	public static void go() {
		boolean[] checked = new boolean[N+M+1];
		PriorityQueue<Info> q = new PriorityQueue<>();
		q.offer(new Info(1, 1));
		
		while (!q.isEmpty()) {
			Info now = q.poll();
//			System.out.println(now.cnt);
			
			if (checked[now.pos]) {
				continue;
			}
			checked[now.pos] = true;
			
			int S = adj.get(now.pos).size();
			
			for (int i=0; i<S; i++) {
				int next = adj.get(now.pos).get(i);
				int nextCnt = now.cnt;
				
				if (next <= N) {
					nextCnt++;
				}
				
				if (nextCnt < d[next]) {
					d[next] = nextCnt;
					q.offer(new Info(next, nextCnt));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); K = sc.nextInt(); M = sc.nextInt();
		adj = new ArrayList<>();
		d = new int[N+M+1];
		for (int i=0; i<N+M+1; i++) {
			adj.add(new ArrayList<Integer>());
			d[i] = INF;
		}
		d[1] = 1;
		
		for (int i=1; i<=M; i++) {
			for (int j=0; j<K; j++) {
				int u = sc.nextInt();			
				adj.get(N+i).add(u);
				adj.get(u).add(N+i);
			}
		}
		
		go();
		System.out.println(d[N] == INF ? -1 : d[N]);
	}
}