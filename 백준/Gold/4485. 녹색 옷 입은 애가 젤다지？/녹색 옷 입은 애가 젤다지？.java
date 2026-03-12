import java.io.*;
import java.util.*;

public class Main {
	
	static class Cell implements Comparable<Cell>{
		int x, y, cost;
		
		public Cell(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Cell c) {
			return Integer.compare(this.cost, c.cost);
		}
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int time = 0;
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			int[][] map = new int[N][N];
			int[][] cost = new int[N][N];
			
			for (int i = 0 ; i < N ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int[] c : cost) {
				Arrays.fill(c, Integer.MAX_VALUE);
			}
			
			cost[0][0] = map[0][0];
			
			PriorityQueue<Cell> pq = new PriorityQueue<>();
			pq.add(new Cell(0,0,map[0][0]));
			
			while(!pq.isEmpty()) {
				Cell cur = pq.poll();
				int x = cur.x;
				int y = cur.y;
				int minCost = cur.cost;
				
				if(cost[x][y] != minCost) continue;
				
				for (int i = 0 ; i < 4 ; i ++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					int newCost = cost[x][y] + map[nx][ny];
					
					if(cost[nx][ny] > newCost) {
						cost[nx][ny] = newCost;
						pq.add(new Cell(nx, ny, newCost));
					}
				}
			}
			
			int ans = cost[N-1][N-1];
			sb.append("Problem ").append(++time).append(": ").append(ans).append("\n");
		}
		
		System.out.println(sb);
		
	}

}
