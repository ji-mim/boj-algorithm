import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[][] map;
    static int[] parents;
    static boolean[][] visited;
    static int[][] dist;
    static int landNum;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Edge> edges = new ArrayList<>();
    
    static class Edge implements Comparable<Edge>{
        int x, y, cost;
        
        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0 ; i < N ; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M ; j ++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0 ; i < N ; i ++) {
            for (int j = 0 ; j < M ; j ++) {
                if(!visited[i][j] && map[i][j] != 0) {
                    visited[i][j] = true;
                    landNum ++;
                    map[i][j] = landNum;
                    dfs(i,j);
                }
            }
        }
        
        dist = new int[landNum + 1][landNum + 1];
        for(int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        
        for (int i = 0 ; i < N ; i ++) {
            for (int j = 0 ; j < M ; j ++) {
                if (map[i][j] != 0) {
                    for (int d = 0 ; d < 4 ; d ++) {
                        int x = i;
                        int y = j;
                        int start = map[i][j];
                        int cnt = 0;
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                        if(map[nx][ny] == 0) {
                            cnt ++;
                            x = nx;
                            y = ny;
                            while(true) {
                                nx = x + dx[d];
                                ny = y + dy[d];
                                if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                                if(map[nx][ny] == start)break;
                                if(map[nx][ny] == 0) {
                                    cnt ++;
                                    x = nx;
                                    y = ny;
                                }else {
                                    if(cnt == 1) break;
                                    int end = map[nx][ny];
                                    dist[start][end] = Math.min(dist[start][end], cnt);
                                    break;
                                }
                                
                            }
                        }
                    }
                }
            }
        }
//        
    //    for(int[] d: map) {
    //        System.out.println(Arrays.toString(d));
    //    }

       
    //    for(int[] d: dist) {
    //        System.out.println(Arrays.toString(d));
    //    }
        
        for (int i = 1 ; i <= landNum ; i ++) {
            for (int j = i ; j <= landNum ; j ++) {
                if(i == j || dist[i][j] == Integer.MAX_VALUE) continue;
                // System.out.println("add = " + i + " " + j);
                if(dist[i][j] < 2) continue;
                edges.add(new Edge(i,j,dist[i][j]));
            }
        }

        // System.out.println("size" + edges.size());
        
        Collections.sort(edges);
        
        int result = 0;
        int cnt = 0;
        
        parents = new int[landNum + 1];
        
        Arrays.fill(parents, -1);
        
        for(Edge edge: edges) {
            int x = edge.x;
            int y = edge.y;
            int cost = edge.cost;
            // System.out.println(x + " " + y + " " + cost);
            
            if(union(x,y)) {
                result += cost;
                // System.out.println(cost);
                cnt ++ ;
                if (cnt == landNum - 1) break;
            }
        }

        if (cnt != landNum - 1) result = -1;
        
        System.out.println(result);
        // dfs 돌리면서 섬 개수 찾고 표시하기 
        // n*n 좌표 만들어둬서 최소 거리 기록 준비 
        // 배열 돌면서 끝 부분들 쭉 돌려서 최소 거리 찾기 
        // UF 
        
    }
    
    static void dfs(int x, int y) {
        for (int i = 0 ; i < 4 ; i ++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(!visited[nx][ny] && map[nx][ny] != 0) {
                visited[nx][ny] = true;
                map[nx][ny] = landNum;
                dfs(nx, ny);
            }
        }
    }
    
    static int findSet(int a) {
        if(parents[a] < 0 ) return a;
        return parents[a] = findSet(parents[a]);
    }
    
    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);
        
        if(rootA == rootB) return false;
        
        if(parents[rootA] < parents[rootB]) {
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        }else {
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }
        return true;
        
    }

}