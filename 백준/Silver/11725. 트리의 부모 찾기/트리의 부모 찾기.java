import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] parentNodes = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 0 ; i <= N ; i ++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		
		for (int i = 0 ; i < N - 1 ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			
			graph[to].add(from);
			graph[from].add(to);
		}
		
		
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(1);
		visited[1] = true;
		
		while(!que.isEmpty()) {
			int parentNode = que.poll();
			
			for (int i = 0 ; i < graph[parentNode].size() ; i ++) {
				int childNode = graph[parentNode].get(i);
				if(visited[childNode]) continue;
				parentNodes[childNode] = parentNode;
				que.add(childNode);
				visited[childNode] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 2 ; i < N + 1; i ++) {
			sb.append(parentNodes[i]).append("\n");
		}
		
		System.out.println(sb);
		
		
		

		
		
	}

}
