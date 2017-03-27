package sort_numbers;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	public static int N;
	public static int[] a;
	
	public static void bubble() {
		for (int i=0; i<N-1; i++) {
			for (int j=0; j<N-i-1; j++) {
				if (a[j] > a[j+1]) {
					int temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
				}
			}
		}
	}
	
	public static void selection() {
		for (int i=0; i<N-1; i++) {
			int j=0, idx = 0;
			for (j=0; j<N-i; j++) {
				if (a[j] > a[idx]) {
					idx = j;
				}
			}
			
			j--;
			int temp = a[j];
			a[j] = a[idx];
			a[idx] = temp;
		}
	}
	
	public static void insertion() {
		for (int i=1; i<N; i++) {
			for (int j=i-1; j>=0; j--) {
				if (a[j] > a[j+1]) {
					int temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
				} else {
					break;
				}
			}
		}
	}
	
	public static void mergeSort(int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = (left + right) / 2;
		mergeSort(left, mid);
		mergeSort(mid + 1, right);
		merge(left, mid, right);
	}
	
	public static void merge(int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		
		int[] temp = new int[right - left + 1];
		int k = 0;
		while (i <= mid && j <= right) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		
		while (i <= mid) {
			temp[k++] = a[i++];
		}
		
		while (j <= right) {
			temp[k++] = a[j++];
		}
		
		for (int l=0; l<right-left+1; l++) {
			a[l + left] = temp[l];
		}
	}
	
	public static void quickSort(int left, int right) {
		if (left >= right) {
			return;
		}
		
		int k = left;
		for (int i=left; i<right; i++) {
			if (a[i] < a[right]) {
				int temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				k++;
			}
		}
		
		int temp = a[k];
		a[k] = a[right];
		a[right] = temp;
		
		quickSort(left, k-1);
		quickSort(k+1, right);
	}
	
	public static void print(){
		for (int i=0; i<N; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("input.txt"));
//		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		a = new int[N];
		for (int i=0; i<N; i++) {
			a[i] = sc.nextInt();
		}
		
		quickSort(0, N-1);
		print();
	}
}
