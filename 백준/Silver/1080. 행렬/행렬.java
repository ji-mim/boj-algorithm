import java.io.*;
import java.util.*;


public class Main {
	
	static int N, M;
	static int[][] A,B;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		B = new int[N][M];
			
		
		
		for (int i = 0 ; i < N ; i ++) {
			String[] input = br.readLine().split("");
			for (int j = 0 ; j < M ; j ++) {
				A[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		for (int i = 0 ; i < N ; i ++) {
			String[] input = br.readLine().split("");
			for (int j = 0 ; j < M ; j ++) {
				B[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		
		if (N < 3 || M < 3)  {
			if(isSame()) System.out.println(0);
			else System.out.println(-1);
			return;
		}

		
		int cnt = 0;
		
		for (int i = 0 ; i < N - 3 + 1 ; i ++) {
			for (int j = 0 ; j < M - 3 + 1 ; j ++) {
				if(diff(i,j)) {
					change(i,j);
					cnt ++;
				}
			}
		}
		
		if(!isSame()) {
			System.out.println(-1);
		}else {
			System.out.println(cnt);
		}
		
	}
	
	static boolean diff(int x, int y) {
		if(A[x][y] != B[x][y]) return true;
		
		return false;
	}
	
	static boolean isSame() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j ++) {
				if(A[i][j] != B[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void change(int x, int y) {
		for (int i = x ; i < x + 3 ; i ++) {
			for (int j = y ; j < y + 3 ; j ++) {
				A[i][j] = (A[i][j] + 1) % 2;
			}
		}
	}

}
