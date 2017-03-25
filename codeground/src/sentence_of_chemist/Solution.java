package sentence_of_chemist;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static String[] elements = new String[]{"H", "He", "Li", "Be", "B", "C", "N", 
			"O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", 
			"Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", 
			"Se", "Br", "Kr", "Rb", "Sr", "Y","Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", 
			"Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs", "Ba", "Hf", "Ta", "W", 
			"Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", 
			"Ra", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Fl", "Lv", "La", 
			"Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", 
			"Lu", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", 
			"Md", "No", "Lr"};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		Set<String> elemSet = new HashSet<>();
		for (int i=0; i<elements.length; i++) {
			elemSet.add(elements[i].toLowerCase());
		}
		
		int TC = sc.nextInt();
		for (int test_case = 1; test_case <= TC; test_case++) {
			String str = sc.next();
			str = str.toLowerCase();
			int N = str.length();
			
			boolean[] isPossible = new boolean[N];
			for (int i=0; i<N; i++) {
				if (i == 0) {
					isPossible[i] = elemSet.contains(str.substring(i, i+1));
				} else {
					isPossible[i] = (isPossible[i-1] && elemSet.contains(str.substring(i, i+1)))
							|| ((i > 1 && isPossible[i-2]) && elemSet.contains(str.substring(i-1, i+1)));
				}
			}
			
			String ans = isPossible[N-1] ? "YES" : "NO";
			System.out.println("Case #" + test_case);
			System.out.println(ans);
		}
	}
}
