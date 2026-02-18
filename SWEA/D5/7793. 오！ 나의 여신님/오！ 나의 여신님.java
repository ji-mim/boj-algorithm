import java.io.*;
import java.util.*;

public class Solution {

    static class Cell{
        int x, y;

        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int T, N, M;
    static int[][] map;
    static int[][] dist;
    static boolean[][] playerVisited;
    static boolean[][] devilVisited;
    static ArrayDeque<Cell> playerQue = new ArrayDeque<>();
    static ArrayDeque<Cell> devilQue = new ArrayDeque<>();
    static String GOD = "D"; // 2
    static int gx, gy;
    static String DEVIL = "*"; // -1
    static String STONE = "X"; // -2
    static String PLAYER = "S"; // 1
    static String FIELD = "."; // 0
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc < T + 1 ; tc ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            dist = new int[N][M];
            playerVisited = new boolean[N][M];
            devilVisited = new boolean[N][M];

            playerQue.clear();
            devilQue.clear();

            for (int i = 0 ; i < N ; i ++){
                String[] input = br.readLine().split("");
                for (int j = 0 ; j < M ; j ++){
                    String val = input[j];
                    if(val.equals(GOD)) {
                        map[i][j] = 2;
                        gx = i;
                        gy = j;
                    }
                    else if (val.equals(DEVIL)) {
                        map[i][j] = -1;
                        pushDevil(i,j);
                    }
                    else if (val.equals(STONE)) map[i][j] = -2;
                    else if (val.equals(PLAYER)){
                        map[i][j] = 1;  
                        pushPlayer(i,j,0);
                    } 
                    else if (val.equals(FIELD)) map[i][j] = 0;
                }
            }

            bfs();

            // for (int[] arr: map){
            //     System.out.println(Arrays.toString(arr));
            // }

            

            if(dist[gx][gy] == 0){
                System.out.println("#" + tc + " " + "GAME OVER");
            } else{
                System.out.println("#" + tc + " " + dist[gx][gy]);
            }

        }

    }

    public static void bfs(){

        while(!devilQue.isEmpty() || !playerQue.isEmpty()){
            //playerDFS
            if(!playerQue.isEmpty()){
                int playerSize = playerQue.size();
                for(int s=0; s<playerSize; s++){
                    Cell curr = playerQue.poll();
                    int x = curr.x;
                    int y = curr.y;

                    if(map[x][y] == -1) continue;

                    for (int i = 0 ; i < 4 ; i ++){
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if(!inRange(nx,ny)) continue;

                        if(!playerVisited[nx][ny] && map[nx][ny] != -1 && map[nx][ny] != -2){
                            // map[x][y] = 0;
                            // map[nx][ny] = 1;
                            pushPlayer(nx, ny, dist[x][y] + 1);
                        }

                        // if(map[nx][ny] == 2) return;
                    }
                }
            }
            if(!devilQue.isEmpty()){    
                //devilDFS
                int devilSize = devilQue.size();
                for(int s=0; s<devilSize; s++){
                        Cell curr = devilQue.poll();
                        int x = curr.x;
                        int y = curr.y;

                        for (int i = 0 ; i < 4 ; i ++){
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if(!inRange(nx,ny)) continue;

                            // if(!devilVisited[nx][ny] && map[nx][ny] == 1) return;

                            if(!devilVisited[nx][ny] && map[nx][ny] != -2 && map[nx][ny] != 2){
                                map[nx][ny] = -1;
                                pushDevil(nx, ny);
                            }
                        }
                    }
                }

        }


    }

    public static boolean inRange(int x, int y){
        return 0<=x && x < N && 0<= y && y < M;
    }

    public static void pushDevil(int x, int y){
        devilVisited[x][y] = true;
        devilQue.offer(new Cell(x,y));
    }

    public static void pushPlayer(int x, int y, int s){
        playerVisited[x][y] = true;
        dist[x][y] = s;
        playerQue.offer(new Cell(x,y));
    }

    
}
