import java.util.*;

class Solution {
    
    int n, m;
    int[] dx = {-1,1,0,0}; // 상 하 좌 우
    int[] dy = {0,0,-1,1};
    int [][][] visited;
    char[][] map;
    int ans = Integer.MAX_VALUE;
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].split("").length;
        
        int sx = 0;
        int sy = 0;
        
        map = new char[n][m];
        visited = new int[n][m][4];
        
        for (int [][] vi : visited){
            for (int [] v : vi){
                Arrays.fill(v, Integer.MAX_VALUE);
            }
        }
        
        for(int i = 0 ; i < n ; i ++){
            char[] input = board[i].toCharArray();
            for (int j = 0 ; j < m ; j ++){
                map[i][j] = input[j];
                if(map[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        
        for (int d = 0 ; d < 4 ; d ++){
            // visited = new boolean[n][m][4];
            simulate(sx, sy, d, 0);
        }
        
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
        int answer = 0;
        return ans;
    }
    
    void simulate(int x, int y, int dir, int time){
        // System.out.println(x + " " + y + " " + dir + " " + time);

        if(visited[x][y][dir] <= time) return;
        visited[x][y][dir] = time;
        
        if(time > ans) return;

        if(map[x][y] == 'G'){
            ans = Math.min(ans, time);
            // System.out.println("goal = " + x + " " + y + " " + dir + " " + time);

            return;
        }
        int nx = x;
        int ny = y;
        
        //이동 
        while(true){
            nx = x + dx[dir];
            ny = y + dy[dir];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) break;
            if(map[nx][ny] == 'D') break;
            x = nx;
            y = ny;
        }
        
        
        
        //다음 방향으로 이동 
        for (int d = 0 ; d < 4 ; d ++){
            if(d == dir) continue;
            simulate(x, y, d, time + 1);
        }
    }
}