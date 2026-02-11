import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, B;
	static int[] arr;
	static int minHeight;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			minHeight = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0 ; i < N ; i ++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			findHeight(0, 0);
			
			System.out.println("#" + tc + " " + (minHeight - B));
			
		}
	}
	
	public static void findHeight(int idx, int sum) {
		if (sum >= minHeight) return;
		
		if(idx == N) {
			if(sum < B) return;
			minHeight = Math.min(minHeight, sum); 
			return;
		}
		
		findHeight(idx + 1, sum + arr[idx]);
		findHeight(idx + 1, sum);
	}
}
