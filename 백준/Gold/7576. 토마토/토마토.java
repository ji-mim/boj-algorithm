import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] box;
	static int[][] days;
	static boolean[][] visited; 
	static ArrayDeque<int[]> que = new ArrayDeque<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int notAged;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		days = new int[N][M];
		visited = new boolean[N][M];
		
		notAged = 0;
		
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 0) notAged ++;
				if(box[i][j] == 1) {
					visited[i][j] = true;
					que.offer(new int[] {i,j});
				}
			}
		}
		
		bfs();
		
		if(notAged != 0) {
			System.out.println(-1);
		}else {
			System.out.println(findMaxDay());
		}
		
		
		
	}
	
	public static int findMaxDay(){
		int day = 0;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				day = Math.max(day, days[i][j]);
			}
		}
		return day;
	}
	
	public static void bfs() {
		while(!que.isEmpty()) {
			for (int j = 0 ; j < que.size() ; j ++) {
				int[] curr = que.poll();
				int x = curr[0];
				int y = curr[1];
				
				for (int i = 0 ; i < 4 ; i ++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(!inRange(nx,ny)) continue;
					if(!visited[nx][ny] && box[nx][ny] == 0) {
						visited[nx][ny] = true;
						box[nx][ny] = 1; 
						notAged --;
						days[nx][ny] = days[x][y] + 1;
						que.offer(new int[] {nx,ny});
					}
				}
			
			}
		}
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<=y && y < M; 
	}
}
