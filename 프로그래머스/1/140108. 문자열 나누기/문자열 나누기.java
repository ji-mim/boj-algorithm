import java.util.*;

class Solution {
    public int solution(String s) {
        char[] arr = s.toCharArray();
        
        int cnt = 0; 
        char x = arr[0];
        int xCnt = 0;
        int notXCnt = 0;
        
        for(int i = 0 ; i < arr.length ; i ++){
            if(x == arr[i]) xCnt ++;
            else notXCnt ++;
            
            if(xCnt == notXCnt) {
                cnt ++;
                if(i != arr.length - 1) {
                    x = arr[i + 1];
                }
                
                xCnt = 0 ;
                notXCnt = 0;
            }
            
        }
        
        if(xCnt > 0) cnt ++;
        
        int answer = 0;
        return cnt;
    }
}