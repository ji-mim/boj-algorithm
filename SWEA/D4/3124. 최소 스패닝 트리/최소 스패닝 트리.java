import java.io.*;
import java.util.*;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int vertex, weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.weight, n.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int[] minEdge = new int[V + 1];
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			boolean[] visited = new boolean[V + 1];
			ArrayList<Node>[] graph = new ArrayList[V + 1];
			for (int i = 0 ; i <= V ; i ++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 1 ; i <= E ; i ++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(to, weight));
				graph[to].add(new Node(from, weight));
			}
			
			minEdge[0] = 0;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(1,0));
			
			Long result = 0L;
			int cnt = 0;
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				int curVertex = cur.vertex;
				int curWeight = cur.weight;
				if(visited[curVertex]) continue;
				visited[curVertex] = true;
				result += curWeight;
				cnt ++;
				
				for(Node node : graph[curVertex]) {
					if(!visited[node.vertex] && minEdge[node.vertex] > node.weight) {
						minEdge[node.vertex] = node.weight;
						pq.add(new Node(node.vertex, node.weight));
					}
				}
			}
			
			result = cnt == V ? result : -1;
			
			System.out.println("#" + tc + " " + result);
			
			
			
		}
		
	}
}
