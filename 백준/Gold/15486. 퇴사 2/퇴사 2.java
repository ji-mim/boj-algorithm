import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 2];
		
		int[] t = new int[N + 2];
		int[] p = new int[N + 2];
		
		for (int i = 1 ; i <= N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dp[i] = 0 ; 
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1 ; i <= N ; i ++) {
			dp[i + 1] = Math.max(dp[i], dp[i + 1]);
			if (i + t[i] <= N + 1) {
				dp[i + t[i]] = Math.max(dp[i] + p[i], dp[i + t[i]]);
			}
		}
		int ans = 0 ;
		for (int i = 1 ; i <= N + 1 ; i ++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		
		
	}

}
