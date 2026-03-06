import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map, dp;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][M];
		for (int[] d : dp) {
			Arrays.fill(d, -1);
		}
		
		dp[N-1][M-1] = 1;
		
		backtrack(0,0);
		
		System.out.println(dp[0][0]);
		
	}
	
	static int backtrack(int x, int y) {
		if(dp[x][y] != -1) return dp[x][y];
		int val = 0 ;
		
		for (int i = 0 ; i < 4 ; i ++) {
			// 아직 안갔고 나보다 작은 곳이라면 그 값을 더함
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			if(map[nx][ny] >= map[x][y]) continue;
			val += backtrack(nx, ny);
		}
		
		
		return dp[x][y] = val;
	}

}
