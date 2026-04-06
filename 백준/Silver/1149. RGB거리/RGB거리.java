import java.io.*;
import java.util.*;

public class Main {
	
	static int N, ans;
	static int[][] cost;
	static int[][] memo;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3]; // [몇번집][0: 빨강, 1: 초록, 2: 파랑]
		ans = Integer.MAX_VALUE;
		memo = new int[N][4];
		for(int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; j ++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int[] d : memo) {
			Arrays.fill(d, -1);
		}
		
		System.out.println(backtrack(0, 3));
		
		
	}
	
	
	static int backtrack(int idx, int prev) {
		if(idx == N) {
			return 0;
		}
		
		if(memo[idx][prev] != -1) return memo[idx][prev];
		
		int min = Integer.MAX_VALUE;
		
		for(int color = 0 ; color < 3 ; color ++) {
			if (idx > 0 && prev == color) continue;
			min = Math.min(min, cost[idx][color] + backtrack(idx + 1, color));
		}
		
		return memo[idx][prev] = min;
	}

}
