import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] graph;
	static int[] indegree;
	static int V,E ;
	static ArrayDeque<Integer> que = new ArrayDeque<>();
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1 ; tc <= 10 ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			que.clear();
			graph = new int[V + 1][V + 1];
			indegree = new int[V + 1];
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < E  ; i ++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to] = 1;
				indegree[to] += 1;
			}
			
			for (int j = 1; j <= V ; j ++) {
				boolean isStart = true;
				for (int i = 1; i <= V ; i ++) {
					if(graph[i][j] == 1) isStart = false;
				}
				
				if(isStart) {
					que.add(j);
				}
			}
			
			
			bfs();
			System.out.println(sb);
			
			
		}
	}
	
	public static void bfs() {
		
		while(!que.isEmpty()) {
			int curr = que.poll();
			sb.append(curr).append(" ");
			
			for (int i = 1 ; i <= V ; i ++) {
				if(graph[curr][i] == 1 ) {
					if(--indegree[i] == 0) {
						que.offer(i);
					}
				}
			}
		}
	}

}
