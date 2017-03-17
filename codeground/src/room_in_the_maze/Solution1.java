package room_in_the_maze;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Room {
	private int N;
	private ArrayList<ArrayList<Integer>> room;
	
	public Room(int N) {
		this.N = N;
		room = new ArrayList<ArrayList<Integer>>();
		for (int i=0; i<N; i++) {
			room.add(new ArrayList<Integer>());
			for(int j=0; j<N; j++) {
				room.get(i).add(0);
			}
		}
		
		int y =0, x = 0;
		int i = 1;
		int moveY = -1, moveX = 1;
		boolean flag = false;
		int cnt = 1;
		
		while (i <= N * N) {		
			room.get(y).set(x, i++);
			for (int j=0; j<cnt-1; j++) {
				y += moveY; x += moveX;
				if (!inRange(y, x)) {
					y -= moveY; x -= moveX;
					break;
				}
				room.get(y).set(x, i++);
			}
			
			if (!flag) {
				if (inRange(y, x+1)) x++;
				else y++;
			} else {
				if (inRange(y+1, x)) y++;
				else x++;
			}
			
			cnt++;
			moveY *= -1; moveX *= -1;
			flag = !flag;
		}
	}
	
	private boolean inRange(int y, int x) {
		return (0<=y && y<N && 0<=x && x<N);
	}
	
	public long travel(String moveString) {
		long ans = 1;
		
		int y = 0, x = 0;
		for (int i=0; i<moveString.length(); i++) {
			List<Integer> nextMove = transMove(moveString.charAt(i));
			y += nextMove.get(0); x += nextMove.get(1);
			ans += room.get(y).get(x);
		}
		
		return ans;
	}
	
	private List<Integer> transMove(char move) {
		List<Integer> ret = new ArrayList<>(Arrays.asList(0, 0));
		
		switch(move) {
		case 'D':
			ret.set(0, 1);
			ret.set(1, 0);
			break;
		case 'U':
			ret.set(0, -1);
			ret.set(1, 0);
			break;
		case 'R':
			ret.set(0, 0);
			ret.set(1, 1);
			break;
		case 'L':
			ret.set(0, 0);
			ret.set(1, -1);
			break;
		}
		
		return ret;
	}
}

public class Solution1 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			String moveString = sc.next();
			
			Room myRoom = new Room(N);
			
			System.out.println("Case #" + test_case);
			System.out.println(myRoom.travel(moveString));
		}
	}
}
