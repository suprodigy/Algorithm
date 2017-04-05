package two_weight;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Info implements Comparable<Info>{
	public int pos, dist1, dist2;
	public List<Boolean> checked;
	
	public Info(int pos, int dist1, int dist2, List<Boolean> checked) {
		this.pos = pos;
		this.dist1 = dist1;
		this.dist2 = dist2;
		this.checked = new ArrayList<>(checked);
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return (this.dist1 * this.dist2 - comp.dist1 * comp.dist2);
	}
	
	
}

public class Main {
	public static int N;
	public static int[][][] a;
	
	public static int go() {
		List<Boolean> checked = new ArrayList<>(N);
		for (int i=0; i<N; i++) {
			checked.add(false);
		}
		
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, 0, 0, checked));
		
		int ans = 987654312;
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			
			if (now.pos == 1) {
				return (now.dist1 * now.dist2);
			}
			
			if (now.checked.get(now.pos)) {
				continue;
			}
			now.checked.set(now.pos, true);
			
			for (int to=0; to<N; to++) {
				int nextDist1 = a[0][now.pos][to];
				int nextDist2 = a[1][now.pos][to];
				if (nextDist1 != -1 && !now.checked.get(to)) {
					pq.offer(new Info(to, now.dist1 + nextDist1, now.dist2 + nextDist2, now.checked));
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		a = new int[2][N][N];
		
		for (int k=0; k<2; k++) {
			for (int i=0; i<N; i++) {
				String str = sc.next();
				for (int j=0; j<N; j++) {
					if (str.charAt(j) == '.') {
						a[k][i][j] = -1;
					} else {
						a[k][i][j] = Character.getNumericValue(str.charAt(j));
					}
				}
			}
		}
		
		int ans = go();
		System.out.println(ans);
	}
}
