import java.io.*;
import java.util.*;

public class Main {
	
	static class Element implements Comparable<Element>{
		int idx, dist;
		
		public Element(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Element e) {
			return Integer.compare(this.dist, e.dist);
		}
	}
	
	static int N, M, X;
	static ArrayList<Element>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 0 ; i <= N ; i ++){
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Element(to, cost));
		}
		
		int ans = 0;
		
		for(int i = 1 ; i <= N ; i ++) {
			ans = Math.max(ans, dijks(i, X) + dijks(X, i));
		}
		
		System.out.println(ans);
		
		
		
	}
	
	public static int dijks(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, (int)1e9);
		dist[start] = 0;
		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.add(new Element(start, 0));
		
		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int minIdx = cur.idx;
			int minDist = cur.dist;
			if(dist[minIdx] != minDist) continue;
			
			for(Element target : graph[minIdx]) {
				int targetIdx = target.idx;
				int targetDist = target.dist;
				int newDist = dist[minIdx] + targetDist;
				if(dist[targetIdx] > newDist) {
					dist[targetIdx] = newDist;
					pq.add(new Element(targetIdx, newDist));
				}
			}
		}
		
		return dist[end];
	}

}
