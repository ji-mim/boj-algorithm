import java.io.*;
import java.util.*;

public class Solution {
	
	static class Pos{
		int x, y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, ans;
	static int ox, oy, hx, hy;
	static Pos[] posList;
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			posList = new Pos[N];
			order = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			ox = Integer.parseInt(st.nextToken());
			oy = Integer.parseInt(st.nextToken());
			hx = Integer.parseInt(st.nextToken());
			hy = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			
			for (int i = 0 ; i < N ; i ++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				posList[i] = new Pos(x, y);
			}
			
			selectOrder(0, new boolean[N]);
			
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	static void selectOrder(int idx, boolean[] visited) {
		if(idx == N) {
			ans = Math.min(ans, calDist());
			return;
		}
		
		for (int i = 0 ; i < N ; i ++) {
			if(visited[i]) continue;
			visited[i] = true;
			order[idx] = i;
			selectOrder(idx + 1, visited);
			visited[i] = false;
		}
	}
	
	public static int calDist() {
		int dist = 0;
		int x = ox;
		int y = oy;
		
		for(int o : order) {
			dist += Math.abs(x - posList[o].x) + Math.abs(y - posList[o].y);
			x = posList[o].x;
			y = posList[o].y;
		}
		dist += Math.abs(x - hx) + Math.abs(y - hy);
		return dist;
	}

}
