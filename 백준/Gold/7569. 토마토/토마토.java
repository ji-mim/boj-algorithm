/**
 * 토마토 
 */

import java.util.*;
import java.io.*;



public class Main {
	// 익은 토마토의 위치를 모두 저장해두고, 마지막에 토마토 위치를 돌면서 최소를 찾고, 익은게 없으면 -1; 
	
	static class Tomato{
		int x, y, z;
		
		public Tomato(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static int N, M, H;
	
	public static int[][][] days;
	public static int[][][] boxArr;
	public static boolean[][][] visited; 
	public static List<Tomato> tomatoList = new ArrayList<>();
	public static ArrayDeque<Tomato> que = new ArrayDeque<>();
	public static int[] dx = {-1,1,0,0,0,0}; // 상,하,좌,우,위,아래
	public static int[] dy = {0,0,-1,1,0,0};
	public static int[] dz = {0,0,0,0,-1,1};

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		days = new int [H][N][M];
		boxArr = new int [H][N][M];
		visited = new boolean [H][N][M];
		
		for (int i = 0 ; i < H ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0 ; k < M ; k ++) {
					boxArr[i][j][k] = Integer.parseInt(st.nextToken());
					if(boxArr[i][j][k] == 1) {
						tomatoList.add(new Tomato(j,k,i));
					}
				}
			}
		}
		
		for(Tomato t : tomatoList) {
			push(t, 0);
		}
		
		bfs();
		
		int ans = 0;
		
		for (int i = 0 ; i < H ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				for (int k = 0 ; k < M ; k ++) {
					if (days[i][j][k] != 0) {
						ans = Math.max(ans, days[i][j][k]);
					}
				}
			}
		}
		
		if(isAllTomatoAging()) {
			System.out.println(ans);			
		}else {
			System.out.println(-1);
		}
	}
	
	public static boolean isAllTomatoAging() {
		for (int i = 0 ; i < H ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				for (int k = 0 ; k < M ; k ++) {
						if(boxArr[i][j][k] == 0) {
							return false;  
					}
				}
			}
		}
		
		return true;
	}
	
	public static void bfs() {
		while(!que.isEmpty()) {
			Tomato curr = que.poll();
			int x = curr.x;
			int y = curr.y;
			int z = curr.z;
			
			for (int i = 0 ; i < 6 ; i ++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				
				if(canGo(nx,ny,nz)) {
					push(new Tomato(nx,ny,nz), days[z][x][y] + 1);
				}
			}
		}
	}
	
	public static boolean canGo(int x, int y, int z) {
		return (inRange(x,y,z) && boxArr[z][x][y] == 0 && !visited[z][x][y]);
	}
	
	public static boolean inRange(int x, int y, int z) {
		return 0<=x && x < N && 0<= y && y < M && 0<=z && z < H;
	}
	
	public static void push(Tomato t, int day) {
		visited[t.z][t.x][t.y] = true;
		days[t.z][t.x][t.y] = day;
		que.offer(t);
		boxArr[t.z][t.x][t.y] = 1;
	}
}




