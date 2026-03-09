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
			
			int ans = 0;
			for (int i = 1 ; i <= N ; i ++) {
				if(parents[i] < 0) {
					ans ++;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	public static void makeSets() {
		parents = new int[N + 1];
		Arrays.fill(parents, -1);
	}
	
	public static int findSet(int node) {
		if(parents[node] < 0) return node;
		return parents[node] = findSet(parents[node]);
	}
	
	public static void union(int from, int to) {
		int rootFrom = findSet(from);
		int rootTo = findSet(to);
		if(rootFrom == rootTo) return;
		
		if(parents[rootFrom] < parents[rootTo]) {
			parents[rootFrom] += parents[rootTo];
			parents[rootTo] = rootFrom;
		}else {
			parents[rootTo] += parents[rootFrom];
			parents[rootFrom] = rootTo;
		}
	}
}
