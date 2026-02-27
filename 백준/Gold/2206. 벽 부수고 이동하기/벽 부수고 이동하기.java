import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		//배열 입력 받기 
		for (int i = 0 ; i < N ; i ++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0 ; j < M ; j ++) {
				map[i][j] = input[j] - '0';
			}
		}
		
		int ans = bfs();
		
		System.out.println(ans);
		
	}
	
	public static int bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int[][][] visited = new int[N][M][2];
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// 시작점 넣기 
		que.offer(new int[] {0,0,0});
		visited[0][0][0] = 1;
		
		//bfs 시작 
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			int isBreaked = cur[2];
			
			// 목적지에 도착했으면 최단 경로니까 return 해주기 
			if (x == N -1 && y == M - 1) {
				return visited[x][y][isBreaked];
			}

			
			for (int i = 0 ; i < 4 ; i ++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 배열 바깥이면 방문하지 않기 
				if(visited[nx][ny][isBreaked] > 0) continue; // 이 상태로 방문해본적 있으면 방문하지 않기 
				
				if(map[nx][ny] == 0) { // 길이 있으면 쭉 가주기 
					que.offer(new int[] {nx,ny,isBreaked});
					visited[nx][ny][isBreaked] = visited[x][y][isBreaked] + 1;
				}else { // 벽이 있는 경우
					if(isBreaked == 1) continue; // 이미 벽을 부순적 있으면 이 진행은 불가능함
					
					// 벽을 부순적 없다면 한번은 부수고 가볼 수 있음 
					que.offer(new int[] {nx,ny,1});
					visited[nx][ny][1] = visited[x][y][isBreaked] + 1;
				}
				
			}
		}
		
		
		
		return -1;
	}

}
