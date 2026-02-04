import java.io.*;
import java.util.*;

public class Main {
	
	// 시간 복잡도 O(n ^ 3) n <= 100
	
	// 모든 높이에 대해서 잠기는 구역을 0으로 표시하고, 갈 수 있는 최대 높이를 찾아낸다
	
	public static int MAX_LEN = 100; 
	
	public static int N;
	public static int ans, countArea;
	
	public static int [][] arr = new int[MAX_LEN][MAX_LEN];
	public static int [][] rainedArr = new int[MAX_LEN][MAX_LEN];
	public static boolean [][] visited = new boolean[MAX_LEN][MAX_LEN];
	
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st; 
	
	public static void main(String[] args) throws IOException{
		
		N = Integer.parseInt(br.readLine());
		inputArr();
		
		for (int h = 0 ; h <= 101 ; h ++) { // h는 비의 높이를 의미 
			raining(h);
			initVisited();
			countArea = 0;
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if(!visited[i][j] && rainedArr[i][j] != 0) {
						dfs(i,j);
						countArea ++;
					}
				}
			}
			ans = Math.max(ans, countArea);
		}
		
		System.out.println(ans);
	}
	
	public static void dfs(int x, int y) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		visited[x][y] = true;
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(canGo(nx,ny)) {
				dfs(nx,ny);
			}
		}
	}
	
	public static boolean canGo(int x, int y) {
		return (inRange(x,y) && !visited[x][y] && rainedArr[x][y] != 0);
	}
	
	public static boolean inRange(int x, int y) {
		return (0<=x && x <N && 0<=y && y < N);
	}
	
	public static void inputArr() throws IOException {
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void initVisited() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void raining(int h) {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				// 만약 h보다 값이 작거나 같으면 0으로 값을 넣어주기 
				if(arr[i][j] <= h) {
					rainedArr[i][j] = 0 ;
				}else {
					rainedArr[i][j] = arr[i][j];
				}
			}
		}
	}
}
