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
			if(dfs(r, 0)) cnt ++;;
		}
		
		System.out.println(cnt);
		
	}
	
	private static boolean dfs(int x, int y) {
		visited[x][y] = true;
		if(y == C - 1)  return true;

		for (int i = 0 ; i < 3 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			if(visited[nx][ny]) continue;
			if(map[nx][ny] == 'x') continue;
			
			if(dfs(nx, ny)) return true;;
		}
		
		return false;
	}
}
