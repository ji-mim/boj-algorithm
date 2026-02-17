import java.util.*;
import java.io.*;

/**
 * DFS로 풀어볼 수 있을 것 같음 , 컴퓨터의 수는 100 이하, 1에서 시작 
 * O(N^2) 예상
 */

public class Main {

    static int N, M ; // computer 개수, 연결 선 개수 
    static int MAX_NUM = 101;
    static int [][] graph = new int[MAX_NUM][MAX_NUM];
    static boolean [] visited = new boolean[MAX_NUM];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < M ; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        dfs(1);

        int ans = 0 ;

        for (int i = 0 ; i <= N ; i ++){
            if (visited[i]) ans ++;
        }

        System.out.println(ans - 1);

    }

    public static void dfs(int visit){
        if(visited[visit]) return;

        visited[visit] = true;

        for (int i = 0 ; i <= N ; i ++){
            if(graph[visit][i] == 1){
                dfs(i);
            }
        }
    }
}
