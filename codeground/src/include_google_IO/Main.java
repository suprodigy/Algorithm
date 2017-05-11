package include_google_IO;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test_case=1; test_case<=T; test_case++) {
			int B = sc.nextInt();
			
			List<Character> ans = new ArrayList<>();
			
			String str = sc.next();
			int idx = 0;
			
			for (int i=0; i<B; i++) {
				char ch = 0;
				for (int j=0; j<8; j++) {
					if (str.charAt(idx++) == 'I') {
						ch |= (1 << (7-j));
					}
				}
				ans.add(ch);
			}
			
			System.out.print("Case #" + test_case + ": ");
			for (char ch : ans) {
				System.out.print(ch);
			}
			System.out.println();
		}
	}
	
}
