import java.io.*;
import java.util.*;

public class Main {
	
	static char[][] map;
	static boolean[][] visited;
	static int [] dx = {-1, 0, 1}; // 우상, 우, 우하
	static int [] dy = {1, 1, 1};
	static int R, C, cnt;
	static boolean findWay;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0 ; i < R ; i ++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0 ; j < C ; j ++) {
				map[i][j] = input[j];
			}
		}
		
		for (int r = 0 ; r < R ; r ++) {
			if(visited[r][0]) continue;
//			visited[r][0] = true;
			findWay = false;
			dfs(r, 0);
		}
		
		System.out.println(cnt);
		
	}
	
	private static void dfs(int x, int y) {
		if(findWay) return;
		visited[x][y] = true;
		for (int i = 0 ; i < 3 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if(visited[nx][ny]) continue;
			if(map[nx][ny] == 'x') continue;
//			visited[nx][ny] = true;
			if(ny == C - 1) {
				cnt ++;
				findWay = true;
//				System.out.println("====================");
//				for(boolean[] b : visited) {
//					System.out.println(Arrays.toString(b));
//				}
				return;
			}
			
			
			dfs(nx, ny);
		}
//		if(!findWay) visited[x][y] = false;
	}
}
