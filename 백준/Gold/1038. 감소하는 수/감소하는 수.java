import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr; 

	
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		arr = new int[12][15]; // r = 자리수, c = 맨 앞자리 수 
		
		int[] prefixSum = new int[10];
		
		Arrays.fill(arr[1], 1);
		
		//arr 채우기 
		
		for(int i = 2 ; i <= 11 ; i ++) { // 자릿수 
			//그 전 자리수 prefixSum 구하기 
			getPrefixSum(i - 1, prefixSum);
//			System.out.println(Arrays.toString(prefixSum));
			for(int j = i - 1 ; j <= 10 ; j ++) {
				arr[i][j] = prefixSum[j - 1];
			}
		}
		
//		for (int[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
		
		
		int[] totalSum = new int[11];
		totalSum[1] = arr[2][10];
		for (int i = 2 ; i < 11 ; i ++) {
			totalSum[i] = totalSum[i - 1] + arr[i + 1][10];
		}
		
//		System.out.println(Arrays.toString(totalSum));
//		[0, 10, 55, 175, 385, 637, 847, 967, 1012, 1022, 1023]

		
		// N이 1023보다 크면 -1 반환 
		// N이 10보다 작거나 같으면 그냥 그 값 반환 
		// 이제 아니면 totalSum 순회하면서 나보다 클 때를 딱 찾아고, 거기를 r로 잡고 arr[r]을 0부터 순회하면서 더하다가 나보다 커지면 거기가 첫번쨰 수, r-1로 올라가서 내 값이 나올 때 까지 더하기 
		// 이러면  N + 1을 찾아야할 것 같은데 
		String ans = "";
		if (N >= 1023) System.out.println(-1);
		else if (N <= 10) System.out.println(N);
		else {
			int findN = N + 1;
			int len = 0 ;
			
			for (int i = 1 ; i < totalSum.length ; i ++) {
				if (totalSum[i] >= findN) {
//					ans += String.valueOf(i);
					findN -= totalSum[i - 1];
					len = i;
					break;
				}
			}
			
			while(len != 0) {
				int sum = 0;
				for (int i = 0 ; i < 10 ; i ++) {
//			        if (i < len - 1) continue;

					sum += arr[len][i];
					
					if(sum >= findN) {
						ans += String.valueOf(i);
						findN -= (sum - arr[len][i]);
						len --;
						break;
					}
				}
			}
			
			System.out.print(ans);
		}
	}
	
	
	static void getPrefixSum(int r, int[] prefix) {
		
		prefix[r-1] = arr[r][r-1];
		
		for (int i = r ; i <= 9 ; i ++) {
			prefix[i] = prefix[i-1] + arr[r][i];
		}
	}
}
