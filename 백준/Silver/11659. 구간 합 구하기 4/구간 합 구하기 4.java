import java.util.*;
import java.io.*;

public class Main {
	
	public static int N, M;
	public static int [] arr; 
	public static int [] ps; 
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		ps = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1 ; i <= N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
			ps[i] = ps[i-1] + arr[i];
		}
		
		
		for (int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			sb.append(ps[x2] - ps[x1 - 1]);
			sb.append("\n");
			
		}
		
		System.out.println(sb);
		
		
		
	}
}
