
import java.io.*;
import java.util.*;

public class Main {
	
	public static int N ;
	public static int [] arr; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int s = 0 ;
		int e = N - 1;
		
		int bestAbs = Math.abs(arr[s] + arr[e]);
		int bestS = s; 
		int bestE = e; 
		
		while(s < e) {
			int sum = arr[s] + arr[e];
			
			if(Math.abs(sum) < bestAbs) {
				bestAbs = Math.abs(sum);
				bestS = s; 
				bestE = e; 
			}
			
			if(sum <= 0) {
				if (sum == 0) {
					break;
				}
				s ++;
			}else {
				e --;
			}
		}
		
		System.out.printf("%d %d", arr[bestS], arr[bestE]);
	}
}
