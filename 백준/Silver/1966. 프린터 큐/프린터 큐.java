import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while(T -- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int order = 0;
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Deque<int[]> que = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N ; i ++) {
				int num = Integer.parseInt(st.nextToken());
				if(i == M) {
					que.add(new int[]{num, -1});
				}else {
					que.add(new int[]{num, 0});
				}
			}
			
			while(true) {
				int top = que.peek()[0];
				
				boolean canPoll = true;
				
				for(int[] e : que) {
					if(e[0] > top) canPoll = false;
				}
				
				if(canPoll) {
					if(que.peek()[1] == -1) {
						System.out.println(order + 1);
						break;
					}
					
					que.poll();
					order ++;
				}else {
					que.offer(que.poll());
				}
				
//				for(int[] e : que) {
//					System.out.print(e[0] + " ");
//				}
//				System.out.println();
			}
		}
	}
}
