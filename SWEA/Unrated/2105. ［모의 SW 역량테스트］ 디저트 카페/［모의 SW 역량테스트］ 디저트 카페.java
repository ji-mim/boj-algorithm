import java.io.*;
import java.util.*;

public class Solution {

    static int[][] map;
    static int ans;
    static int N, T;
    static boolean[] visited = new boolean[110];
    static int[] dx = {1,1,-1,-1};  
    static int[] dy = {1,-1,-1,1};

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc <= T ; tc ++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ans = -1;
            for (int i = 0 ; i < N ; i ++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0 ; j < N ; j ++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0 ; i < N - 1 ; i ++){
                for (int j = 0 ; j < N ; j ++){
                    // findRoute(i,j,i,j,0,0,0);
                    visited[map[i][j]] = true;
                    dfs(i, j, i, j, 0, 1);
                    visited[map[i][j]] = false;
                }
            }
            System.out.println("#" + tc + " " + ans);
        }

    }

    public static void dfs(int sx, int sy, int x, int y, int dir, int cnt) {

        for(int d = dir; d <= dir+1 && d < 4; d++) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!inRange(nx, ny)) continue;

            // 마지막 방향에서 시작점으로 복귀하면 성공
            if(d == 3 && nx == sx && ny == sy && cnt >= 4) {
                ans = Math.max(ans, cnt);
                return;
            }

            if(!visited[map[nx][ny]]) {
                visited[map[nx][ny]] = true;
                dfs(sx, sy, nx, ny, d, cnt+1);
                visited[map[nx][ny]] = false;
            }
        }
    }

    public static void findRoute(int sx, int sy, int x, int y, int dir, int range1, int range2){
        if(!inRange(x,y)) return;
        if(visited[map[x][y]]) return;

        visited[map[x][y]] = true;
        int nx = x;
        int ny = y;

        if (dir == 0){
            while(ny < N && nx < N){
                nx = x + dx[dir];
                ny = y + dy[dir];
                findRoute(sx,sy,nx,ny,dir+1,range1+1,range2);
                visited[map[sx][sy]] = false;
                x = nx;
                y = ny;
            }
        }
        else if (dir == 1){
            while(nx < N && ny < N){
                nx = x + dx[dir];
                ny = y + dy[dir];
                findRoute(sx,sy,nx,ny,dir+1,range1,range2 + 1);
                visited[map[sx][sy]] = false;

                x = nx;
                y = ny;
            }
        }
        else if (dir == 2){
            for (int i = 0 ; i < range1 - 1; i ++){
                nx = x + dx[dir];
                ny = y + dy[dir];
                if(!inRange(nx,ny)) return;
                if(visited[map[nx][ny]]) return;
                visited[map[nx][ny]] = true;
                x = nx;
                y = ny;
            }
            findRoute(sx,sy,x + dx[dir], y + dy[dir], dir + 1, range1, range2);
            visited[map[sx][sy]] = false;

        }
        else if(dir == 3){
            for (int i = 0 ; i < range2 - 1; i ++){
                nx = x + dx[dir];
                ny = y + dy[dir];
                if(!inRange(nx,ny)) return;
                if(visited[map[nx][ny]]) return;
                visited[map[nx][ny]] = true;
                x = nx;
                y = ny;
            }

            ans = Math.max(ans, calType());
            return;
        }

    }

    public static boolean inRange(int x, int y){
        return 0<=x && x < N && 0<=y && y < N;
    }

    public static int calType(){
        int cnt = 0 ;

        for (boolean v : visited){
            if(v) cnt ++;
        }

        return cnt;
    }
    
}
