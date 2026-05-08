import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int len = score.length;
        
        if (len < m) return 0;
        
        Arrays.sort(score);
        
        int ans = 0 ;
        
        for(int i = len - m ; i >= 0 ; i -= m){
            ans += score[i];
        }
        
        ans *= m;
        
        
        
        return ans;
    }
}