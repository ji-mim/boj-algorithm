import java.io.*;
import java.util.*;

public class Solution  {
	
	static class Node implements Comparable<Node>{
		int vertex;
		long weight;
		
		public Node(int vertex, long weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node n) {
			return Long.compare(this.weight, n.weight);
		}
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			int N = Integer.parseInt(br.readLine());
			Long[] xList = new Long[N];
			Long[] yList = new Long[N];
			ArrayList<Node>[] graph = new ArrayList[N];
			for (int i = 0 ; i < N ; i ++) {
				graph[i] = new ArrayList<>();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N ; i ++) {
				xList[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N ; i ++) {
				yList[i] = Long.parseLong(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = i + 1 ; j < N ; j ++) {
					Long weight = (long)(Math.abs(xList[i] - xList[j]) * Math.abs(xList[i] - xList[j])) 
							+ (long)(Math.abs(yList[i] - yList[j]) * Math.abs(yList[i] - yList[j]));
					graph[i].add(new Node(j, weight));
					graph[j].add(new Node(i, weight));
				}
			}
			
			long[] minEdge = new long[N];
			Arrays.fill(minEdge, Long.MAX_VALUE);
			minEdge[0] = 0;
			boolean[] visited = new boolean[N];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0,0));
			
			int cnt = 0;
			double result = 0;
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if(visited[cur.vertex]) continue;
				visited[cur.vertex] = true;
				
				result += (double)cur.weight;
				cnt ++;
				
				for (Node next : graph[cur.vertex]) {
					if(!visited[next.vertex] && minEdge[next.vertex] > next.weight) {
						minEdge[next.vertex] = next.weight;
						pq.add(new Node(next.vertex, next.weight));
					}
				}
				
			}
			
			result *= E;
			
			System.out.println("#" + tc + " " + Math.round(result));
			
			
			
		}
		
	}

}
