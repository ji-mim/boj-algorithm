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
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		int start = Integer.parseInt(br.readLine());
		dist[start] = 0;
		
		ArrayList<Element>[] graph = new ArrayList[V + 1];
		for (int i = 0 ; i <= V ; i ++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0 ; i < E ; i ++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[to].add(new Element(from, d));
		}
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.add(new Element(start, 0));
		
		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int minIdx = cur.idx;
			int minDist = cur.dist;
			
			if(dist[minIdx] != minDist) continue;
			
			for (Element target : graph[minIdx]) {
				int targetIdx = target.idx;
				int targetDist = target.dist;
				int newDist = dist[minIdx] + targetDist;
				if(dist[targetIdx] > newDist) {
					dist[targetIdx]= newDist;
					pq.add(new Element(targetIdx, newDist));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1 ; i <= V ; i ++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}else {
				sb.append(dist[i]).append("\n");
				
			}
		}
		
		System.out.println(sb);
		
	}

}
