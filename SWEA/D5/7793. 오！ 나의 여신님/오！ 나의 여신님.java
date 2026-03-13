import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, M;
	static int [][] map;
	static String ans;
	
	static int[][] devilStep;
	static int[][] suStep;
	
	static int gx, gy; // 여신 위치
	static ArrayDeque<int[]> devil = new ArrayDeque<>();
	static ArrayDeque<int[]> su = new ArrayDeque<>();
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T ; tc ++ ) {
			devil.clear();
			su.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			devilStep = new int[N][M];
			suStep = new int[N][M];
			ans = "GAME OVER";
			
			for (int i = 0 ; i < N ; i ++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0 ; j < M ; j ++) {
					if(input[j] == 'D') {
						gx = i;
						gy = j;
						map[i][j] = 2;
					}else if (input[j] == '*') {
						devil.add(new int[] {i,j});
						map[i][j] = 1;
						devilStep[i][j] = 1;
					}else if (input[j] == 'S') {
						su.add(new int[] {i,j});
						map[i][j] = 0;
						suStep[i][j] = 1;
					}else if (input[j] == '.') {
						map[i][j] = 0;
					}else if (input[j] == 'X') {
						map[i][j] = -1;
					}
				}
			}
			
			
			bfs(devil, devilStep, true);
			bfs(su, suStep, false);
			
//			for(int[] m : devilStep) {
//				System.out.println(Arrays.toString(m));
//			}
//			System.out.println("=============");
//			for(int[] m : suStep) {
//				System.out.println(Arrays.toString(m));
//			}
			
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void bfs(ArrayDeque<int[]> que, int[][] step, boolean flag) {
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0 ; i < 4 ; i ++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(step[nx][ny] != 0) continue;
				if(map[nx][ny] == -1) continue;
				
				if(flag) { // 악마
					if(map[nx][ny] == 2) continue;
				}else {
					if(map[nx][ny] == 1) continue;
				}
				
				if(!flag && map[nx][ny] == 2) {
					ans = String.valueOf(suStep[x][y]);
				}
				
				if(!flag && devilStep[nx][ny] != 0 && suStep[x][y] + 1 >= devilStep[nx][ny]) {
					continue;
				}
				step[nx][ny] = step[x][y] + 1;
				
				que.add(new int[] {nx, ny});
			}
		}
		
	}

}
