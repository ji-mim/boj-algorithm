import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node>{
		int vetex, weight;
		
		public Node(int vertex, int weight) {
			this.vetex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.weight, n.weight);
		}
	}

	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] minEdge = new int[N];
		
		for (int i = 0 ; i < N ; i ++) {
			minEdge[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] graph = new int[N][N];
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] visited = new boolean[N];
		
		int result = 0;
		
		for (int c = 0 ; c < N ; c ++) {
			int minVertex = -1 ; 
			int minWeight = Integer.MAX_VALUE; 
			
			for (int i = 0 ; i < N ; i ++) {
				if(!visited[i] && minWeight > minEdge[i]) {
					minVertex = i;
					minWeight = minEdge[i];
				}
				
			}
			visited[minVertex] = true;
					
			for (int i = 0 ; i < N ; i ++) {
				if(!visited[i] && graph[i][minVertex] != 0 && minEdge[i] > graph[i][minVertex]) {
					minEdge[i] = graph[i][minVertex];
				}
			}
		}
		
		for (int i : minEdge) {
			result += i;
		}
		
		System.out.println(result);
		
	}
}
