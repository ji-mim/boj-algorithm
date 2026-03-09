import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, ans;
	static int[][] map;
	static boolean[][] visited;
	static ArrayDeque<int[]> que = new ArrayDeque<>();
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		HashMap<Character, Integer> mapper = new HashMap<>();
		mapper.put('*', -1);
		mapper.put('.', 0);
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			que.clear();
			
			for (int i = 0 ; i < N ; i ++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0 ; j < N ; j ++) {
					map[i][j] = mapper.get(input[j]);
				}
			}
			
			//숫자 넣어주기 
			fillBomb();
			
			bfs();
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if(map[i][j] == 0 && !visited[i][j]) {
						que.offer(new int[] {i, j});
						visited[i][j] = true;
						bfs();
						ans ++;
					}
				}
			}
			
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if(map[i][j] != -1 && !visited[i][j]) ans ++;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
		
		
		
		
	}
	
	static void bfs() {
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0 ; i < 8 ; i ++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(map[nx][ny] == 0) que.add(new int[] {nx,ny});
				
			}
		}
	}
	
	static void fillBomb() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				int bombNum = 0;
				if(map[i][j] != 0) continue;
				for (int d = 0 ; d < 8 ; d ++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if(map[nx][ny] == -1) bombNum ++;
				}
				map[i][j] = bombNum;
			}
		}
	}

}
