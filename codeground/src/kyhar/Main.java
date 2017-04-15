package kyhar;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static final int INF = 1234567890;
	
	public static int N, M;
	public static int[][] quantity;	 
	public static int[][] price;	
	public static int[] needed;		
	public static int[] q;
	
	public static int howManyBomb(int left, int right) {
		int ans = 0;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			if (canBuy(mid)) {
				ans = Math.max(ans, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return ans;
	}
	
	public static boolean canBuy(int count) {
		long cost = 0;
		for (int i=0; i<N; i++) {
			cost += howMuch(i, needed[i] * count - q[i]);
			if (cost > M) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int howMuch(int i, int count) {
		int ans = INF;
		
		int S = (int)Math.ceil((double)count / quantity[0][i]);
		for (int k=0; k<=S; k++) {
			int temp  = 0;
			
			int mod = count - (quantity[0][i] * k);
			temp += (price[0][i] * k);
			
			if (mod > 0) {
				temp += ((int)Math.ceil((double)mod/quantity[1][i]) * price[1][i]);
			}
			
			ans = Math.min(ans, temp);
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		quantity = new int[2][N];
		price = new int[2][N];
		needed = new int[N];
		q = new int[N];
		
		for (int i=0; i<N; i++) {
			needed[i] = sc.nextInt();
			q[i] = sc.nextInt();
			quantity[0][i] = sc.nextInt();
			price[0][i] = sc.nextInt();
			quantity[1][i] = sc.nextInt();
			price[1][i] = sc.nextInt();
		}
		
		
		int ans = howManyBomb(0, M * 100);
		System.out.println(ans);
	}
}