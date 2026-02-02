import java.util.*;
import java.io.*;

public class Main {
	
	public static int N, M;
	public static int [][] arr ; 
	public static int [][] ps ;
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st; 
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][N + 1];
		ps = new int[N + 1][N + 1];
		
		for (int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1 ; j <= N ; j ++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				ps[i][j] = arr[i][j] + ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
			}
			
		}
		
		for (int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = ps[x2][y2] - ps[x1 - 1][y2] - ps[x2][y1 - 1] + ps[x1 - 1][y1 - 1];
			sb.append(sum + "\n");

		}
		
		System.out.println(sb);
		
		
	}

}
