package jailbreak;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Pair implements Comparable<Pair> {
	public int y, x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public int compareTo(Pair comp) {		
		// TODO Auto-generated method stub
		if (this.y != comp.y) {
			return (this.y - comp.y);
		} else {
			return (this.x - comp.x);
		}
	}
}

class Info implements Comparable<Info> {
	public int y1, x1;
	public int y2, x2;
	public Set<Pair> set;
	
	public Info(int y1, int x1, int y2, int x2, Set<Pair> set) {
		this.y1 = y1; this.x1 = x1;
		this.y2 = y2; this.x2 = x2;
		this.set = new TreeSet<>(set);
	}

	@Override
	public int compareTo(Info comp) {
		// TODO Auto-generated method stub
		return (this.set.size() - comp.set.size());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return y1 + " " + x1 + " " + y2 + " " + x2 + " " + set.size();
	}
	
}

public class Main {
	public static int N, M;
	public static char[][] a;
	public static int[] dy = new int[]{0, 0, -1, 1};
	public static int[] dx = new int[]{1, -1, 0, 0};
	
	public static int go(int y1, int x1, int y2, int x2) {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		
		boolean[][] checked1 = new boolean[N+2][M+2];
		boolean[][] checked2 = new boolean[N+2][M+2];
		
		checked1[y1][x1] = true;
		checked2[y2][x2] = true;
		
		pq.offer(new Info(y1, x1, y2, x2, new HashSet<>()));
		
		while (!pq.isEmpty()) {
			Info now = pq.poll();
			System.out.println(now);
			
			if (!inRange(now.y1, now.x1) && !inRange(now.y2, now.x2)) {
				return now.set.size();
			}
			
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					Set<Pair> temp = new TreeSet<>(now.set);
					
					int ny1 = now.y1 + dy[i], nx1 = now.x1 + dx[i];	
					int ny2 = now.y2 + dy[j], nx2 = now.x2 + dx[j];
					
					if (!inRange(now.y1, now.x1)) {
						ny1 = now.y1; nx1 = now.x1;
					} else if (!checked1[ny1][nx1] && a[ny1][nx1] != '*'){
						if (a[ny1][nx1] == '#') {
							if (!temp.contains(new Pair(ny1, nx1))) {
								temp.add(new Pair(ny1, nx1));
							}
						}
					} else {
						continue;
					}

					if (!inRange(now.y2, now.x2)) {
						ny2 = now.y2; nx2 = now.x2;
					} else if (!checked2[ny2][nx2] && a[ny2][nx2] != '*') {
						if (a[ny2][nx2] == '#') {
							if (!temp.contains(new Pair(ny2, nx2))) {
								temp.add(new Pair(ny2, nx2));
							}
						}
					} else {
						continue;
					}
					
					if (checked1[ny1][nx1] && checked2[ny2][nx2]) {
						continue;
					}
					
					checked1[ny1][nx1] = true;
					checked2[ny2][nx2] = true;
					pq.offer(new Info(ny1, nx1, ny2, nx2, temp));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean inRange(int y, int x) {
		return (1<=y && y<N+1 && 1<=x && x<M+1);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t=0; t<T; t++) {
			N = sc.nextInt(); M = sc.nextInt();
			a = new char[N+2][M+2];
			for (int i=0; i<N+2; i++) {
				Arrays.fill(a[i], '.');
			}
			
			int y1 = -1, x1 = -1, y2 = -1, x2 = -1;
			for (int i=1; i<N+1; i++) {
				String str = sc.next();
				for (int j=1; j<M+1; j++) {
					a[i][j] = str.charAt(j-1);
					if (a[i][j] == '$') {
						if (y1 == -1) {
							y1 = i; x1 = j;
						} else {
							y2 = i; x2 = j;
						}
					}
				}
			}
			
			System.out.println(go(y1, x1, y2, x2));
		}
	}
}
