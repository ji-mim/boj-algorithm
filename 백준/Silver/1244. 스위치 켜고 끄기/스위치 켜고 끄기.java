import java.io.*;
import java.util.*;


public class Main {

	static int N, M; // LED 수, 학생 수
	static int[] LED;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		LED = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1 ; i <= N ; i ++) {
			LED[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		for (int t = 0 ; t < M ; t ++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			changeLED(gender, idx);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1 ; i <= N ; i ++) {
			sb.append(LED[i]).append(" ");
			if (i % 20 == 0) sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void changeLED(int gender, int idx) {
		if(gender == 1) { // 남자였을 때 받은 idx의 배수를 켠다.
			int val = idx;
			int multi = 1;
			while(val * multi <= N) {
				turn(val * multi ++);
			}
		}else { //여자였을 때 대칭 구간을 찾는다
			for (int i = 0 ; i <= N ; i ++) {
				int left = idx - i;
				int right = idx + i;
				
				if(left <= 0 || left > N || right <= 0 || right > N) break;
				
				if(LED[left] == LED[right]) {
					if(i == 0) {
						turn(left);
					}else {
						turn(left);
						turn(right);
					}
				}else {
					break;
				}
			}
			
		}
	}

	static void turn(int idx) {
		LED[idx] = (LED[idx] + 1) % 2;
	}
}
