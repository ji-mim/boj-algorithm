import java.io.*;
import java.util.*;

public class Main {
	
	static int N, R, Q;
	static int[] memo;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		memo = new int[N + 1];
		graph = new ArrayList[N + 1];
		
		for (int i = 0 ; i < N + 1; i ++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0 ; i < N - 1 ; i ++) {
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			graph[to].add(from);
			graph[from].add(to);
			
		}
		
		
		visited = new boolean[N + 1];
		
		dfs(R);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0 ; i < Q ; i ++) {
			sb.append(memo[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int dfs(int node) {
		int val = 1;
		visited[node] = true;
		if(memo[node] != 0) return memo[node];
		
		for (int child : graph[node]) {
			if(!visited[child]) {
				val += dfs(child);
			}
		}
		
		return memo[node] = val;
	}

}
