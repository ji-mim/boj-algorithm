import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			makeSets();
			
			while(M -- > 0) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(from, to);
			}
			
			boolean[] visited = new boolean[N + 1];
			int ans = 0;
			for (int i = 1 ; i <= N ; i ++) {
				if(!visited[findSet(i)]) {
					ans ++;
					visited[findSet(i)] = true;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	public static void makeSets() {
		parents = new int[N + 1];
		for (int i = 1 ; i <= N ; i ++) {
			parents[i] = i ;
		}
	}
	
	public static int findSet(int node) {
		if(parents[node] == node) return node;
		return parents[node] = findSet(parents[node]);
	}
	
	public static void union(int from, int to) {
		if(findSet(from) == findSet(to)) return;
		parents[findSet(to)] = findSet(from);
	}
}
