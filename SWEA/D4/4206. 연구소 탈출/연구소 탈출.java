import java.io.*;
import java.util.*;

public class Solution {
	
	static int T, N, M; 
	static int[][] map;
	static int[][] visitedP;
	static int[][] visitedF;
	static int fx,fy,px,py;
	static ArrayDeque<int[]> queP = new ArrayDeque<>();
	static ArrayDeque<int[]> queF = new ArrayDeque<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static String ans = "CANNOT ESCAPE";
	static boolean blockedByGas ;
	static boolean canEscape ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			queP.clear();
			queF.clear();
			map = new int[N][M];
			visitedP = new int[N][M];
			visitedF = new int[N][M];
			blockedByGas = false;
			canEscape  = false;
			
			for (int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < M ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 3) {
						px = i;
						py = j;
						queP.add(new int[] {i,j});
						visitedP[i][j] = 1;
						map[i][j] = 0;
					}
					if (map[i][j] == 2) {
						fx = i;
						fy = j;
						queF.add(new int[] {i,j});
						visitedF[i][j] = 1;
						map[i][j] = 0;
					}
				}
			}
			
			bfsF();
			bfsP();
			
			if(canEscape) {
				System.out.println("#" + tc + " " + ans);
			}else {
				if(blockedByGas) {
					System.out.println("#" + tc + " " + "ZOMBIE");
					
				}else {
					System.out.println("#" + tc + " " + "CANNOT ESCAPE");
				}
				
			}
		}
	}
	
	static void bfsF() {
		while(!queF.isEmpty()) {
			int[] cur = queF.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int d = 0 ; d < 4 ; d ++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visitedF[nx][ny] != 0) continue;
				if(map[nx][ny] != 0) continue;
				
				queF.add(new int[] {nx,ny});
				visitedF[nx][ny] = visitedF[x][y] + 1;
			}
		}
	}
	
	static void bfsP() {
		while(!queP.isEmpty()) {
			int[] cur = queP.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int d = 0 ; d < 4 ; d ++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				//탈출하는 경우 
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					ans = String.valueOf(visitedP[x][y]);
					canEscape = true;
					return;
				}
				
				if(visitedP[nx][ny] != 0) continue;
				if(map[nx][ny] != 0) continue;
				
	            int pNext = visitedP[x][y] + 1;
	            if (visitedF[nx][ny] != 0 && visitedF[nx][ny] <= pNext) {
	                blockedByGas = true;
	                continue;
	            }

				
				queP.add(new int[] {nx,ny});
				visitedP[nx][ny] = visitedP[x][y] + 1;
			}
		}
		
	}
}
