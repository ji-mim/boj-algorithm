import java.io.*;
import java.util.*;

public class Solution {

	static int N, K;
	static int[] cost;
	static int[] weight;
	static int[][] memo;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			memo = new int[N][1001];
			cost = new int[N];
			weight = new int[N];
			for(int[] m : memo) {
				Arrays.fill(m, -1);
			}
			for (int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				cost[i] = Integer.parseInt(st.nextToken());
				
			}
			System.out.println("#" + tc + " " + dfs(0, 0));
		}
	}
	
	static int dfs(int idx, int w) {
		if(w > K) return -100_000_000;
		if(idx == N) return 0;
		if(memo[idx][w] != -1) return memo[idx][w];
		
		int a = dfs(idx + 1, w);
		int b = dfs(idx + 1, w + weight[idx]) + cost[idx];
		
		return memo[idx][w] = Math.max(a, b);
	}

}
