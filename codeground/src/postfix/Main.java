package postfix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	public static List<String> split(String expression) {	
		expression.trim();
		List<String> exps = new ArrayList<>();
		
		// 선형 탐색하면서 연산 최소 단위로 나눠서 저장
		String temp = "";
		for (int i=0; i<expression.length(); i++) {
			char ch = expression.charAt(i);
			switch (ch) {
			case '+': case '-': case '/': case '*': case '^': 
			case '(': case ')': case '{': case '}':
				if (!temp.equals("")) {
					exps.add(temp);
					temp = "";
				}
				exps.add(String.valueOf(ch));
				break;
			case ' ':
				break;
			default:
				temp += ch;
			}
		}
		
		if (!temp.equals("")) {
			exps.add(temp);
		}
		
		return exps;
	}
	
	public static List<String> toPostfix(List<String> exps) throws Exception {
		List<String> ret = new ArrayList<>();
		
		// 연산자 우선 순위 Map을 이용해 저장
		Map<String, Integer> priorities = new HashMap<>();
		priorities.put("+", 0); priorities.put("-", 0);
		priorities.put("*", 1); priorities.put("/", 1);
		priorities.put("^", 2);
		
		// 중위연산 표현식 순차적으로 접근하면서 후위연산 표현식으로 변환
		Stack<String> st = new Stack<>();
		
		for (int i=0; i<exps.size(); i++) {
			String now = exps.get(i);
			
			if (isLeftParen(now)) {
				
				// 왼쪽 괄호이면 stack에 pop
				st.push(now);
				
			} else if (isRightParen(now)) {
				
				// 오른쪽 괄호이면 해당하는 왼쪽 짝이 나올 때까지 stack에서 pop -> 후위 표현식에 표기 
				String target = now.equals(")") ? "(" : "{";
				// 잘못된 짝을 찾기 위해 선언한 변수
				String nonTarget = now.equals(")") ? "{" : "(";
				
				while (!st.isEmpty()) {
					String temp = st.pop();
					if (temp.equals(target)) {
						break;
					} else if (temp.equals(nonTarget)) {
						// 짝이 맞이 않는 경우 Exception throw!
						throw new Exception("표현 형식 오류");
					}
					ret.add(temp);
				}
				
			} else if (isOperator(now)) {
				
				// 연산자이면 스택의 최상위에 있는 연산자보다 해당 연산자의 우선순위가 높은 때까지 stack에서 pop -> 후위 표현식에 표기
				while (!st.isEmpty() && priorities.containsKey(st.peek())) {
					String temp = st.peek();
					if (priorities.get(temp) >= priorities.get(now)) {
						ret.add(temp);
						st.pop();
					} else {
						break;
					}
				}
				
				// 도중에 괄호를 만나거나 현재 연산자의 우선순위가 높아지면 stack에 push!
				st.push(now);
				
			} else {
				ret.add(now);
			}
			
		}
		
		// 순회 끝나면 stack에 남아있는 연산들 후위표현식에 추가
		while (!st.isEmpty()) {
			ret.add(st.pop());
		}
		
		return ret;
	}
	
	private static boolean isLeftParen(String str) {
		return (str.equals("(") || str.equals("{"));
	}
	
	private static boolean isRightParen(String str) {
		return (str.equals(")") || str.equals("}"));
	}
	
	private static boolean isOperator(String str) {
		return (str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*") || str.equals("^"));
	}
	
	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		List<String> list1 = split(str);
		List<String> list2 = toPostfix(list1);
		
		for (String temp : list2) {
			System.out.print(temp);
		}
		System.out.println();
	}
}
