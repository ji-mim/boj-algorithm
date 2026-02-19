import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, T, ans;
	static int [][] cheese;
	static int [][] eatedCheese;
	static boolean [][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			eatedCheese = new int[N][N];
			visited = new boolean[N][N];
			ans = -1;
			
			for (int i = 0 ; i < N ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0 ; i <= 100 ; i ++) {
				initEatedCheese();
				eatCheese(i);
				initVisited();
				int chunkCnt = findCheeseChunk();
				ans = Math.max(ans, chunkCnt);
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	public static int findCheeseChunk() {
		int cnt = 0;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				if(!visited[i][j] && eatedCheese[i][j] != 0) {
					dfs(i,j);
					cnt ++;
				}
			}
		}
		
		return cnt;
	}
	
	public static void dfs(int x, int y) {
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!inRange(nx,ny)) continue;
			
			if(!visited[nx][ny] && eatedCheese[nx][ny] != 0) {
				visited[nx][ny] = true;
				dfs(nx,ny);
			}
		}
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<=y && y < N;
	}
	
	
	
	public static void initEatedCheese() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				eatedCheese[i][j] = cheese[i][j];
			}
		}
	}
	
	public static void initVisited() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void eatCheese(int day) {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				if(eatedCheese[i][j] <= day) {
					eatedCheese[i][j] = 0;
				}
			}
		}
	}

}
