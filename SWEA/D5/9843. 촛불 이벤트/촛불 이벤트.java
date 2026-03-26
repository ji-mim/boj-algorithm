import java.io.*;
import java.util.*;


public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringBuilder sb = new StringBuilder();
			long N = Long.parseLong(br.readLine());
			
			long ans = binarySearch(1, (long)Math.sqrt(2.0 * N) + 1 , N);
			sb.append("#").append(tc).append(" ").append(ans);
			System.out.println(sb);
		}
		
		
	}
	
	
	static long binarySearch(long left, long right, long target) {
		long result = -1;
		
		while(left <= right) {
			long mid = (right + left) / 2;
			long k = mid * (mid + 1) / 2;
			
			if (k == target){
				result = mid;
				break;
			}
			
			if (k > target) {
				right = mid - 1;
			}else {
				left = mid + 1;
			}
		}
		
		return result;
	}
}
