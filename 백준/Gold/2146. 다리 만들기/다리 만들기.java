
import java.util.*;
import java.io.*;

public class Main {

    static class Cell{
        int x, y, area;

        public Cell(int x, int y, int area){
            this.x = x;
            this.y = y;
            this.area = area;
        }
    }

    static int map[][];
    static int N;
    static int ans = Integer.MAX_VALUE;
    static boolean visited[][];
    static boolean sideVisited[][];

    static int dist[][][];
    static ArrayDeque<Cell> que = new ArrayDeque<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int areaNum = 1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        sideVisited = new boolean[N][N];
        dist = new int[N][N][2];

        for (int i = 0 ; i < N ; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1. dfs로 돌면서 영역 visited 체크, 가장자리 부분 Que에 넣기 
        for (int i = 0 ; i < N ; i ++){
            for (int j = 0 ; j < N ; j ++){
                if (!visited[i][j] && map[i][j] == 1){
                    dist[i][j][1] = areaNum;
                    dfs(i,j);
                    areaNum ++;
                }
            }
        }
        
        // System.out.println("==============");
        
        // for (int i = 0 ; i < N ; i ++){
        //     for (int j = 0 ; j < N ; j ++){
        //         System.out.print(dist[i][j][1] + " ");
        //     }
        //     System.out.println();
        // }

        //2. bfs로 최소 거리 구하기
        // 이때 방문하지 않은 곳으로 갈 경우 방문하고 dist 최신화 해주기 
        // 만약 방문했던 곳인데 dist 상에서 area가 다를 경우 내 dist 값이랑, 그
        // dist 값 더해서 정답 최솟값 기준 최신화 해주기 

        bfs();


        // System.out.println("==============");
        
        // for (int i = 0 ; i < N ; i ++){
        //     for (int j = 0 ; j < N ; j ++){
        //         System.out.print(dist[i][j][1] + " ");
        //     }
        //     System.out.println();
        // }

        
        // System.out.println("==============");
        
        // for (int i = 0 ; i < N ; i ++){
        //     for (int j = 0 ; j < N ; j ++){
        //         System.out.print(dist[i][j][0] + " ");
        //     }
        //     System.out.println();
        // }


        System.out.println(ans);

    }

    public static void bfs(){
        while(!que.isEmpty()){
            Cell curr = que.poll();
            int x = curr.x;
            int y = curr.y;

            for (int i = 0 ; i < 4 ; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!inRange(nx,ny)) continue;

                if(visited[nx][ny] && dist[nx][ny][1] != dist[x][y][1]){
                    ans = Math.min(ans, dist[nx][ny][0] + dist[x][y][0]);
                    continue;
                }

                if(!visited[nx][ny] && map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    dist[nx][ny][0] = dist[x][y][0] + 1;
                    dist[nx][ny][1] = dist[x][y][1];
                    que.offer(new Cell(nx,ny,dist[x][y][1]));
                }
            }
        }
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        for (int i = 0 ; i < 4 ; i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(!inRange(nx,ny)) continue;

            if(map[nx][ny] == 0 && !sideVisited[x][y]){
                sideVisited[x][y] = true;
                que.offer(new Cell(x,y,areaNum));
            }

            if(!visited[nx][ny] && map[nx][ny] == 1){
                dist[nx][ny][1] = areaNum;
                dfs(nx,ny);
            }

        }
    }

    public static boolean inRange(int x, int y){
        return 0<=x && x < N && 0 <= y && y < N;
    }

    
}
