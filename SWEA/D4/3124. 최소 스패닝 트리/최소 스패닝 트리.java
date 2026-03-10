import java.io.*;
import java.util.*;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
	
	static int T, V, E;
	static int[] parents;
	static Edge[] edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			Long result = 0L;
			int cnt = 0;
			parents = new int[V + 1];
			edges = new Edge[E];
			Arrays.fill(parents, -1);
			
			for (int i = 0 ; i < E ; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(start, end, weight);
			}
			
			Arrays.sort(edges);
			
			for(Edge edge: edges) {
				int start = edge.start;
				int end = edge.end;
				int weight = edge.weight;
				
				if(union(start, end)) {
					result += weight;
					cnt ++;
					if(cnt == V - 1) {
						break;
					}
				}
			}
			
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	public static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA == rootB) return false;
		
		if(parents[rootA] < parents[rootB]) {
			parents[rootA] += parents[rootB];
			parents[rootB] = rootA;
		}else {
			parents[rootB] += parents[rootA];
			parents[rootA] = rootB;
		}
		
		return true;
	}


}
