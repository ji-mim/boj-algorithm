import java.io.*;
import java.util.*;

public class Main {
	
	static class Element implements Comparable<Element>{
		int Idx, dist;
		
		public Element(int vertex, int dist) {
			this.Idx = vertex;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Element e) {
			return Integer.compare(this.dist, e.dist);
		}
	}
	
	static int N, M;
	static ArrayList<Integer>[] p;
	static ArrayList<Element>[] graph;
	static boolean [][] usedEdge;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) { // 시간 복잡도 모르겠음 
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0) break;
			usedEdge = new boolean[N + 1][N + 1];
			
			p = new ArrayList[N + 1];
			for (int i = 0 ; i <= N ; i ++) { //O(N);
				p[i] = new ArrayList<>();
			}
			
			
			graph = new ArrayList[N + 1];
			for (int i = 0 ; i <= N ; i ++) { //O(N);
				graph[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			//그래프 받기 
			for (int i = 0 ; i < M ; i ++) { // O(M);
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				graph[from].add(new Element(to, dist));
			}
			// 처음 돌려서 나온 값이 최소 거리니까, 그 거리가 나오지 않을 때 까지 돌리면 될 것 같은데 ?
			int shortestPath = dijkstra(start, end, false);
//			System.out.println(shortestPath);
			// Array에서 지우기 
//			removePath(end);
			
//			for(boolean[] u: usedEdge) {
//				System.out.println(Arrays.toString(u));
//			}
			
			int nextPath = dijkstra(start, end, true);
			nextPath = nextPath == Integer.MAX_VALUE ? -1 : nextPath ;
			sb.append(nextPath).append("\n");
		}
		System.out.println(sb);
		
		//1. 먼저 최단 경로를 찾고, 그 경로를 기록해두기 (노드가 아니라 경로를 기록해야함.. 그냥 경로를 삭제해버려야하나)
		//2. 최단 경로를 포함하지 않게 최단 경로를 다시 찾기 
	}
	
	static int dijkstra(int start, int end, boolean flag) {
		int[] dist = new int[N +1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.add(new Element(start, 0));
		
		while(!pq.isEmpty()) {
			Element cur = pq.poll();
			int minIdx = cur.Idx;
			int minDist = cur.dist;
			if(dist[minIdx] != minDist) continue;
			
			if(!flag && minIdx == end) {
//				System.out.println(Arrays.toString(p));
//				System.out.println(Arrays.toString(dist));
				removePath(start, end);
//				System.out.println("end");
			}
			
			for (Element target : graph[minIdx]) {
				int targetIdx = target.Idx;
				int targetDist = target.dist;
				int newDist = dist[minIdx] + targetDist;
				
				if(flag) {
					if(usedEdge[minIdx][targetIdx]) continue; 
				}
				
				if(dist[targetIdx] > newDist) {
					dist[targetIdx] = newDist;
					p[targetIdx].clear();
					p[targetIdx].add(minIdx);
					pq.add(new Element(targetIdx, newDist));
				}else if (dist[targetIdx] == newDist) {
					p[targetIdx].add(minIdx);
				}
			}
		}
		
//		System.out.println(Arrays.toString(dist));
//		System.out.println(Arrays.toString(p));
		return dist[end];
	}
		
	
	static void removePath(int start, int end) {
	    if(end == start) return;

	    for(int parent : p[end]) {
	        if(!usedEdge[parent][end]) {
	            usedEdge[parent][end] = true;
	            removePath(start, parent);
	        }
	    }
	}
}
