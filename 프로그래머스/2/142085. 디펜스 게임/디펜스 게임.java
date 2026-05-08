import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int ans = 0 ;
        if(k >= enemy.length) return enemy.length;
        
        for(int i = 0 ; i < enemy.length ; i ++){
            pq.add(enemy[i]);
            
            if(pq.size() > k){
                int e = pq.poll();
                n -= e;
                
                if(n < 0){
                    break;
                }
            }
            
            ans = i + 1;
            
        }
        
        return ans;
    }
}