import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, ans;
	static int [][] map;
	static int [][] copyMap;
	static boolean [][] visited;
	static List<int[]> wallSpot = new ArrayList<>();
	static List<Integer> selected = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				wallSpot.add(new int[] {i, j});
			}
		}
		
		// 3개 뽑기
		selectWallSpot(0, 0);
		
		System.out.println(ans);
		
		
	}
	
	public static void selectWallSpot(int idx, int cnt) {
		if (cnt == 3) {
			for(int i : selected) { // 빈 공간이 아니면 
				if(map[wallSpot.get(i)[0]][wallSpot.get(i)[1]] != 0) return;
			}
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < M ; j ++) {
					copyMap[i][j] = map[i][j];
					visited[i][j] = false;
				}
			}
			
			
			ans = Math.max(ans, cntSafeArea());
			
			
			return;
		}
		
		for (int i = idx ; i < wallSpot.size() ; i ++) {
			selected.add(i);
			selectWallSpot(i + 1, cnt + 1);
			selected.remove(selected.size() - 1);	
		}
	}
	
	public static int cntSafeArea() {
		int cnt = 0;
		// 벽 세우기
		for(int i : selected) {
			copyMap[wallSpot.get(i)[0]][wallSpot.get(i)[1]] = 1;
		}
		
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				if(!visited[i][j] && copyMap[i][j] == 2) {
					visited[i][j] = true;
					dfs(i,j);
				}
			}
		}
		
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				if(!visited[i][j] && copyMap[i][j] == 0) {
					cnt ++;
				}
			}
		}
		
		return cnt;
	}
	
	public static void dfs(int x, int y) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if(visited[nx][ny]) continue;
			if(copyMap[nx][ny] == 2 || copyMap[nx][ny] == 1) continue;
			visited[nx][ny] = true;
			dfs(nx, ny);
		}
	}
}
