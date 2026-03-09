import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		
		for (int i = 0 ; i < N ; i ++) {
			parents[i] = i; 
		}
		
		int ans = 0 ;
		
		for (int i = 1 ; i <= M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			int rootFrom = findRoot(from);
			int rootTo = findRoot(to);
			if(rootFrom == rootTo) {
				ans = i;
				break;
			}
			
			parents[rootTo] = rootFrom;
		}
		
		System.out.println(ans);
	}
	
	public static int findRoot(int node) {
		if(parents[node] == node) return node;
		return parents[node] = findRoot(parents[node]);
	}
}
