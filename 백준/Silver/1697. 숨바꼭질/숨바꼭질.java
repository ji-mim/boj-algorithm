import java.util.*;
import java.io.*;

public class Main {
	public static int N, M;
	public static final int MAX_LEN = 100_001;
	public static int[] step = new int[MAX_LEN];
	public static boolean[] visited = new boolean[MAX_LEN];
	public static ArrayDeque<Integer> que = new ArrayDeque<>();
	public static int[] dx = {-1,1};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		push(N, 0);
		
		bfs();
		
		System.out.println(step[M]);
		
		
	}
	
	public static void bfs() {
		
		while(!que.isEmpty()) {
			int curr = que.poll();
			
			for (int i = 0 ; i < 2 ; i ++) {
				int next = curr + dx[i];
				if (canGo(next)) {
					push(next, step[curr] + 1);
				}
			}
			
			int next = curr * 2;
			if (canGo(next)) {
				push(next, step[curr] + 1);
			}
		}
	}
	
	public static boolean canGo(int num) {
		return (0<= num && num < MAX_LEN) && !visited[num];
	}
	
	
	
	public static void push(int num, int s) {
		visited[num] = true;
		que.add(num);
		step[num] = s;
	}

}
