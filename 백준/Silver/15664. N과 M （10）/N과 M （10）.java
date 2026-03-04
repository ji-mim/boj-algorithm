import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static StringBuilder sb = new StringBuilder();
	public static int[] numbers, inputs;
	public static boolean[] isvisited;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		StringTokenizer st = new StringTokenizer(br.readLine());
		inputs = new int[N];
		for(int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		
		numbers = new int[M];
		isvisited = new boolean[N+1];
		Arrays.sort(inputs);
		permute(0, 0);
		
		System.out.println(sb);
	}

	private static void permute(int depth, int start) {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		int prev = Integer.MIN_VALUE;
		for(int i = start; i < N; i++) {
			if(isvisited[i]) continue;
			if(inputs[i] == prev) continue;
			prev = inputs[i];
			numbers[depth] = inputs[i];
			isvisited[i] = true;
			permute(depth + 1, i + 1);
			isvisited[i] = false;

		}
	}

}
