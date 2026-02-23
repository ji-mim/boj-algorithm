import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int MIN_INT = Integer.MIN_VALUE;

        int[] stairs = new int[N + 1];

        for (int i = 1 ; i <= N ; i ++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N + 1][3] ;
        
        for (int i = 0 ; i <= N ; i ++){
            Arrays.fill(dp[i], MIN_INT);
        }

        /**
         * 	dp[i][1] : i번째 계단을 밟고 있고, 바로 직전이 i-2였던 경우(= i로 올 때 2칸 점프)
            즉, 연속으로 밟은 개수가 1
            dp[i][2] : i번째 계단을 밟고 있고, 바로 직전이 i-1이었던 경우(= 1칸 이동)
            즉, 연속으로 밟은 개수가 2
         */

        dp[1][1] = stairs[1];

        if (N >= 2){
            dp[2][2] = stairs[1] + stairs[2];
            dp[2][1] = stairs[2];
        }

        for (int i = 3 ; i <= N ; i ++){
            dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
            dp[i][2] = dp[i-1][1] + stairs[i];
        }

        int ans = 0 ; 

        ans = Math.max(dp[N][1], dp[N][2]);

        System.out.println(ans);
        


    }
    
}
