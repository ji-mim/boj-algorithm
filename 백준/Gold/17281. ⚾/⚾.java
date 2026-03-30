import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer> hitOrder = new ArrayList<>();
	static ArrayDeque<Integer> que = new ArrayDeque<>();
	static int[][] results;
	static int N;
	static int maxScore;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxScore = 0;
		results = new int[N + 1][10];
		
		for (int i = 1 ; i <= N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1 ; j <= 9 ; j ++) {
				results[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//타순 정하기 
		selectOrder(1, new boolean[10]);
		
		System.out.println(maxScore);
		
	}
	
	
	static void selectOrder(int idx, boolean[] visited){
		if(idx == 9) {
			que.clear();
			for (int i = 0 ; i < hitOrder.size() ; i ++) {
				if(i == 3) que.add(1);
				que.add(hitOrder.get(i));
			}
			
			maxScore = Math.max(maxScore, play());
			
//			System.out.println(Arrays.toString(que.toArray()));
			return;
		}
		
		for(int i = 2 ; i <= 9 ; i ++) {
			if(visited[i]) continue;
			visited[i] = true;
			hitOrder.add(i);
			selectOrder(idx + 1, visited);
			hitOrder.remove(hitOrder.size() - 1);
			visited[i] = false;
		}
	}
	
	static int play() {
		int inning = 1;
		int outCnt = 0 ;
		int fieldPlayerCnt = 0;
		int[] fieldPlayer = new int[4];
		int score = 0;
		
		while(inning <= N) {
			fieldPlayer[0] = 1;
			fieldPlayerCnt ++;
			int player = que.getFirst();
			//result 보고 결과 반영 쭉~
			int result = results[inning][player];
			que.offerLast(que.pollFirst());
			
			if(result == 0) { // 아웃인 경우 
				if(outCnt == 2) { // 이닝 끝나면 
					inning ++;
					outCnt = 0;
					fieldPlayerCnt = 0;
					Arrays.fill(fieldPlayer, 0);
					continue;
				}
				outCnt ++;
				fieldPlayerCnt --;
				continue;
			}
			
			if(result == 4) {
				score += fieldPlayerCnt;
				Arrays.fill(fieldPlayer, 0);
				fieldPlayerCnt = 0;
				continue;
			}
			
			for(int i = 3 ; i >= 0 ; i --) {
				if(fieldPlayer[i] != 0) {
					if (i + result >= 4) { // 득점 
						score ++;
						fieldPlayer[i] = 0;
						fieldPlayerCnt --;
					}else { // 진루 
						fieldPlayer[i + result] = 1;
						fieldPlayer[i] = 0;
					}
				}
			}
		}
		
		return score;
	}
	
}
