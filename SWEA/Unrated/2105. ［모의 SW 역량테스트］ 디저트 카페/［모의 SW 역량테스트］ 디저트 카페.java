
import java.io.*;
import java.util.*;

public class Solution {
	
	static int T, N;
	static int ans;
	static int[][] map;
	static boolean[] visited;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			ans = -1;
			map = new int[N][N];
			visited = new boolean[101];
			
			for (int i = 0 ; i < N ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N - 1 ; j ++) {
					visited[map[i][j]] = true;
					findCourse(i,j,i,j,0,1);
					visited[map[i][j]] = false;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
		
	}
	
	public static void findCourse(int sx, int sy, int x, int y, int dir, int cnt) {
		for (int d = dir ; d <= dir + 1 && d < 4 ; d ++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(d == 3 && sx == nx && sy == ny && cnt >= 4) {
				ans = Math.max(ans, cnt);
			}
			
			
			if(!visited[map[nx][ny]]) {
				visited[map[nx][ny]] = true;
				findCourse(sx, sy, nx, ny, d, cnt + 1);
				visited[map[nx][ny]] = false;
			}
		}
	}

}
