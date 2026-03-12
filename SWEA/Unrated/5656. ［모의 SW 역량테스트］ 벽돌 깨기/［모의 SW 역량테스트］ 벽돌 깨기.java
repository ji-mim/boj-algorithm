import java.io.*;
import java.util.*;

public class Solution {

	static int N, W, H;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int blockCnt; 
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			blockCnt = 0;
			ans = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			for (int i = 0 ; i < H ; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < W ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) blockCnt ++;
				}
			}
			
			backtrack(0, map, blockCnt);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void backtrack(int idx, int[][] map, int remainCnt) {
		if(remainCnt == 0) {
		    ans = 0;
		    return;
		}
		
		if (idx == N) {
			// 남아 있는 벽돌의 개수 세기 
			ans = Math.min(ans, remainCnt);
			return;
		}
		
		int[][] bombedMap = new int[H][W];
		
		for (int bombCol = 0 ; bombCol < W ; bombCol ++) {
			int bombRow = -1 ; 
			for (int r = 0 ; r < H ; r ++) {
				if(map[r][bombCol] != 0) {
					bombRow = r;
					break;
				}
			}
			if(bombRow == -1) continue;
			
			for (int i = 0 ; i < H ; i ++) {
				for (int j = 0 ; j < W ; j ++) {
					bombedMap[i][j] = map[i][j];
				}
			}
			
			int nextRemain = remainCnt;
			
			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(new int[] {bombRow, bombCol, bombedMap[bombRow][bombCol]});
			bombedMap[bombRow][bombCol] = 0;
			nextRemain --;
			
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				int x = cur[0];
				int y = cur[1];
				int range = cur[2];
				
				for (int i = 0 ; i < 4 ; i ++) {
					for (int r = 1 ; r < range ; r ++) {
						int nx = x + dx[i] * r;
						int ny = y + dy[i] * r;
						if(nx < 0 || nx >= H || ny < 0 || ny >= W) break;
						if(bombedMap[nx][ny] != 0) {
							que.add(new int[] {nx, ny, bombedMap[nx][ny]});
							bombedMap[nx][ny] = 0;
							nextRemain --;
						}
					}
				}
			}
			
			//중력 
			for (int c = 0 ; c < W ; c ++) {
				int fillIdx = H - 1;
				for (int r = H - 1 ; r >= 0 ; r --) {
					if(bombedMap[r][c] != 0 ) { 
						if(fillIdx != r) {
							bombedMap[fillIdx --][c] = bombedMap[r][c];
							bombedMap[r][c] = 0;
						}else {
							fillIdx --;
						}
					}
				}
			}
			
			backtrack(idx + 1, bombedMap, nextRemain);
		}
	}

}
