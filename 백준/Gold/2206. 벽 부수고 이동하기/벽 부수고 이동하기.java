import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	
	private static boolean[][][] visited;
	private static int answer, ROW, COL;
	private static int[] dr = {1, -1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static char[][] map;
	
	static class Node {
		int r, c, dist, broken;
		Node(int r, int c, int dist, int broken) {
			this.r = r; this.c = c; this.dist = dist; this.broken = broken;
		}
	}
	
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] arr = br.readLine().split(" ");
		
		ROW = Integer.parseInt(arr[0]);
		COL = Integer.parseInt(arr[1]);
		map = new char[ROW][COL];
		for(int i = 0; i < ROW; i++) {
			char[] line = br.readLine().toCharArray();
			map[i] = line.clone();
		}
		
		answer = Integer.MAX_VALUE;
		visited = new boolean[ROW][COL][2];
		System.out.println(bfs());
	}


	private static int bfs() {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if(cur.r == ROW - 1 && cur.c == COL - 1) {
				return cur.dist;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nr >= ROW || nc < 0 || nc >= COL) continue;
				
				if(map[nr][nc] == '0') {
					if(!visited[nr][nc][cur.broken]) {
						visited[nr][nc][cur.broken] = true;
						q.add(new Node(nr, nc, cur.dist + 1, cur.broken));
					}
				} else { // 벽
					if (cur.broken == 0 && ! visited[nr][nc][1]) {
						visited[nr][nc][1] = true;
						q.add(new Node(nr, nc, cur.dist + 1, 1));
					}
				}
			}
		}

		return -1;
	}
}
