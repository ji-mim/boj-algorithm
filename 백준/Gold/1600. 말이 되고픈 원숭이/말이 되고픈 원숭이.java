import java.io.*;
import java.util.*;

public class Main {

	static int K, N, M;
	static int[][] map;
	static int[][][] visited; // x,y,말 점프 횟수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M][K + 1];
		
		for (int i = 0 ; i < N ; i ++ ) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = bfs();
		
		System.out.println(ans);
	}
	
	public static int bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.offer(new int[] {0,0,0});
		visited[0][0][0] = 1;
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		int[] hx = {-2,-1,1,2,2,1,-1,-2};
		int[] hy = {1,2,2,1,-1,-2,-2,-1};
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			int used = cur[2];
			
			if(x == N - 1 && y == M - 1) {
				return visited[x][y][used] - 1;
			}
			
			// 일반 점프 
			for (int i = 0 ; i < 4 ; i ++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visited[nx][ny][used] > 0) continue;
				if(map[nx][ny] == 1) continue;
				
				que.offer(new int[] {nx,ny,used});
				visited[nx][ny][used] = visited[x][y][used] + 1;
			}
			
			// 말점프 
			
			if(used >= K) continue;
			
			for (int i = 0 ; i < 8 ; i ++) {
				int nx = x + hx[i];
				int ny = y + hy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visited[nx][ny][used+1] > 0) continue;
				if(map[nx][ny] == 1) continue;
				
				que.offer(new int[] {nx,ny,used+1});
				visited[nx][ny][used+1] = visited[x][y][used] + 1;
			}
			
		}
		
		
		
		return -1;
	}
}
