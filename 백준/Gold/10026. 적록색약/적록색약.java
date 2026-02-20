import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] grid;
	static boolean[][] normalVisitied;
	static boolean[][] sickVisitied;
	
	static int R = 1;
	static int G = 2;
	static int B = 3;
	static int nCnt, sCnt;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String args[]) throws IOException {
		// 적록 색약은 R,G를 구분 못함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		normalVisitied = new boolean[N][N];
		sickVisitied = new boolean[N][N];
		
		for (int i = 0 ; i < N ; i ++) {
			String[] input = br.readLine().split("");
			for (int j = 0 ; j < N ; j ++) {
				String color = input[j];
				if(color.equals("R")) grid[i][j] = R;
				if(color.equals("G")) grid[i][j] = G;
				if(color.equals("B")) grid[i][j] = B;
			}
		}
		
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				if(!normalVisitied[i][j]) {
					findChunk(i,j);
					nCnt ++;
				}
				
				if(!sickVisitied[i][j]) {
					findSickChunk(i,j);
					sCnt ++;
				}
			}
		}
		
		System.out.println(nCnt + " " + sCnt);
	}
	
	public static void findChunk(int x, int y) {
		normalVisitied[x][y] = true;
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(!normalVisitied[nx][ny] && grid[nx][ny] == grid[x][y]) {
				findChunk(nx, ny);
			}
		}
	}
	
	public static void findSickChunk(int x, int y) {
		sickVisitied[x][y] = true;
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(!sickVisitied[nx][ny] 
					&& (grid[nx][ny] == grid[x][y] 
					|| (grid[x][y] == R && grid[nx][ny] == G) 
					|| (grid[x][y] == G && grid[nx][ny] == R))) {
				findSickChunk(nx, ny);
			}
		}
	}
}
