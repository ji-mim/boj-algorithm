import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int ans = 0; 
			
			map = new int[N][N];
			
			for (int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 가로방향 탐색 
			visited = new boolean[N][N];
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						if(checkSpotCol(i,j)) {
							ans ++;
						}
					}
				}
			}
			
			// 새로방향탐색
			visited = new boolean[N][N];
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						if(checkSpotRow(i,j)) {
							ans ++;
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}


	}
	
	static boolean checkSpotCol(int x, int y) {
		int cnt = 0;
		int nx = x; 
		int ny = y;
		
		while(true) {
			if(ny >= N) break;
			if(map[nx][ny] == 1) {
				visited[nx][ny] = true;
				cnt ++;
				ny ++;
			}else break;
		}
		

		if (cnt == K) {
			return true;
		}
		
		
		return false;
	}
	static boolean checkSpotRow(int x, int y) {
		int cnt = 0;
		int nx = x; 
		int ny = y;
		
		while(true) {
			if(nx >= N) break;
			if(map[nx][ny] == 1) {
				visited[nx][ny] = true;
				cnt ++;
				nx ++;
			}else break;
		}
		
		
		if (cnt == K) {
			return true;
		}
		
		
		return false;
	}

}
