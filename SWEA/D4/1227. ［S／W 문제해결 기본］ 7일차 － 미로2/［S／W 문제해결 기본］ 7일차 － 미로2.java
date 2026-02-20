import java.io.*;
import java.util.*;

public class Solution {
	
	static int T;
	static int LEN = 100;
	static int[][] map = new int[LEN][LEN];
	static boolean [][] visited = new boolean[LEN][LEN];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1 ; tc <= 10 ; tc ++) {
			T = Integer.parseInt(br.readLine());
			map = new int[LEN][LEN];
			visited = new boolean[LEN][LEN];
			int sx = 0;
			int sy = 0;
			int gx = 0;
			int gy = 0;
			for (int i = 0 ; i < LEN ; i++) {
				String[] input = br.readLine().split("");
				for (int j = 0 ; j < LEN ; j ++) {
					map[i][j] = Integer.parseInt(input[j]);
					if(map[i][j] == 2) {
						sx = i;
						sy = j;
					}
					if(map[i][j] == 3) {
						gx = i;
						gy = j;
					}
				}
			}
			
			dfs(sx,sy);
			
			int ans = 0;
			if(visited[gx][gy]) ans = 1;
			
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= LEN || ny < 0 || ny >= LEN) continue;
			
			if(!visited[nx][ny] && map[nx][ny] != 1) {
				dfs(nx,ny);
			}
		}
	}
}
