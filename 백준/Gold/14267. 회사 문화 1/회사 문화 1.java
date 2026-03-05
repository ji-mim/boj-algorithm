import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parents, pos, dp;
	static ArrayList<Integer>[] tree;
	static ArrayList<Integer> leafs = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N + 1];
		
		for (int i = 0 ; i <= N ; i ++) {
			tree[i] = new ArrayList<>();
		}
		
		parents = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1 ; i <= N ; i ++) {
			int parent = Integer.parseInt(st.nextToken());
			if(i == 1) continue;
			parents[i] = parent;
			tree[i].add(parent);
			tree[parent].add(i);
		}
		
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		pos = new int[N + 1];
		
		for (int i= 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			pos[p] += v;
		}
		
		
		findLeafs();
		
		parents[1] = -1;
		
		for(int leaf : leafs) {
			backtrack(leaf);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1 ; i <= N ; i ++) {
			sb.append(dp[i]).append(" ");
		}
		
		System.out.println(sb);
		
		
	}
	
	public static int backtrack(int node) {
		if(dp[node] != -1) return dp[node];
		
		if(parents[node] == -1) {
			return dp[node] = 0; 
		}
		
		
		return dp[node] = backtrack(parents[node]) + pos[node];
	}
	
	public static void findLeafs() {
		boolean[] visited = new boolean[N + 1];
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(1);
		visited[1] = true;
		
		while(!que.isEmpty()){
			int cur = que.poll();
			boolean isLeaf = true;
			for (int c : tree[cur]) {
				if(visited[c]) continue;
				visited[c] = true;
				isLeaf = false;
				que.add(c);
			}
			
			if(isLeaf) leafs.add(cur);
		}
	}
}
