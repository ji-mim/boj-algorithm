import java.io.*;
import java.util.*;

public class Main {
	
	
	static class Block{
		int idx, a, h, w;

		public Block(int idx, int a, int h, int w) {
			this.idx = idx;
			this.a = a;
			this.h = h;
			this.w = w;
		}
	}
	static int N;
	static int ans;
	static Block[] blockArr;
	static int[] next;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		next = new int[N + 1];
		
		blockArr = new Block[N];
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			blockArr[i] = new Block(i + 1, a, h, w); 
		}
		
		Arrays.sort(blockArr, (a,b) -> Integer.compare(b.a, a.a));
		
		int maxHeight = 0;
		int start = -1;
		Arrays.fill(dp, -1);
		Arrays.fill(next, -1);
		
		
		for (int i = 0 ; i < N ; i ++) {
			int h = backtrack(i);
			if(h > maxHeight) {
				maxHeight = h;
				start = i;
			}
		}
		
        List<Integer> answer = new ArrayList<>();
        while (start != -1) {
            answer.add(blockArr[start].idx);
            start = next[start];
        }

        System.out.println(answer.size());
        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.println(answer.get(i));
        }

		
	}
	
	static int backtrack(int cur) {
		if (dp[cur] != -1) return dp[cur];
		
		dp[cur] = blockArr[cur].h;
		
		for(int nxt = cur + 1; nxt < N ; nxt ++) {
			if(blockArr[nxt].a < blockArr[cur].a && blockArr[nxt].w < blockArr[cur].w ) {
				int candidate = blockArr[cur].h + backtrack(nxt);
				if(candidate > dp[cur]) {
					dp[cur] = candidate;
					next[cur] = nxt;
				}
			}
		}
		
		return dp[cur];
		
	}

}
