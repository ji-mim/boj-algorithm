import java.io.*;
import java.util.*;


public class Solution {
    static String A, B;
    static int[][] dp;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc <= T ; tc ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            A = st.nextToken();
            B = st.nextToken();
            
            dp = new int[A.length()][B.length()];
            
            for (int[] d : dp){
                Arrays.fill(d, -1);
            }

            System.out.println("#" + tc + " " + lcs(0,0));
        }
    }

    public static int lcs(int i, int j){
        if(i == A.length() || j == B.length()) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        if(A.charAt(i) == B.charAt(j)) return dp[i][j] = 1 + lcs(i + 1, j + 1);

        return dp[i][j] = Math.max(lcs(i + 1, j), lcs(i, j + 1));
        
    }
}
