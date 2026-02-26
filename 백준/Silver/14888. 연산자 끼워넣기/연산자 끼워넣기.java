import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 27,392 kb
 * 103 ms
 * 걸린시간 30분
 * 난이도 : 3/10
 * 셀프 피드백
 * 어렵지 않은 문제였다
 * dfs 유형 10개만 더 풀면 아마 마스터 될듯??
 * 숫자가 고정되어 있어 완탐으로 풀기가 가능한 문제
 * 
 * 
 * */

public class Main {
	
	private static int dept = 0;
	private static int min, max, N;
	private static int[] nums;
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

		dept = 0;
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
			
		N = Integer.parseInt(br.readLine());
		int plus = 0;
		int minus = 0;
		int mul = 0;
		int div = 0;
			
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());

		plus = Integer.parseInt(st.nextToken());
		minus = Integer.parseInt(st.nextToken());
		mul = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());
		
		dfs(1, nums[0], plus, minus, mul, div);
			
		System.out.println(max);
		System.out.println(min);
		
	}

	private static void dfs(int dept, int result, int plus, int minus, int mul, int div) {
		
		if(dept == N) {
			min = Math.min(min, result);
			max = Math.max(max, result);
		}
		
		if(plus > 0) {
			dfs(dept + 1, result + nums[dept], plus - 1, minus, mul, div);
		}
		if(minus > 0) {
			dfs(dept + 1, result - nums[dept], plus, minus - 1, mul, div);
		}
		if(mul > 0) {
			dfs(dept + 1, result * nums[dept], plus, minus, mul - 1, div);
		}
		if(div > 0) {
			dfs(dept + 1, result / nums[dept], plus, minus, mul, div - 1);
		}
	}
}
