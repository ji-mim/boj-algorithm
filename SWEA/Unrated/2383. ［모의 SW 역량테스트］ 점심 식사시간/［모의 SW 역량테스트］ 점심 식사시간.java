import java.io.*;
import java.util.*;

public class Solution {
	
	static class Pos{
		int x, y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, ans;
	static int time, remainCnt;
	static int[][] map; 
	static List<Pos> pos; // 인간들 위치 
	static List<int[]> stairs = new ArrayList<>(); // 계단 정보 [x, y, len]
	static int[] selected; //선택된 계단 
	static int[] dist;
	static Deque<int[]> stair1 = new ArrayDeque<int[]>();
	static Deque<int[]> waitStair1 = new ArrayDeque<int[]>();
	static Deque<int[]> stair2 = new ArrayDeque<int[]>();
	static Deque<int[]> waitStair2 = new ArrayDeque<int[]>();
	
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1 ; tc <= T ; tc ++) {
			ans = Integer.MAX_VALUE;
			stairs.clear();
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			pos = new ArrayList<>();
			for (int i = 0 ; i < N ; i ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) pos.add(new Pos(i, j));
					if(map[i][j] > 1) stairs.add(new int[] {i, j, map[i][j]});
				}
			}
			M = pos.size();
			selected = new int[M];
			dist = new int[M];
			stair1.clear();
			stair2.clear();
			waitStair1.clear();
			waitStair2.clear();
			
			//뽑기 
			selectStair(0);
			
			System.out.println("#" + tc + " " + ans);
			
			
			
		}
	}
	
	static void selectStair(int idx) {
		if(idx == M) {
			time = 0;
			remainCnt = M;
			
			fillDist();
			
			while(remainCnt != 0) {
				time ++;
				
				//que 먼저 빼주기 
				for(int[] q : stair1) {
					q[1] -- ;
				}
				
				for(int[] q : stair2) {
					q[1] -- ;
				}

				while(true) {
					if(!stair1.isEmpty()) {
						if(stair1.peek()[1] == 0) {
							stair1.pop();
							remainCnt -- ;
						}else break;
					}else break;
				}
				while(true) {
					if(!stair2.isEmpty()) {
						if(stair2.peek()[1] == 0) {
							stair2.pop();
							remainCnt --;
						}else break;
					}else break;
				}
				
				// wait que 넣어주기 
				if(stair1.size() < 3) {
					while(stair1.size() < 3 && !waitStair1.isEmpty()) {
						stair1.offer(waitStair1.poll());
					}
				}
				
				if(stair2.size() < 3) {
					while(stair2.size() < 3 && !waitStair2.isEmpty()) {
						stair2.offer(waitStair2.poll());
					}
				}

				
				for(int i = 0 ; i < M ; i ++) {
					//-1 아니면 체크 0  되면 que에 넣기 
					if(dist[i] == 0) continue;
					dist[i] -- ;
					if(dist[i] == 0) {
						if(selected[i] == 0) {
							if(stair1.size() < 3) {
								stair1.add(new int[] {i, stairs.get(selected[i])[2]});
							}else {
								waitStair1.add(new int[] {i, stairs.get(selected[i])[2]});
								
							}
						}
						else if(selected[i] == 1) {
							if(stair2.size() < 3) {
								stair2.add(new int[] {i, stairs.get(selected[i])[2]});
							}else {
								waitStair2.add(new int[] {i, stairs.get(selected[i])[2]});
							}
						}
					}
				}
			}
			
			ans = Math.min(ans, time);
			
			return;
		}
		
		for(int i = 0 ; i < 2 ; i ++) {
			selected[idx] = i;
			selectStair(idx + 1);
		}
	}
	
	static void fillDist() {
		for (int i = 0 ; i < M ; i ++) {
			int[] selectStair = stairs.get(selected[i]);
			dist[i] = Math.abs(selectStair[0] - pos.get(i).x) + Math.abs(selectStair[1] - pos.get(i).y) + 1;
		}
	}

}
