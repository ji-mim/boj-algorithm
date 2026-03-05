import java.io.*;
import java.util.*;

public class Main {

	static class Node{
		int to;
		int d;
		
		public Node(int to, int d) {
			this.to = to;
			this.d = d;
		}
	}
	static int N;
	static ArrayList<Node>[] tree;
	static int farNode;
	static int farDist;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];
		
		for (int i = 0 ; i < N + 1 ; i ++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0 ; i < N - 1 ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			tree[p].add(new Node(c,v));
			tree[c].add(new Node(p,v));
		}
		
		
		bfs(1);
		int A = farNode;
		bfs(A);
		System.out.println(farDist);
	}
	
	public static void bfs(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);
		dist[start] = 0;
		
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(start);
		
		farNode = start;
		farDist = 0;
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			
			if(dist[cur] > farDist) {
				farDist = dist[cur];
				farNode = cur;
			}

			for (Node c : tree[cur]) {
				if(dist[c.to] != -1) continue;
				dist[c.to] = dist[cur] + c.d;
				que.add(c.to);
			}
		}
		
		
	}
}
