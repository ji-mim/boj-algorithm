import java.io.*;
import java.util.*;

public class Solution {
	
	public static int N, M ;
	public static int [][] arr; 
	public static int [][] ps; 
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static int ans ;

	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ans = 0;
			
			arr = new int[N+1][N+1];
			ps = new int[N+1][N+1];
			
			for (int i = 1 ; i <= N ; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1 ; j <= N ; j ++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					ps[i][j] = arr[i][j] + ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
				}
			}
			
			for (int x1 = 1 ; x1 <= N - M + 1 ; x1 ++) {
				for (int y1 = 1 ; y1 <= N - M + 1 ; y1 ++) {
					int x2 = x1 + M - 1;
					int y2 = y1 + M - 1;
					int sum = ps[x2][y2] - ps[x1-1][y2] - ps[x2][y1-1] + ps[x1-1][y1-1];
					ans = Math.max(sum, ans);
				}
			}
			
			System.out.printf("#%d %d\n", tc, ans);
		}
		
	}

}
