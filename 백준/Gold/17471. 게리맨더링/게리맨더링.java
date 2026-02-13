
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, ans; 
	static int[] populations;
	static int[][] graph;
	static boolean[] picked;
	static boolean[] visited;
	static int trueArea;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		
		populations = new int[N];
		graph = new int[N][N];
		picked = new boolean[N];
		visited = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0 ; i < N ; i ++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int vertex = i;
			int linkedVertexNum = Integer.parseInt(st.nextToken());
			
			for (int j = 0 ; j < linkedVertexNum ; j ++) {
				int linkedVertex = Integer.parseInt(st.nextToken()) - 1;
				graph[vertex][linkedVertex] = 1;
				graph[linkedVertex][vertex] = 1;
			}
		}
		
		picked[0] = true;
		trueArea = 1;
		
		seperateArea(1, 1);
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void seperateArea(int cnt, int idx) {
		if(idx == N) {
			if(trueArea == N) return;
			if(!isPossibleToSeperate()) return;
			ans = Math.min(ans, calDiff());
			return;
		}
		
		picked[idx] = true;
		trueArea ++;
		seperateArea(cnt + 1, idx + 1);
		picked[idx] = false;
		trueArea --;
		seperateArea(cnt, idx + 1);
		
	}
	
	public static int calDiff() {
		int trueSum = 0;
		int falseSum = 0;
		
		for (int i = 0 ; i < N ; i ++) {
			if(picked[i]) trueSum += populations[i];
			else falseSum += populations[i];
		}
		
		return Math.abs(trueSum - falseSum);
	}
	
	public static boolean isPossibleToSeperate() { 
		// 연결 정점 중에 우리 구역이 포함되어 있는지 파악해보자 
		// 연결된 모든 정점을 돌았을 때 우리 당의 모든 정점을 돌았는지 체크해보자  
		int startT = -1;
		int startF = -1;
		for (int i = 0 ; i < N ; i ++) {
	        if (picked[i] && startT == -1) startT = i;
	        if (!picked[i] && startF == -1) startF = i;
		}
		
		
		
	    initVisited();
	    dfsParty(startT, true);
	    for (int i = 0; i < N; i++) {
	        if (picked[i] && !visited[i]) return false;
	    }

	    initVisited();
	    dfsParty(startF, false);
	    for (int i = 0; i < N; i++) {
	        if (!picked[i] && !visited[i]) return false;
	    }

		
		
		return true;
	}
	
	static void dfsParty(int from, boolean party) {
		if(visited[from]) return;
		visited[from] = true;
		
		for (int to = 0 ; to < N ; to ++) {
			if(graph[from][to] == 1 && picked[from] == picked[to]) {
				dfsParty(to, party);
			}
		}
	}
	static void initVisited() {
		for (int i = 0 ; i < N ; i ++) {
			visited[i] = false;
		}
	}
}
