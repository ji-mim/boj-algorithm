import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        
        ArrayList<Integer> honors = new ArrayList<>();
        int[] answer = new int[score.length];
        
        for(int i = 0 ; i < score.length ; i ++){
            honors.add(score[i]);
            Collections.sort(honors);
            Collections.reverse(honors);
            
            if(honors.size() > k){
                honors.remove(honors.size() - 1);
            }
            
            // System.out.println(Arrays.toString(honors.toArray()));
            
            answer[i] = honors.get(honors.size() - 1);
            
        }
        
        
        return answer;
    }
}