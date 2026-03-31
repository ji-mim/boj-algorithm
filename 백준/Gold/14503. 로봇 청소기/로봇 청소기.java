import java.io.*;
import java.util.*;

public class Main {

	
	static int N, M;
	static int[][] room;
	static boolean[][] cleaned;
	static int x, y, dir;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		cleaned = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		while(true) {
			// 현재 위치가 청소되지 않았으면 청소하기
			if(!cleaned[x][y]) cleaned[x][y] = true;
			
			// 주변 4칸을 둘러보고 청소되지 않은 빈칸이 있는지 체크하기
			boolean isDirty = checkNearBy();
			
			// 있다면 반시계로 90도 회전, 바라보는 방향 기준으로 앞쪽 칸이 청소되지 않은 빈칸이면 전진
			if(isDirty) {
				dir = (dir + 3) % 4;
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(inRange(nx, ny) && room[nx][ny] == 0 && !cleaned[nx][ny]) {
					x = nx;
					y = ny;
				}
			// 없다면 뒤로 한칸 후진, 이 때 뒤쪽 칸이 벽이라 후진 할 수 없으면 종료 
			}else {
				int nx = x + dx[(dir + 2) % 4];
				int ny = y + dy[(dir + 2) % 4];
				
				if(inRange(nx, ny) && room[nx][ny] == 1) break;
				
				x = nx;
				y = ny;
			}
		}
		
		// 청소한 구역 세기
		
		int ans = 0;
		
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				if(cleaned[i][j]) ans ++;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean checkNearBy() {
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(inRange(nx, ny) && room[nx][ny] == 0 && !cleaned[nx][ny]) { // 맵 내부이고, 벽이 아닌데, 청소하지 않았으면 true
				return true;
			}
		}
		
		
		return false;
	}
	
	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}
