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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, (int) 1e9);
		
		ArrayList<Integer>[] visit = new ArrayList[N + 1];
		ArrayList<Element>[] graph = new ArrayList[N + 1];
		for (int i = 0 ; i <= N ; i ++) {
			graph[i] = new ArrayList<>();
			visit[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Element(to, d));
//			graph[to].add(new Element(from, d));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dist[start] = 0;
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.add(new Element(start, 0));
		visit[start].add(start);
		
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
					visit[targetIdx].clear();
					visit[targetIdx].addAll(visit[minIdx]);
					visit[targetIdx].add(targetIdx);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dist[end]).append("\n").append(visit[end].size()).append("\n");
		
		for (int v : visit[end]) {
			sb.append(v).append(" ");
		}
		
		System.out.println(sb);
		
	}

}
