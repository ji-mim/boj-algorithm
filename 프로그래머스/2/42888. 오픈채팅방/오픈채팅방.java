import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        HashMap<String, String> nickNameMap = new HashMap<>();
        
        for(String r : record){
            String[] rList = r.split(" ");
            String command = rList[0];
            
            if(command.equals("Enter") || command.equals("Change")){
                String uid = rList[1];
                String nickName = rList[2];
                nickNameMap.put(uid, nickName);
            }     
        }
                
        
        List<String> results = new ArrayList<>();
        
        for(String r : record){
            String[] rList = r.split(" ");
            String command = rList[0];
            String uid = rList[1];
            
            if(command.equals("Enter")){
                String result = String.format("%s님이 들어왔습니다.", nickNameMap.get(uid));
                results.add(result);
            }else if (command.equals("Leave")){
                String result = String.format("%s님이 나갔습니다.", nickNameMap.get(uid));
                results.add(result);
            }  
            
            
        }
        
        String[] answer = new String[results.size()];
        
        for(int i = 0 ; i < results.size() ; i ++){
            answer[i] = results.get(i);
        }
        
        return answer;
    }
}