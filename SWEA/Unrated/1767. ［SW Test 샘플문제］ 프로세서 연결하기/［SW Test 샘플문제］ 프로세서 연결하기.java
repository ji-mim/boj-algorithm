import java.util.*;
import java.io.*;

public class Solution {
	
	public static class Core{
		int x, y;
		
		public Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int T,N;
	public static int[][] arr;
	public static int[][] lineArr;
	public static int lineCnt;
	public static char LINE = '_';
	public static ArrayList<Core> coreList = new ArrayList<>();
	public static ArrayList<Integer> selectedDir = new ArrayList<>();
	public static ArrayList<int[]> backup = new ArrayList<>();
	public static int activeCoreCnt;
	public static int maxCoreCnt;
	public static int[] dx = {0,-1,1,0,0};
	public static int[] dy = {0,0,0,-1,1};
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static int coreCnt;
	public static int bestConnected, bestLength;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			lineArr = new int[N][N];
			coreCnt = 0;
			coreList.clear();
			bestConnected = -1;
			bestLength = Integer.MAX_VALUE;
			activeCoreCnt = 0;

			
			inputArr();
			dfs(0,activeCoreCnt,0);
			
			System.out.printf("#%d %d\n", tc, bestLength);
		}
	}
	
	public static void dfs(int idx, int connected, int length) {
		if(connected + (coreCnt - idx) < bestConnected) return; 
		if(connected + (coreCnt - idx) == bestConnected && length >= bestLength) return;
		
	    if (idx == coreCnt) {
	        if (connected > bestConnected) {
	            bestConnected = connected;
	            bestLength = length;
	        } else if (connected == bestConnected) {
	            bestLength = Math.min(bestLength, length);
	        }
	        return;
	    }
		
		Core c = coreList.get(idx);
		
		for (int dir = 1 ; dir <= 4 ; dir ++) {
			int len = canConnectLen(c,dir);
			if(len == -1) continue;
			
			setWire(c,dir,true);
			dfs(idx + 1, connected + 1 , length + len);
			setWire(c,dir,false);
		}
		
		dfs(idx + 1, connected, length);
	}
	
	public static int canConnectLen(Core c, int dir) {
		int x = c.x;
		int y = c.y;
		int len = 0;
		
		while (true) {
			x += dx[dir];
			y += dy[dir];
			if(!inRange(x,y)) return len;
			if(arr[x][y] != 0) return -1;
			
			len ++;
		}
	}
	
	public static void setWire(Core c, int dir, boolean put) {
		int x = c.x;
		int y = c.y;
		
		int val = put ? LINE : 0;
		
		while(true) {
			x += dx[dir];
			y += dy[dir];
			
			if(!inRange(x,y)) return;
			arr[x][y] = val;
		}
	}
	
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<=y && y < N;
	}
	
	
	public static void printLineArr() {
		System.out.println("===============================");
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}
	
	public static void inputArr() throws IOException{
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 1) {
					if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
						activeCoreCnt ++;
					}else {
						coreList.add(new Core(i,j));
						coreCnt ++;
					}
				}
			}
		}
	}
}
