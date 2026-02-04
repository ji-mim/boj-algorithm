import java.util.*;
import java.io.*;

public class Solution {
	
	//사다리 입력을 받을 좌표 하나, 방문 배열 하나 
	//조건 좌우에 1인 좌표가 있는지 확인, 있다면 그 방향으로 0이 나올 때 까지 이동
	public static int LEN = 100;
	public static int [][] ladder = new int[LEN][LEN];
	public static boolean [][] visited = new boolean[LEN][LEN];
	public static StringTokenizer st;
	public static BufferedReader br;
	public static boolean flag;
	public static boolean endOfLine;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1 ; tc <= 10 ; tc ++) {
			br.readLine();
			initArr();
			flag = false;
			for (int i = 0 ; i < LEN ; i ++) {
				if(ladder[0][i] == 1) {
					initVisited();
					endOfLine = false;
					dfs(0,i);
					if(flag) {
						System.out.printf("#%d %d\n", tc, i);
						break;
					}
				}
			}
		}
	}
	
	public static void dfs(int x, int y) {
		int [] dx = {0,0,1}; // 좌우하
		int [] dy = {-1,1,0};
		
		if(endOfLine) return;
		
		visited[x][y] = true;
		
		for (int i = 0 ; i < 3 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (canGo(nx,ny)) {
				if(ladder[nx][ny] == 2) {
					flag = true;
					return;
				}
				if (nx == LEN - 1) {
					endOfLine = true;
					return;
				}
				dfs(nx, ny);
			}
		}
	}
	
	public static boolean canGo(int x, int y) {
		return (inRange(x,y) && !visited[x][y] && ladder[x][y] != 0);
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < LEN && 0<=y && y < LEN;
	}
	
	public static void initArr() throws IOException{
		for (int i = 0 ; i < LEN ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < LEN ; j ++) {
				ladder[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = false;
			}
		}
	}
	
	public static void initVisited() {
		for (int i = 0 ; i < LEN ; i ++) {
			for (int j = 0 ; j < LEN ; j ++) {
				visited[i][j] = false;
			}
		}
	}
}
