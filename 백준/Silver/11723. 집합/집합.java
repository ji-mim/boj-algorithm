import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException{
        HashSet<Integer> set = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();



        while(N -- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if(command.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                if(!set.contains(num)) set.add(num);
            }else if(command.equals("check")){
                int num = Integer.parseInt(st.nextToken());
                if(set.contains(num)) sb.append("1").append("\n");
                else sb.append("0").append("\n");
            }else if(command.equals("remove")){
                int num = Integer.parseInt(st.nextToken());
                if(set.contains(num)) set.remove(num);
            }else if(command.equals("empty")) set.clear();
            else if(command.equals("toggle")){
                int num = Integer.parseInt(st.nextToken());
                if(set.contains(num)) set.remove(num);
                else set.add(num);
            }else if(command.equals("all")){
                set.clear();
                for(int i = 1 ; i <= 20 ; i ++) set.add(i);
            }
        }

        System.out.println(sb);
    }
    
}
