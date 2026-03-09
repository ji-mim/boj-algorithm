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
			
			
			if(findRoot(from) == findRoot(to)) {
				ans = i;
				break;
			}
			
			union(from, to);
//			parents[to] = from;
		}
		
		System.out.println(ans);
	}
	
	public static boolean union(int a, int b) {
		if(findRoot(a) == findRoot(b)) return false;
		parents[findRoot(b)] = findRoot(a);
		return true;
	}
	
	public static int findRoot(int node) {
		if(parents[node] == node) return node;
		return findRoot(parents[node]);
	}
}
