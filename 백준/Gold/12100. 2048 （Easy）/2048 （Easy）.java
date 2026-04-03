import java.io.*;
import java.util.*;

public class Main {

	static int N ;
	static int[][] map ;
	static int[][] modifiedMap;
	static int[][] rotateMap;
	static int[] selected = new int[5]; 
	static int ans;
	static Deque<Integer> stack = new ArrayDeque<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(br.readLine());
		map = new int[N][N];
		modifiedMap = new int[N][N];
		rotateMap = new int[N][N];
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		
//		for (int i = 0 ; i < N ; i ++) {
//			for (int j = 0 ; j < N ; j ++) {
//				modifiedMap[i][j] = map[i][j];
//			}
//		}
//		
//		rotate(1);
//		
//		gravity();
//		
//		merge();
//		
//		for (int i = 0 ; i < N ; i ++) {
//			System.out.println(Arrays.toString(modifiedMap[i]));
//		}
//		
		permutation(0);
		
		System.out.println(ans);
		
	}
	
	static void permutation(int idx) {
		if(idx == 5) {
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					modifiedMap[i][j] = map[i][j];
				}
			}
			
			for (int i = 0 ; i < selected.length ; i ++) {
				int way = selected[i];
				//회전 
				rotate(way - 1);
				
				//중력 
				gravity();
				
				//합치기 
				merge();
				
				//중력 
				gravity();
			}
			
			//가장 큰 수 세기 
//			for (int i = 0 ; i < N ; i ++) {
//				System.out.println(Arrays.toString(modifiedMap[i]));
//			}
			
			
			ans = Math.max(ans, findMaxNum());
			
			return;
		}
		
		for (int i = 1 ; i < 5 ; i ++) {
			selected[idx] = i ; 
			permutation(idx + 1);
		}
	}
	
	static void rotate(int cnt) {
		while(cnt -- > 0) {
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					rotateMap[i][j] = modifiedMap[N - j - 1][i];
				}
			}
			
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					modifiedMap[i][j] = rotateMap[i][j];
				}
			}
		}
	}
	
	static void gravity() {
		for (int c = 0 ; c < N ; c ++) {
			stack.clear();
			for (int r = 0 ; r < N ; r ++) {
				if(modifiedMap[r][c] != 0) {
					stack.push(modifiedMap[r][c]);
					modifiedMap[r][c] = 0;
				}
			}
			
			int r = N - 1;
			while(!stack.isEmpty()) {
				modifiedMap[r -- ][c] = stack.pop();
			}
		}
	}
	
	static void merge() {
		for (int c = 0 ; c < N ; c ++) {
			for (int r = N - 2 ; r >= 0 ; r --) {
				if (modifiedMap[r][c] != 0 && modifiedMap[r][c] == modifiedMap[r + 1][c]) {
					modifiedMap[r + 1][c] *= 2;
					modifiedMap[r][c] = 0;
				}
			}
		}
	}
	
	static int findMaxNum() {
		int num = 0 ;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				num = Math.max(num, modifiedMap[i][j]);
			}
		}
		
		return num;
	}
}
