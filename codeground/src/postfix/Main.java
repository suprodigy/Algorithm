package postfix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Main {
	public static List<String> toPostfix(List<String> exps) throws Exception {
		List<String> ret = new ArrayList<>();
		
		// ������ �켱 ���� Map�� �̿��� ����
		Map<String, Integer> priorities = new HashMap<>();
		priorities.put("+", 0); priorities.put("-", 0);
		priorities.put("*", 1); priorities.put("/", 1);
		priorities.put("^", 2);
		
		// �������� ǥ���� ���������� �����ϸ鼭 �������� ǥ�������� ��ȯ
		Stack<String> st = new Stack<>();
		
		for (int i=0; i<exps.size(); i++) {
			String now = exps.get(i);
			
			if (isLeftParen(now)) {
				
				// ���� ��ȣ�̸� stack�� pop
				st.push(now);
				
			} else if (isRightParen(now)) {
				
				// ������ ��ȣ�̸� �ش��ϴ� ���� ¦�� ���� ������ stack���� pop -> ���� ǥ���Ŀ� ǥ�� 
				String target = now.equals(")") ? "(" : "{";
				// �߸��� ¦�� ã�� ���� ������ ����
				String nonTarget = now.equals(")") ? "{" : "(";
				
				while (!st.isEmpty()) {
					String temp = st.pop();
					if (temp.equals(target)) {
						break;
					} else if (temp.equals(nonTarget)) {
						// ¦�� ���� �ʴ� ��� Exception throw!
						throw new Exception("ǥ�� ���� ����");
					}
					ret.add(temp);
				}
				
			} else if (isOperator(now)) {
				
				// �������̸� ������ �ֻ����� �ִ� �����ں��� �ش� �������� �켱������ ���� ������ stack���� pop -> ���� ǥ���Ŀ� ǥ��
				while (!st.isEmpty() && priorities.containsKey(st.peek())) {
					String temp = st.peek();
					if (priorities.get(temp) >= priorities.get(now)) {
						ret.add(temp);
						st.pop();
					} else {
						break;
					}
				}
				
				// ���߿� ��ȣ�� �����ų� ���� �������� �켱������ �������� stack�� push!
				st.push(now);
				
			} else {
				ret.add(now);
			}
			
		}
		
		// ��ȸ ������ stack�� �����ִ� ����� ����ǥ���Ŀ� �߰�
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
		
	}
}