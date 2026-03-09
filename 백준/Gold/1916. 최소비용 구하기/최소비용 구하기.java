import java.io.*;
import java.util.*;

public class Main {
	
	static class Element implements Comparable<Element>{
		int index, dist;
		
		public Element(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Element e) {
			return this.dist - e.dist;
		}
	}

	static int N, M;
	static int[] dist;
	static ArrayList<Element>[] graph;
	static PriorityQueue<Element> pq = new PriorityQueue<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N + 1];
		graph = new ArrayList[N + 1];
		
		for (int i = 0 ; i <= N ; i ++) {
			graph[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, (int)1e9);
		
		for (int i = 0 ; i < M ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[to].add(new Element(from, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dist[start] = 0;
		
		pq.add(new Element(start, 0));
		
		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int minDist = cur.dist;
			int minIndex = cur.index;
			
			if(minDist != dist[minIndex]) continue;
			
			for (int i = 0 ; i <graph[minIndex].size() ; i ++) {
				int targetIndex = graph[minIndex].get(i).index;
				int targetDist = graph[minIndex].get(i).dist;
				
				int newDist = targetDist + dist[minIndex];
				if(newDist < dist[targetIndex]) {
					dist[targetIndex] = newDist;
					pq.add(new Element(targetIndex, newDist));
				}
			}
		}
		
		
		System.out.println(dist[end]);
		
	}
}
