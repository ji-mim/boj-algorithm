import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        // 누적합 구하기 
        int n = sequence.length;
        int[] presum = new int[n + 1];
        int[] ans = new int[2];
        int minLen = Integer.MAX_VALUE;
        
        presum[1] = sequence[0];
        
        for(int i = 2 ; i < n + 1 ; i ++){
            presum[i] = presum[i - 1] + sequence[i - 1];
        }
        
        
        for (int i = 1 ; i < n + 1 ; i ++){
            
            int left = i ; 
            int right = n ;
            
            while(left <= right){
                int mid = (left + right) / 2 ;
                int sum = presum[mid] - presum[i - 1];
                
                if(sum == k){
                    int len = mid - i + 1;
                    if(len < minLen){
                        minLen = len;
                        ans[0] = i;
                        ans[1] = mid;
                    }
                    
                    right = mid - 1;
                }else if (sum < k){
                    left = mid + 1;
                }else if (sum > k){
                    right = mid - 1;
                }
            }
        }
        
        ans[0] -= 1;
        ans[1] -= 1;
        
        System.out.println(Arrays.toString(ans));
        
        
        
        return ans;
    }
}