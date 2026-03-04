import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, X;
	static int[][] map;
	static boolean[] isInstalled;
	static int ans;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			isInstalled = new boolean[N];
			ans = 0;
			
			for (int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0 ; j < N ; j ++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0 ; i < N ; i ++) {
				// row 검사 
				if(canInstallRow(i)) {
					ans ++ ;
				}
				
				// col 검사 
				if (canInstallCol(i)) {
					ans ++;
				}
				
			}
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
	
	static boolean canInstallRow(int x) {
		initInstalled();
		
		for (int i = 0 ; i < N - 1 ; i ++) {
			int diff = map[x][i] - map[x][i + 1];
			if (diff == 0) continue;
			if (Math.abs(diff) > 1) return false; //높이 차가 1보다 크면 불가능
			if (diff == 1) { // 내리막길 
				int h = map[x][i + 1];
				for (int j = 0 ; j < X ; j ++) {
					int idx = i + 1 + j;
					// 배열 벗어나는지 체크 
					if(idx < 0 || idx >= N) return false;
					// 이미 설치된 곳인지 체크 
					if(isInstalled[idx]) return false;
					// 중간에 높이가 동일한지 체크 
					if(map[x][idx] != h) return false;
					// 경사로 설치
					isInstalled[i + 1 + j] = true;
				}
			}else if (diff == -1) { // 오르막길 
				int h = map[x][i];
				for (int j = 0 ; j < X ; j ++) {
					int idx = i - j ;
					// 배열 벗어나는지 체크 
					if(idx < 0 || idx >= N) return false;
					// 이미 설치된 곳인지 체크 
					if(isInstalled[idx]) return false;
					// 중간에 높이가 동일한지 체크 
					if(map[x][idx] != h) return false;
					// 경사로 설치
					isInstalled[i - j] = true;
				}
			}
		}
		
//		System.out.println("x = " + x + Arrays.toString(isInstalled));
		
		return true;
	}
	
	static boolean canInstallCol(int y) {
		initInstalled();
		
		for (int i = 0 ; i < N - 1 ; i ++) {
			int diff = map[i][y] - map[i + 1][y];
			if (diff == 0) continue;
			if (Math.abs(diff) > 1) return false; //높이 차가 1보다 크면 불가능
			if (diff == 1) { // 내리막길 
				int h = map[i + 1][y];
				for (int j = 0 ; j < X ; j ++) {
					int idx = i + 1 + j;
					// 배열 벗어나는지 체크 
					if(idx < 0 || idx >= N) return false;
					// 이미 설치된 곳인지 체크 
					if(isInstalled[idx]) return false;
					// 중간에 높이가 동일한지 체크 
					if(map[idx][y] != h) return false;
					// 경사로 설치
					isInstalled[i + 1 + j] = true;
				}
			}else if (diff == -1) { // 오르막길 
				int h = map[i][y];
				for (int j = 0 ; j < X ; j ++) {
					int idx = i - j ;
					// 배열 벗어나는지 체크 
					if(idx < 0 || idx >= N) return false;
					// 이미 설치된 곳인지 체크 
					if(isInstalled[idx]) return false;
					// 중간에 높이가 동일한지 체크 
					if(map[idx][y] != h) return false;
					// 경사로 설치
					isInstalled[i - j] = true;
				}
			}
		}
		
//		System.out.println("y = " + y + Arrays.toString(isInstalled));
		
		return true;
	}
	
	static void initInstalled() {
		for (int i = 0 ; i < N ; i ++) {
			isInstalled[i] = false;
		}
	}

}
