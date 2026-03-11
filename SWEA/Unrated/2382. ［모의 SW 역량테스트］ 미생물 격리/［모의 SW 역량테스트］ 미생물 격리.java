import java.io.*;
import java.util.*;

public class Solution {
	
	static class Micro{
		int r, c, cnt, dir, total;

		public Micro(int r, int c, int cnt, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.total = this.cnt = cnt;
			this.dir = dir;
		}
	}
	
	static int N, M, K;
	static int[] dr = {0,-1,1,0,0}; //상:1, 하:1, 좌:3, 우:4
	static int[] dc = {0,0,0,-1,1}; 
	static Micro[] list ; // 미생물 군집 리스트 
	static Micro[][] map; // 미생물의 이동을 기록(병합 관리를 위해)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1 ; tc <= TC ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			list = new Micro[K];
			map = new Micro[N][N];
			
			for (int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				list[i] = new Micro(
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken())
						);  
			}
			
			System.out.println("#" + tc + " " + solve());
			
		}
	}
	
	private static int solve() { // M시간 격리 후 살아 있는 미생물 수의 총합 리턴 
		int time = M, nr, nc, remainCnt = 0;
		
		// M 시간 동안 처리 
		while(time -- > 0) {
			for(Micro cur : list) {
				if(cur.cnt == 0) continue; // 소멸된 군집이면 스킵
				// 군집 다음 좌표로 좌표 update,
				nr = cur.r += dr[cur.dir];
				nc = cur.c += dc[cur.dir];
				// 가장자리 셀인 경우는 크기 1/2, 방향 반대로
				if(nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
					cur.total = cur.cnt /= 2;
					if(cur.cnt == 0) continue;
					if(cur.dir % 2 == 1) cur.dir++;
					else cur.dir--;
				}
				
				// 업데이트된 좌표로 이동하며 병합
				if(map[nr][nc] == null) { // 이동하는 위치에 자신이 처음 도착하는 것
					map[nr][nc] = cur;
				}else { // 이동하는 위치에 먼저 도착한 군집이 있다면 군집의 크기를 비교해서 병합 
					if(map[nr][nc].cnt > cur.cnt) {
						map[nr][nc].total += cur.cnt;
						cur.cnt = 0; // 현재 군집은 흡수되었으므로 미생물 수는 0으로 (소멸 상태로)
					}else { //먼저 도착한 군집보다 현재 군집이 더 크다면 자신이 흡수함 
						cur.total += map[nr][nc].total;
						map[nr][nc].cnt = 0; // 군집소멸
						map[nr][nc] = cur;
					}
				}
			}
			remainCnt = reset();
		}
		return remainCnt;
	}
	
	private static int reset() {
		int result = 0;
		for(Micro cur : list) {
			if(cur.cnt == 0) continue;
			
			if(map[cur.r][cur.c] == cur) {
				map[cur.r][cur.c] = null;
				cur.cnt = cur.total;
			}
			result += cur.cnt;
		}
		return result;
	}
	
}
