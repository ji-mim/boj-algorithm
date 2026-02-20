import java.io.*;
import java.util.*;

public class Solution {
	
	static int V,E;
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	static ArrayDeque<Integer> que = new ArrayDeque<>();
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1 ; tc <= 10 ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			sb = new StringBuilder();
			indegree = new int[V + 1];
			que.clear();
			
			sb.append("#").append(tc).append(" ");
			
			graph = new ArrayList[V + 1];
			
			for (int i = 0 ; i <= V ; i ++) {
				graph[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0 ; i < E ; i ++) {
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				graph[to].add(from);
				indegree[from] ++;
			}
			
			for (int i = 1 ; i <= V ; i ++) {
				if(indegree[i] == 0) que.add(i);
			}
			
			bfs();
			
			System.out.println(sb);
			
			
		}
		
	}
	
	public static void bfs() {
		while(!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur).append(" ");
			
			for (int i = 0 ; i < graph[cur].size(); i ++) {
				if(--indegree[graph[cur].get(i)] == 0) que.offer(graph[cur].get(i));
			}
		}
		
	}

}
