import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, K;
	static int sx,sy,fx,fy;
	static char[][] map ;
	static int[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0 ; i < N ; i ++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0 ; j < M ; j ++) {
				map[i][j] = input[j];
				if(map[i][j] == '0') {
					sx = i;
					sy = j;
				    map[i][j] = '.';   // 시작 칸을 빈칸으로 바꿈 (추천)
				}
				
				if(map[i][j] == '1') {
					fx = i;
					fy = j;
				}
			}
		}
		
		int ans = bfs();
		
		System.out.println(ans);
		
	}
	
	
	static int bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		visited = new int[N][M][1<<6]; // x | y | 열쇠 | 문 
		que.offer(new int[]{sx,sy,0});
		visited[sx][sy][0] = 1;
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			int bitK = cur[2];
			
			if(map[x][y] == '1') return visited[x][y][bitK] - 1;
			
			for (int i = 0 ; i < 4 ; i ++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nbitK = bitK;
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(map[nx][ny] == '#') continue;
				
				
				if(getKey(map[nx][ny])) { //이동한 좌표에 열쇠가 있는 경우 
					nbitK |= 1 << (map[nx][ny] - 'a');
				} else if(atDoor(map[nx][ny])) { // 이동한 좌표에 문이 있는 경우 
					if((nbitK & (1 << (map[nx][ny] - 'A'))) == 0) continue;
				}
				
				if(visited[nx][ny][nbitK] > 0) continue;
				visited[nx][ny][nbitK] = visited[x][y][bitK] + 1; 
				que.offer(new int[] {nx,ny,nbitK});
			}
		}
		
		
		return -1;
	}
	
	static boolean atDoor(char c) {
		if(c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F') return true;
		return false;
	}

	
	static boolean getKey(char c) {
		if(c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f') return true;
		return false;
	}

}
