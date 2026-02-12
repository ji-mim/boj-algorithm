import java.io.*;
import java.util.*;

// 처음에 풀 때 못풀었음, 문제 이해를 잘못했고, 조합을 너무 많이 사용해서 시간초과 뜸
// 문제를 잘 읽으면 한번에 부분집합(조합)으로 풀 수 있는 문제였음

public class Solution {
    
    static int T, N, ans;
    
    static int[][] arr;
    static boolean[] picked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1 ; tc <= T ; tc ++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            ans = Integer.MAX_VALUE;
            picked = new boolean[N];
            picked[0] = true;
            for (int i = 0 ; i < N ; i ++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N ; j ++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            divideFood(1, 1);

            System.out.println("#" + tc + " " + ans);
        }
    }
    
    public static void divideFood(int idx, int cnt) {
        if (cnt == N/2) {
            ans = Math.min(ans, Math.abs(calcTaste(true) - calcTaste(false)));
            return;
        }

        if (idx == N) return;

        if (cnt + (N - idx) < N/2) return;

        picked[idx] = true;
        divideFood(idx + 1, cnt + 1);
        picked[idx] = false;
        divideFood(idx + 1, cnt);

        
    }
    static int calcTaste(boolean teamA) {
        int sum = 0;
        for (int i = 0; i < N ; i++) {
            if(picked[i] != teamA) continue;
            for (int j = i + 1; j < N ; j++) {
                if(picked[j] != teamA) continue;
                sum += arr[i][j] + arr[j][i];
            }
        }
        return sum;
    }
}
