
import java.io.*;
import java.util.*;

public class Solution {

    static int T, N, M ;
    static int ans ;
    static int [][] graph;
    static boolean [] visited;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc <= T ; tc ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = 0 ;

            graph = new int[N+1][N+1];
            visited = new boolean[N + 1];

            for (int i = 0 ; i < M ; i ++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = 1;
                graph[to][from] = 1;
            }

            for (int i = 1 ; i <= N ; i ++){
                if(!visited[i]){
                    dfs(i);
                    ans ++;
                }
            }

            System.out.println("#" + tc + " " + ans);
        }

    }
    
    public static void dfs(int curr){
        visited[curr] = true;

        for (int i = 1 ; i <= N ; i ++){
            if(!visited[i] && graph[curr][i] == 1){
                dfs(i);
            }
        }
    }


}
