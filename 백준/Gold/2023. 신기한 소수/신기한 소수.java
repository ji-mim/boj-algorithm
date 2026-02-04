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
		
		select(0);
		
		System.out.println(sb);
		
	}
	
	public static void select(int idx) {
		if(idx == M) {
			if(isPrime()) {
				sb.append(changeToNum() + "\n");
			}
			return;
		}
		
		for(int i = 1 ; i <= N ; i ++) {
			selected.add(i);
			select(idx + 1);
			selected.remove(selected.size() - 1);
		}
	}
	
	public static boolean isPrime() {
		
		int num = changeToNum();
		
		int d = 0;
		for (int t = num; t > 0; t /= 10) d++;

		int div = 1;
		for (int i = 1; i < d; i++) div *= 10;

		// prefix 출력
		for (int i = 0; i < d; i++) {
		    int prefix = num / div;
		    
			if(!checkPrime(prefix)) {
				return false;
			}
		    div /= 10; 
		}
		return true;
	}
	
	public static boolean checkPrime(int num) {
		if (num == 1) return false;
		if (num == 2) return true;
		if (num % 2 == 0) return false;
		
		for (int i = 3 ; i <= Math.sqrt(num) ; i ++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int changeToNum() {
		int num = 0 ;
		int offSet = 1;
		
		for (int i = selected.size() - 1 ; i >= 0 ; i --) {
			num += selected.get(i) * offSet;
			offSet *= 10;
		}
		
		return num;
	}

}
