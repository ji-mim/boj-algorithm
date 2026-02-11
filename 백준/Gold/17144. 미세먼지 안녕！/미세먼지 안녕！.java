
import java.io.*;
import java.util.*;

class Dirst{
	int x, y, size;
	
	public Dirst(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
}

public class Main {

	static int[] up, down;
	static int N, M, T;
	static int [][] arr, move, spread;  
	static ArrayDeque<Dirst> dirstQ = new ArrayDeque<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main (String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		move = new int[N][M];
		spread = new int[N][M];
		up = new int[2];
		down = new int[2];
		
		
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1 && up[0] == 0) { // 여기서 실수 
					up[0] = i;
					up[1] = j;
					down[0] = i + 1;
					down[1] = j;
				}
				if(arr[i][j] > 0) {
					dirstQ.add(new Dirst(i, j, arr[i][j]));
				}
			}
		}
		
		for (int t = 0 ; t < T ; t ++) {
			initArrs(); // move,spread 초기화
			
			spreadDirst(); // dirstList의 먼지 전파
			
			blow(); // 공기청정기 동작 바람 돌리기 
			
			//배열 동기화, que에 집어넣기 
			sync();
		}
		
		int ans = getAns();
		System.out.println(ans);
		
	}
	
	public static void spreadDirst() {
		while(!dirstQ.isEmpty()) {
			Dirst curr = dirstQ.poll();
			int x = curr.x;
			int y = curr.y;
			int size = curr.size; 
			int spreadSize = (int) curr.size / 5; 
			
			for (int k = 0 ; k < 4 ; k ++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(canSpread(nx,ny)) { // 배열 안에 있고, 공기청정기와 만나지 않을 때 
					spread[nx][ny] += spreadSize;
					size -= spreadSize;
				}
			}
			spread[x][y] += size;
		}
		
		// 이부분 빠트림 
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) { 
				move[i][j] = spread[i][j];
			}
		}
	}
	
	public static boolean canSpread(int x, int y) {
		return ((0<= x && x < N && 0<=y && y < M) && arr[x][y] != -1);
	}
	
	public static void blow() {
		// 위쪽 
		blowUpArea();
		//아래쪽 
		blowDownArea();
	}
	
	public static void blowDownArea() {
		int x = down[0];
		int y = down[1];
		
		//왼쪽 
		for (int i = x ; i < N - 1 ; i ++ ) {
			move[i][0] = spread[i + 1][0];
		}
		
		//아래쪽
		for(int i = 0 ; i < M - 1 ; i ++) {
			move[N - 1][i] = spread[N - 1][i + 1];
		}
		
		//오른쪽
		for(int i = N - 1 ; i > x ; i --) {
			move[i][M - 1] = spread[i - 1][M - 1];
		}
		
		//위쪽
		for(int i = M - 1 ; i > 0 ; i --) {
			move[x][i] = spread[x][i - 1];
		}
		
		move[x][y + 1] = 0;
		move[x][y] = -1;
	}
	
	public static void blowUpArea() {
		int x = up[0];
		int y = up[1];
		
		//왼쪽  
		for (int i = x ; i > 0 ; i -- ) {
			move[i][0] = spread[i - 1][0];
		}
		
		//위쪽
		for(int i = 0 ; i < M - 1 ; i ++) {
			move[0][i] = spread[0][i + 1];
		}
		
		//오른쪽 여기 실수
		for(int i = 0 ; i < x ; i ++) {
			move[i][M-1] = spread[i + 1][M-1];
		}
		
		//아래쪽
		for(int i = M - 1 ; i > 0 ; i --) {
			move[x][i] = spread[x][i - 1];
		}
		
		move[x][y + 1] = 0;
		move[x][y] = -1;
	}
	
	public static void sync() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) { 
				arr[i][j] = move[i][j];
				if (arr[i][j] > 0) {
					dirstQ.add(new Dirst(i, j, arr[i][j]));
				}
			}
		}
	}
	
	public static int getAns() {
		int cnt = 0 ;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) { 
				if (arr[i][j] > 0) {
					cnt += arr[i][j];
				}
			}
		}
		return cnt;
	}
	
	
	
	public static void initArrs() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) { 
				move[i][j] = spread[i][j] = 0;
			}
		}
	}
}
