class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        
        int[][] check = new int[n][3];
        int[] sign = new int[n];
        
        
        for(int i = 0 ; i < n ; i ++){
            for (int j = 0 ; j < 3 ; j ++){
                check[i][j] = signals[i][j];
            }
        }
        
        for(int t = 1 ; t <= 100000000 ; t ++){
            // 시간 흐르기 
            for(int i = 0 ; i < n ; i ++){ // 몇번째 신호등인지 
                // 현재 신호에 맞는 시간 가서 조정하고 
                // if(check[i][sign[i]] == 0){ 
                //     check[i][sign[i]] = signals[i][sign[i]];
                //     sign[i] = (sign[i] + 1) % 3;
                // }else{
                //     check[i][sign[i]] --;
                // }
                check[i][sign[i]] --;
                if(check[i][sign[i]] == 0){
                    check[i][sign[i]] = signals[i][sign[i]];
                    sign[i] = (sign[i] + 1) % 3;
                }

                // 0이면 신호 바꾸고 원복하고 
                
            }
            
            // 모두 노란색인지 확인 
            boolean isAllY = true;
            for (int i = 0 ; i < n ; i ++){
                if(sign[i] != 1) isAllY  = false;
            }
            
            if(isAllY) return t + 1;
        }
        
        
        
        
        int answer = 0;
        return -1;
    }
}