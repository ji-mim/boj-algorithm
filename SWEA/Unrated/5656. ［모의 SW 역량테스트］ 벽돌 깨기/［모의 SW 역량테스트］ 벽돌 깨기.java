import java.io.*;
import java.util.*;

public class Solution {

	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	private static int N,W,H,min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine()); // 테스트케이스 
		
		for (int tc = 1 ; tc <= TC ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			for (int i = 0 ; i < H ; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < W ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			drop(0, map);
			System.out.println("#" + tc + " " + min);
		}
	}
	
	static void copy(int[][] src, int[][] dest) {
		for (int r = 0 ; r < H ; r ++) {
			for (int c = 0 ; c < W ; c ++) {
				dest[r][c] = src[r][c];
			}
		}
	}
	
	static int getRemain(int[][] map) {
		int cnt = 0;
		for (int r = 0 ; r < H ; r ++) {
			for (int c = 0 ; c < W ; c ++) {
				if(map[r][c] != 0) ++ cnt;
			}
		}
		
		return cnt;
	}
	
	static boolean drop(int count, int[][] map){ // 구슬 떨어뜨리기 : 중복 순열 
		int remainCount = getRemain(map);
		if(remainCount == 0) {
			min = 0;
			return true; //이 문제의 최적해 등장~
		}
		if(count == N) { // 모든 구슬을 다 던졌다면 남은 벽돌 개수 최소값 갱신 
			min = Math.min(min, remainCount);
			return false;
		}
		
		
		int[][] newMap = new int[H][W];
		for(int c = 0 ; c < W ; c ++) {
			// c열에 구슬 낙하했을 경우 부서지게 되는 첫 벽돌 찾기
			int r = 0;
			while(r < H && map[r][c] == 0) ++ r;
			// 그런 벽돌이 없다면 부서지는 벽돌이 없으므로 현재 구슬로 다음 열 시도
			if(r == H) continue;
			
			// 그런 벽돌이 있다면 벽돌 부수기
			copy(map, newMap);
			int brick = map[r][c];
			// 연쇄 폭발
			boom(newMap, r, c);
			// 벽돌 내리기
			if(brick>1) down(newMap);
			//다음 구슬 떨어뜨리기
			if(drop(count + 1, newMap)) return true;
		}
		
		return false;
		
	}
	
	static void boom(int[][] map, int r, int c){ 
		Queue<Point> queue = new ArrayDeque<>();
		if(map[r][c] > 1) queue.offer(new Point(r,c,map[r][c]));
		map[r][c] = 0;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int d = 0 ; d < 4 ; d ++) {
				int nr = cur.r;
				int nc = cur.c;
				for (int k = 0 ; k < cur.no - 1 ; k ++) {
					nr += dr[d];
					nc += dc[d];
					if(nr >= H || nr < 0 || nc >= W || nc < 0) continue;
					if(map[nr][nc] == 0) continue;
					if(map[nr][nc] > 1) queue.offer(new Point(nr,nc,map[nr][nc]));				
					map[nr][nc] = 0;
				}
			}
		}
	}
	
	static void down(int[][] map) {
		//모든 열에 대해 처리(열 고정 후 처리)
		for(int c = 0 ; c < W ; c ++) {
			// 맨 아래부터 올라오며 빈칸 찾기 
			int er = H - 1;
			while(er >=0 && map[er][c] != 0) --er;
			
			if (er < 0) continue;
			for (int r = er - 1 ; r >= 0 ; r --) {
				if(map[r][c] != 0) {
					map[er][c] = map[r][c];
					map[r][c] = 0;
					--er;
				}
			}
			
		}
	}
	
	static class Point{
		int r,c,no;

		public Point(int r, int c, int no) {
			super();
			this.r = r;
			this.c = c;
			this.no = no;
		}
		
		
	}
}
