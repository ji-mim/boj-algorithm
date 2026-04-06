import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] map;
	static int[][] memo;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new int[N * N + N + 1][N * N + N + 1];
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int[] m : memo) {
			Arrays.fill(m, -1);
		}
		
		System.out.println(backtrack(new int[]{0,0}, new int[] {0,1}));
		
		
	}
	
	static int backtrack(int[] prevPos, int[] curPos) {
		if(curPos[0] == N - 1 && curPos[1] == N - 1) return 1;
		if(memo[prevPos[0] * N + prevPos[1]][curPos[0] * N + curPos[1]] != -1) return memo[prevPos[0] * N + prevPos[1]][curPos[0] * N + curPos[1]]; 
		
		
		int dist = 0;
		// 가로 
		if(prevPos[0] == curPos[0]) {
			//가로로 이동 
			if(inRange(curPos[0], curPos[1] + 1) && map[curPos[0]][curPos[1] + 1] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0], curPos[1] + 1});
			}
			// 대각 이동 
			if(inRange(curPos[0] + 1, curPos[1] + 1) && map[curPos[0] + 1][curPos[1] + 1] != 1 && map[curPos[0]][curPos[1] + 1] != 1 && map[curPos[0] + 1][curPos[1]] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0] + 1, curPos[1] + 1});
			}
			
		}
		// 대각
		else if(prevPos[0] + 1 == curPos[0] && prevPos[1] + 1 == curPos[1]) {
			//가로로 이동 
			if(inRange(curPos[0], curPos[1] + 1) && map[curPos[0]][curPos[1] + 1] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0], curPos[1] + 1});
			}
			// 대각 이동 
			if(inRange(curPos[0] + 1, curPos[1] + 1) && map[curPos[0] + 1][curPos[1] + 1] != 1 && map[curPos[0]][curPos[1] + 1] != 1 && map[curPos[0] + 1][curPos[1]] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0] + 1, curPos[1] + 1});
			}
			//새로로 이동 
			if(inRange(curPos[0] + 1, curPos[1]) && map[curPos[0] + 1][curPos[1]] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0] + 1, curPos[1]});
			}
		}
		
		// 새로
		else if(prevPos[1] == curPos[1]) {
			//새로로 이동 
			if(inRange(curPos[0] + 1, curPos[1]) && map[curPos[0] + 1][curPos[1]] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0] + 1, curPos[1]});
			}
			// 대각 이동 
			if(inRange(curPos[0] + 1, curPos[1] + 1) && map[curPos[0] + 1][curPos[1] + 1] != 1 && map[curPos[0]][curPos[1] + 1] != 1 && map[curPos[0] + 1][curPos[1]] != 1) {
				dist += backtrack(new int[] {curPos[0], curPos[1]}, new int[] {curPos[0] + 1, curPos[1] + 1});
			}
		}
		
		
		return memo[prevPos[0] * N + prevPos[1]][curPos[0] * N + curPos[1]] = dist;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0 <= y && y < N;
	}

}
