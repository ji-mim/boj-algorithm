import java.io.*;
import java.util.*;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		long weight;

		public Edge(int start, int end, long weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Double.compare(this.weight, e.weight);
		}
	}
	
	static int T, N;
	static double E;
	static long[] xList, yList;
	static double[][] wList;
	static ArrayList<Edge> edges = new ArrayList<>();
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			xList = new long[N];
			yList = new long[N];
			wList = new double[N][N];
			edges.clear();
			parents = new int[N];
			Arrays.fill(parents, -1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N ; i++) {
				xList[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0 ; i < N ; i ++) {
				yList[i] = Long.parseLong(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = i ; j < N ; j ++) {
					if(i == j || wList[i][j] != 0) continue;
					long w = Math.abs(xList[i] - xList[j]) * Math.abs(xList[i] - xList[j]) 
							+ Math.abs(yList[i] - yList[j]) * Math.abs(yList[i] - yList[j]);
					
					wList[i][j] = w;
					wList[j][i] = w;
					
					edges.add(new Edge(i,j,w));
				}
			}
			
			Collections.sort(edges);
			
			int count = 0 ;
			double result = 0;
			
			for (Edge edge: edges) {
				int start = edge.start;
				int end = edge.end;
				double weight = edge.weight;
				if(union(start, end)) {
					count ++;
					result += weight;
					
					if(count == N - 1) {
						break;
					}
				}
			}
			
			System.out.println("#" + tc + " " + Math.round(result * E));
			
			
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
