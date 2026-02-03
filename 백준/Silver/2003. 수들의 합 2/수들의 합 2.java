import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s,e,sum,cnt;
		s=e=sum=cnt=0;
		
		while(true) {
			if(sum >= M) {
				if (sum == M) {
					cnt ++;
				}
				sum -= arr[s ++];
			}else if(e == N) {
				break;
			}else {
				sum += arr[e ++];
			}
		}
		
		System.out.println(cnt);
		
	}

}
