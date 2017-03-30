package snake;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Pair {
	public int y;
	public int x;
	
	public Pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	public static int N;
	public static boolean[][] a;
	public static Map<Integer, Character> trans;
	
	public static int[] dy = new int[]{0, 1, 0, -1};
	public static int[] dx = new int[]{1, 0, -1, 0};
	
	public static int go() {
		boolean[][] checked = new boolean[N][N];
		for (int i=0; i<N; i++) {
			Arrays.fill(checked[i], false);
		}
		
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));
		checked[0][0] = true;
		
		int second = 0, ny = 0, nx = 0, dir = 0;
		while (true) {
			if (trans.containsKey(second)) {
				char temp = trans.get(second);
				switch (temp) {
				case 'L':
					dir--;
					if (dir < 0) {
						dir += 4;
					}
					break;
				case 'D':
					dir = (dir + 1) % 4;
				}
			}
			
			ny += dy[dir]; nx += dx[dir]; second++; 
			
			if (!inRange(ny, nx) || checked[ny][nx]) {
				break;
			}
			
			if (!a[ny][nx]) {
				Pair temp = q.poll();
				checked[temp.y][temp.x] = false;
			} else {
				a[ny][nx] = false;
			}
			
			q.offer(new Pair(ny, nx));
			checked[ny][nx] = true;
		}
		
		return second;
	}
	
	public static boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
	
		N = sc.nextInt();
		a = new boolean[N][N];
		for (int i=0; i<N; i++) {
			Arrays.fill(a[i], false);
		}
		
		int K = sc.nextInt();
		for (int i=0; i<K; i++) {
			int y = sc.nextInt()-1, x = sc.nextInt()-1;
			a[y][x] = true;
		}
		
		trans = new HashMap<>();
		int L = sc.nextInt();
		for (int i=0; i<L; i++) {
			int s = sc.nextInt();
			char c = sc.next().charAt(0);
			trans.put(s, c);
		}
		
		System.out.println(go());
	}
}
