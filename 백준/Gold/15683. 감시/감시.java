import java.io.*;
import java.util.*;

public class Main {
	
	//모든 cctv의 개수를 구하고, 좌표를 기록해둔 뒤, 각각의 CCTV가 1~4개를 선택할 수 있는 모든 경우의 수에 대해서 탐색을 해보자 
	// 8^ 4 = 128 충분..??
	// CCTV가 1이면 진행 방향, 2는 진행방향 기준 뒤족도, 3은 진행방향 기준 위쪽도, 4는 진행방향 기준 위, 뒤, 5는 진행방향 기준 위 아래 뒤 
	
	// 방향은 동남서북
	
	public static class CCTV{
		int x, y, type;
		
		public CCTV(int x, int y, int type) {
			this.x = x; 
			this.y = y;
			this.type = type;
		}
	}
	
	public static int[] dx = {0,1,0,-1}; // 동남서북
	public static int[] dy = {1,0,-1,0};
	
	public static int N, M;
	public static int min = Integer.MAX_VALUE;
	public static int [][] arr; 
	public static int [][] turnOnArr; 
	
	public static int[] cctvType = {1,2,3,4,5};
	public static int cctvCnt;
	
	public static ArrayList<CCTV> cctvList = new ArrayList<>();
	public static ArrayList<Integer> watchDir = new ArrayList<>();
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		turnOnArr = new int[N][M];
		
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0 && arr[i][j] != 6) {
					cctvList.add(new CCTV(i,j,arr[i][j]));
				}
			}
		}
		
		cctvCnt = cctvList.size();
		selectDir(0); // 중복 순열 
		
		System.out.println(min);
		
	}
	
	public static void selectDir(int idx) {
		if(idx == cctvCnt) {
//			System.out.println(Arrays.toString(watchDir.toArray()));
			min = Math.min(min, countBlindSpot());
//			printTurnOnArr();
			return;
		}
		
		for (int i = 0 ; i <= 3 ; i ++) {
			watchDir.add(i);
			selectDir(idx + 1);
			watchDir.remove(watchDir.size() - 1);
		}
	}
	
	public static int countBlindSpot() {
		initTurnOnArr();
		
		for (int i = 0 ; i < cctvCnt ; i ++) {
			int idx = i;
			int dir = watchDir.get(i);
			turnOnCCTV(idx, dir);
		}
		int count = counting();
		return count;
	}
	
	public static int counting() {
		int count = 0;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				if(turnOnArr[i][j]  == 0) count ++;
			}
		}
		
		return count;
	}
	
	public static void turnOnCCTV(int idx, int dir) {
		CCTV cctv = cctvList.get(idx);
		
		//cctv 타입을 먼저 구하고, 방향 기준으로 타입별 볼 수 있는 곳을 체크
		if(cctv.type == 1) {
			int x = cctv.x;
			int y = cctv.y;
			int d = dir;
			while(true) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(!inRange(nx,ny) || arr[nx][ny] == 6) break;
				if(arr[nx][ny] == 0) turnOnArr[nx][ny] = '#';
				x = nx;
				y = ny;
			}
		}else if (cctv.type == 2) {
			int x = cctv.x;
			int y = cctv.y;
			int d = dir;
			for (int i = 0 ; i < 2 ; i ++) {
				while(true) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx,ny) || arr[nx][ny] == 6) break;
					if(arr[nx][ny] == 0) turnOnArr[nx][ny] = '#';
					x = nx;
					y = ny;
				}
				d = (d + 2) % 4;
				x = cctv.x;
				y = cctv.y;
			}
		}else if(cctv.type == 3) {
			int x = cctv.x;
			int y = cctv.y;
			int d = dir;
			for (int i = 0 ; i < 2 ; i ++) {
				while(true) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx,ny) || arr[nx][ny] == 6) break;
					if(arr[nx][ny] == 0) turnOnArr[nx][ny] = '#';
					x = nx;
					y = ny;
				}
				d = (d + 3) % 4;
				x = cctv.x;
				y = cctv.y;

			}
		}else if (cctv.type == 4) {
			int x = cctv.x;
			int y = cctv.y;
			int d = dir;
			for (int i = 0 ; i < 3 ; i ++) {
				while(true) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx,ny) || arr[nx][ny] == 6) break;
					if(arr[nx][ny] == 0) turnOnArr[nx][ny] = '#';
					x = nx;
					y = ny;
				}
				d = (d + 3) % 4;
				x = cctv.x;
				y = cctv.y;

			}
		}else if (cctv.type == 5) {
			int x = cctv.x;
			int y = cctv.y;
			int d = dir;
			for (int i = 0 ; i < 4 ; i ++) {
				while(true) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!inRange(nx,ny) || arr[nx][ny] == 6) break;
					if(arr[nx][ny] == 0) turnOnArr[nx][ny] = '#';
					x = nx;
					y = ny;
				}
				d = (d + 1) % 4;
				x = cctv.x;
				y = cctv.y;

			}
		}
	}
	
	public static void printTurnOnArr() {
		System.out.println("===============");
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				System.out.print(turnOnArr[i][j] + " "); 
			}
			System.out.println();
		}
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<=y && y < M;
	}
	
	public static void initTurnOnArr() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				turnOnArr[i][j] = arr[i][j] ; 
			}
		}
	}
}
