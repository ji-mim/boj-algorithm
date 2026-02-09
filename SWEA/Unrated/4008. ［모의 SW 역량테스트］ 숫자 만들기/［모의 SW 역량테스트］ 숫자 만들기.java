import java.io.*;
import java.util.*;

public class Solution {
    public static int T, N;
    public static int[] nums;
    public static int maxVal, minVal;
    public static int plus, minus, divide, multi;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    //중복 순열 

    public static void main(String args[]) throws IOException{
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1 ; tc <= T ; tc ++){
            maxVal = Integer.MIN_VALUE;
            minVal = Integer.MAX_VALUE;

            N = Integer.parseInt(br.readLine());
            nums = new int[N];

            st = new StringTokenizer(br.readLine());
            plus = Integer.parseInt(st.nextToken());
            minus = Integer.parseInt(st.nextToken());
            multi = Integer.parseInt(st.nextToken());
            divide = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            for (int i = 0 ; i < N ; i ++){
                nums[i] = Integer.parseInt(st.nextToken());
            }

            backtrack(1, nums[0]);

            System.out.printf("#%d %d\n", tc, maxVal - minVal);

        }
    }

    public static void backtrack(int idx, int sum){
        if (idx == N){
            maxVal = Math.max(maxVal, sum);
            minVal = Math.min(minVal, sum);

            return;
        }

        int next = nums[idx];

        if(plus > 0){
            plus -- ;
            backtrack(idx + 1, sum + next);
            plus ++;
        }

        if(minus > 0){
            minus -- ;
            backtrack(idx + 1, sum - next);
            minus ++;
        }

        if(multi > 0){
            multi --;
            backtrack(idx + 1, sum * next);
            multi ++;
        }

        if(divide > 0){
            divide --;
            backtrack(idx + 1, sum / next);
            divide ++;
        }

    }
}
