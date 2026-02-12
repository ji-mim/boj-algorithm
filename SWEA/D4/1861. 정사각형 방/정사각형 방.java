import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, T; 
	static int [][] arr;
	static int [][] dp;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static ArrayList<int []> nums = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
            nums.clear();

			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			dp = new int[N][N];
			for (int i = 0 ; i < N ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					nums.add(new int[]{i,j,arr[i][j]});
				}
			}
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					dp[i][j] = 1;
				}
			}

			
			nums.sort((a,b) -> Integer.compare(a[2], b[2]));
			
			for (int[] num : nums) {
				findDP(num);
			}
			
			int maxX = 0;
			int maxY = 0;
			int maxVal = 0;
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if (dp[i][j] >= maxVal) {
						if (dp[i][j] == maxVal) {
							if(arr[i][j] < arr[maxX][maxY]) {
								maxX = i;
								maxY = j;
								maxVal = dp[i][j];
							}
							continue;
						}
						maxX = i;
						maxY = j;
						maxVal = dp[i][j];
					}
				}
			}
			
			System.out.println("#" + tc + " " + (arr[maxX][maxY] - maxVal + 1) + " " + maxVal);
		}
		
	}
	
	public static void findDP(int[] num) {
		int x = num[0];
		int y = num[1];
		int val = arr[x][y];
		
		for (int i = 0 ; i < 4 ; i ++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(inRange(nx,ny) && arr[nx][ny] == val + 1) {
				dp[nx][ny] = dp[x][y] + 1;
				break;
			}
		}
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<= y && y < N;
	}

}
