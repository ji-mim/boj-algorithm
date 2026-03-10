import java.io.*;
import java.util.*;

public class Solution {
	
	public static class Micro{
		int x, y, sum, maxCnt, dir;

		public Micro(int x, int y, int sum, int maxCnt, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.sum = sum;
			this.maxCnt = maxCnt;
			this.dir = dir;
		}
	}
	
	static int T, N, M, K;
	static ArrayList<Micro> microList = new ArrayList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			microList.clear();
			
			for (int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				microList.add(new Micro(x,y,cnt, cnt, dir));
			}
			
			for (int i = 0 ; i < M ; i ++) {
				HashMap<Integer, Micro> nextMap = new HashMap<>();
				
				for (Micro m : microList) {
					int nx = m.x + dx[m.dir];
					int ny = m.y + dy[m.dir];
					int nSum = m.sum;
					int nd = m.dir;
					
					if(nx == N - 1 || nx == 0 || ny == N - 1 || ny == 0) {
						nd = changeDir(nd);
						nSum /= 2;
					}
					
					if(nSum == 0) continue;
					
					int key = nx * N + ny;
					
					if(!nextMap.containsKey(key)) {
						nextMap.put(key, new Micro(nx,ny,nSum, nSum , nd));
					}else {
						Micro exist = nextMap.get(key);
						if(exist.maxCnt < nSum) {
							exist.maxCnt = nSum;
							exist.dir = nd;
						}
						exist.sum += nSum;
					}
				}
				
				ArrayList<Micro> nextList = new ArrayList<>();
				for(Micro m : nextMap.values()) {
					nextList.add(m);
				}
				
				microList = nextList;
			}
			
			int totalCnt = microList.stream().mapToInt(m -> m.sum).sum();
			
			System.out.println("#" + tc + " " + totalCnt);
		}
	}
	
	static int changeDir(int dir) {
		if(dir == 0) return 1;
		if(dir == 1) return 0;
		if(dir == 2) return 3;
		if(dir == 3) return 2;
		return -1;
	}
}
