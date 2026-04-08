import java.io.*;
import java.util.*;

public class Main {
	
	static class App implements Comparable<App>{
		int m, c;

		public App(int m, int c) {
			super();
			this.m = m;
			this.c = c;
		}
		
		@Override
		public int compareTo(App a) {
			if(this.c == a.c) return Integer.compare(a.m, this.m);
			return Integer.compare(this.c, a.c);
		}
	}
	
	static int N, M, answer;
	static int[] memory, cost;
	static int[][] memo;
	static int totalCost;
	static App[] apps;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		memory = new int[N];
		cost = new int[N];
		apps = new App[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			cost[i] = Integer.parseInt(st.nextToken());
			totalCost += cost[i];
		}
		
		for(int i = 0 ; i < N ; i ++) {
			apps[i] = new App(memory[i], cost[i]);
		}
		
		memo = new int[N + 1][totalCost + 1];
		for(int i = 0 ; i <= N ; i ++) {
			Arrays.fill(memo[i], -1);
		}
		
		for(int budget = 0 ; budget <= totalCost ; budget ++) {
			if(dfs(0, budget) >= M) {
				answer = budget;
				break;
			}
		}
		
		System.out.println(answer);
		
		
		
	}
	
	static int dfs(int idx, int budget) {
		if(idx == N) return 0;
		
		if(memo[idx][budget] != -1) return memo[idx][budget];
		
		int ret = dfs(idx + 1, budget);
		
		if(budget >= cost[idx]) {
			ret = Math.max(ret, memory[idx] + dfs(idx + 1, budget - cost[idx]));
		}
		
		return memo[idx][budget] = ret;
		
		
		
	}

}
