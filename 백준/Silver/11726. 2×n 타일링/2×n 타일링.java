import java.util.*; 

public class Main {
	
	static int N;
	static int memo[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		memo = new int[N + 1];
		
		Arrays.fill(memo, -1);
		
		System.out.println(backtrack(0));
		
	}
	
	static int backtrack(int len) {
		if(len == N) return 1;
		if(memo[len] != -1) return memo[len] % 10_007;
		
		int way = 0;
		
		if(N - len < 2) {
			way += backtrack(len + 1) % 10_007;
		}else {
			way += backtrack(len + 1) % 10_007;
			way += backtrack(len + 2) % 10_007;
		}
		way %= 10_007;
		return memo[len] = way;
	}
}
