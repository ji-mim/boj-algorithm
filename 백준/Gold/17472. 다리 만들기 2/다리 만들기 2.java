import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_000;

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    static int[] parents;
    static int landNum;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static ArrayList<Edge> edges = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
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
        input();

        labelIslands();
        initDist();
        findBridges();
        buildEdges();

        int result = kruskal();
        System.out.println(result);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void labelIslands() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    landNum++;
                    visited[i][j] = true;
                    map[i][j] = landNum;
                    dfs(i, j);
                }
            }
        }
    }

    static void dfs(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!inRange(nx, ny)) continue;
            if (visited[nx][ny] || map[nx][ny] == 0) continue;

            visited[nx][ny] = true;
            map[nx][ny] = landNum;
            dfs(nx, ny);
        }
    }

    static void initDist() {
        dist = new int[landNum + 1][landNum + 1];
        for (int i = 0; i <= landNum; i++) {
            Arrays.fill(dist[i], INF);
        }
    }

    static void findBridges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                int start = map[i][j];

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    int len = 0;

                    // 바로 옆이 바다가 아니면 다리 시작 불가
                    if (!inRange(nx, ny) || map[nx][ny] != 0) continue;

                    // 바다를 직진하면서 길이 측정
                    while (inRange(nx, ny) && map[nx][ny] == 0) {
                        len++;
                        nx += dx[d];
                        ny += dy[d];
                    }

                    // 범위 밖으로 나갔으면 종료
                    if (!inRange(nx, ny)) continue;

                    // 같은 섬이면 불가
                    if (map[nx][ny] == start) continue;

                    // 다리 길이가 2 이상이어야 함
                    if (len < 2) continue;

                    int end = map[nx][ny];
                    dist[start][end] = Math.min(dist[start][end], len);
                    dist[end][start] = Math.min(dist[end][start], len);
                }
            }
        }
    }

    static void buildEdges() {
        for (int i = 1; i <= landNum; i++) {
            for (int j = i + 1; j <= landNum; j++) {
                if (dist[i][j] == INF) continue;
                edges.add(new Edge(i, j, dist[i][j]));
            }
        }
        Collections.sort(edges);
    }

    static int kruskal() {
        parents = new int[landNum + 1];
        Arrays.fill(parents, -1);

        int result = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            if (union(edge.x, edge.y)) {
                result += edge.cost;
                cnt++;

                if (cnt == landNum - 1) break;
            }
        }

        if (cnt != landNum - 1) return -1;
        return result;
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB) return false;

        if (parents[rootA] < parents[rootB]) {
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
        } else {
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
        }

        return true;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}