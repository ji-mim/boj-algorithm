import java.util.*;

class Solution {
    public int solution(int[] order) {
        
        Deque<Integer> stack1 = new ArrayDeque<>(); // 오름차순 스택
        Deque<Integer> stack2 = new ArrayDeque<>(); // 내림차순 스택 
        
        for(int i = order.length ; i >= 1 ; i --){
            stack1.push(i);
        }
        
        int cnt = 0 ;
        
        for(int o : order){
            boolean isLoad = false;
            while(true){
                if(!stack1.isEmpty() && stack1.peek() == o){ //stack1 에서 바로 꺼낼 수 있는지 확인 
                    stack1.pop();
                    cnt ++;
                    isLoad = true;
                    break;
                }
                
                if(!stack2.isEmpty() && stack2.peek() == o){ //stack2에서 꺼낼 수 있는지 확인 
                    stack2.pop();
                    cnt ++;
                    isLoad = true;
                    break;
                }
                
                if(stack1.isEmpty() && !stack2.isEmpty() && stack2.peek() != o){ //종료해야 하는 상황인지 확인 
                    break;
                }
                
                stack2.push(stack1.pop()); // 전부 아니면 일단 넘기고 다시 확인 
            }
            if(!isLoad) break;
        }
        
        return cnt;
    }
}