import java.io.*;
import java.util.*;

/**
 * 처음 든 생각, dfs로 최솟값만 선택하면서 진행하면 어떨까? -> 근데 최솟값 선택이 정답을 보장해주지 않음 
 * dfs를 그냥 돌기는 N의 크기가 최대 100이라 너무 큼
 * 가중치가 양수니까 다익스트라를 사용해보자는 생각을 하게 됨 
 */

public class Solution {

    static class Cell implements Comparable<Cell> {
        int x, y, d;

        public Cell(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Cell c){
            return Integer.compare(this.d, c.d);
        } 
    }

    static int[][] map, dist;
    static int T, N;
    static int INF = (int) 1e9;
    static PriorityQueue<Cell> pq = new PriorityQueue<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc <= T ; tc ++){
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            dist = new int[N][N];

            //배열 초기값 
            for (int i = 0 ; i < N ; i ++){
                String[] rowInput = br.readLine().split("");
                for (int j = 0 ; j < N ; j ++){
                    map[i][j] = Integer.parseInt(rowInput[j]);
                }
            }

            for (int [] d : dist){
                Arrays.fill(d, INF);
            }

            dist[0][0] = map[0][0];

            dijkstra();

            System.out.println("#" + tc + " " + dist[N-1][N-1]);



        }

    }

    public static void dijkstra(){
        pq.add(new Cell(0,0,map[0][0]));

        while(!pq.isEmpty()){
            Cell curr = pq.poll();
            int x = curr.x;
            int y = curr.y;

            if(curr.d != dist[x][y]) continue;

            for (int i = 0 ; i < 4 ; i ++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                int newDist = map[nx][ny] + curr.d;
                if(dist[nx][ny] > newDist){
                    dist[nx][ny] = newDist;
                    pq.add(new Cell(nx,ny,newDist));
                }

            }



        }

    }
    
}
