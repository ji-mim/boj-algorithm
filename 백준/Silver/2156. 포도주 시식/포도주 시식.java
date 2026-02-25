import java.io.*;
import java.util.*;

public class Main {
	
	static int [] dp = new int[10001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] drinks = new int[N + 1];
		
		for (int i = 1 ; i <= N ; i ++) {
			drinks[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 0;
		dp[1] = drinks[1];
		
		if(N >= 2) {
			dp[2] = drinks[1] + drinks[2];
		}
		if(N>=3) {
			dp[3] = Math.max(drinks[1] + drinks[2], Math.max(drinks[1] + drinks[3], drinks[2] + drinks[3]));
		}
		
		for (int i = 4 ; i <= N ; i ++) {
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + drinks[i], dp[i-3] + drinks[i] + drinks[i-1]));
		}
		
		System.out.println(dp[N]);
		
		
		
		
	}
}
