import java.io.*;
import java.util.*;

public class Solution {

	static class Node{
		Node parent, child;
	}
	static int N, M;
	static boolean[][] adj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adj = new boolean[N + 1][N + 1];
			
			for(int i = 0 ; i < M ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int small = Integer.parseInt(st.nextToken());
				int tall = Integer.parseInt(st.nextToken());
				adj[small][tall] = true;
			}
			
			for(int k = 1 ; k < N + 1 ; k ++) {
				for(int i = 1 ; i < N + 1 ; i ++) {
					if(!adj[i][k]) continue;
					for(int j = 1 ; j < N + 1 ; j ++) {
						if(adj[k][j]) adj[i][j] = true;
					}
				}
			}
			
			int ans = 0;
			
			for(int i = 1 ; i < N + 1 ; i ++) {
				int cnt = 0 ;
				for(int j = 1 ; j < N + 1 ; j ++) {
					if(i == j) continue;
					if(adj[i][j] || adj[j][i]) cnt ++;
				}
				
				if(cnt == N - 1) ans ++;
			}
			
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
	
}
