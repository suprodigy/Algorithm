package room_in_the_maze;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Room {
	private int N;
	private ArrayList<Long> startNums = new ArrayList<>();
	
	public Room(int N) {
		this.N = N;
		
		long num = 1;
		long sum = 0;
		
		for (int i=0; i<2*N-1; i++) {
			startNums.add(num);
			
			if (i < N) sum++;
			else sum--;
			num += sum;
		}
		
//		// test
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<N; j++) {
//				System.out.print(getRoomNumber(i, j) + " ");
//			}
//			System.out.println();
//		}
	}
	
	public long travel(String moveString) {
		long ans = 1;
		
		int y = 0, x = 0;
		for (int i=0; i<moveString.length(); i++) {
			List<Integer> nextMove = transMove(moveString.charAt(i));
			y += nextMove.get(0); x += nextMove.get(1);
			ans += getRoomNumber(y, x);
		}
		
		return ans;
	}
	
	private long getRoomNumber(int y, int x) {
		// 1. 몇 번째 대각선에 있는지 알아냄.
		int cnt = x + y;
		
		// 2. 그 대각선의 시작 인덱스를 알아냄.
		int startY = 0, startX = 0;
		if (cnt % 2 == 0) {
			if (cnt >= N) {
				startY = N-1; 
				startX = cnt - (N-1);
			}
			else {
				startY = cnt;
				startX = 0;
			}
		} else {
			if (cnt >= N) {
				startY = cnt - (N-1);
				startX = N-1;
			} else {
				startY = 0;
				startX = cnt;
			}
		}
		
		// 3. 대각선에서 몇 번째 위치에 있는지 알아냄.
		// 대각선 번호가 홀수이면 우상단에서 좌하단 방향으로 내려가고,
		// 대각선 번호가 짝수이면 좌하단에서 우상단 방향으로 올라감.
		int pos = 0;
		if (cnt % 2 != 0) {
			pos = y - startY;
		} else {
			pos = startY - y;
		}
		
		return startNums.get(cnt) + pos;
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

public class Solution2 {
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
