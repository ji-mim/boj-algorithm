
import java.util.*;

public class Main {
	
	public static int N = 9;
	public static int M; 
	
	//가능한 모든 수열 중에서 소수를 찾자 
	public static List<Integer> selected = new ArrayList<>();
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		
		int[] first = {2,3,5,7};
		
		for (int i : first) {
			select(i, 1);
		}
		
		System.out.println(sb);
		
	}
	
	public static void select(int num, int idx) {
		if(idx == M) {
			sb.append(num + "\n");
			return;
		}
		
		int[] prime = {1,3,7,9};
		
		for (int i : prime) {
			int nextNum = num * 10 + i;
			if(isPrime(nextNum)) {
				select(nextNum, idx + 1);
			}
		}
		
	}
	
	public static boolean isPrime(int num) {
		if (num == 1) return false;
		if (num == 2) return true;
		if (num % 2 == 0) return false;
		
		for (int i = 3 ; i <= Math.sqrt(num) ; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}

}
