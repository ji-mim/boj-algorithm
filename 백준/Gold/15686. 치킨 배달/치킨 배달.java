import java.util.*;
import java.io.*;

public class Main {
    // 조합으로 가능한 치킨집을 뽑아두고, 
    // 각각의 집에서 치킨 거리를 구한다? 
    // (최소 거리는 어떻게 구함? -> 1) bfs 2) 최소 거리를 미리 구해두기)

    public static int N, M;
    public static int ans = Integer.MAX_VALUE;
    public static int[][] arr; 
    public static ArrayList<int[]> homes = new ArrayList<>();
    public static ArrayList<int[]> chickens = new ArrayList<>();
    public static int[][] chickenRange = new int[100][15]; // [집 인덱스][치킨집 인덱스]

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static void main(String args[]) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j ++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) homes.add(new int[]{i,j});
                if(arr[i][j] == 2) chickens.add(new int[]{i,j});
            }
        }

        for (int i = 0 ; i < homes.size(); i ++){
            for(int j = 0 ; j < chickens.size(); j ++){
                chickenRange[i][j] = Math.abs(homes.get(i)[0] - chickens.get(j)[0]) + Math.abs(homes.get(i)[1] - chickens.get(j)[1]);
            }
        }

        selectChickenStore(0,0, new ArrayList<Integer>());

        System.out.println(ans);

        
    }

    public static void selectChickenStore(int start, int idx, ArrayList<Integer> selected){
        if(idx == M){
            int cickenRangeOfCity = calChickenRange(selected);
            
            ans = Math.min(ans, cickenRangeOfCity);
            return;
        }

        for (int i = start ; i < chickens.size() ; i ++){
            selected.add(i);
            selectChickenStore(i + 1, idx + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    public static int calChickenRange(ArrayList<Integer> selected){
        int len = 0;
        for (int i = 0 ; i < homes.size() ; i ++){
            int minLen = Integer.MAX_VALUE;
            for (int j : selected){
                minLen = Math.min(minLen, chickenRange[i][j]);
            }
            len += minLen;
            
        }

        return len;
    }
    
}
