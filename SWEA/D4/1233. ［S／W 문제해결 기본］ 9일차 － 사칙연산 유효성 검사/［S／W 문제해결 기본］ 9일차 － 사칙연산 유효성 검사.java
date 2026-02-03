
import java.util.*;
import java.io.*;

public class Solution {
	
	// 연산자가 나왔을 때 내 자식 노드가 존재하는지 확인
	// 숫자가 나왔으면, 내 자식이 없어야 함 
	
	public static int MAX_LEN = 200;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<String> operator = Arrays.asList("-", "+", "*", "/");
		
		
		for (int t = 1 ; t <= 10 ; t ++) {
			
			String [] nodes = new String[MAX_LEN + 1];
			int n = Integer.parseInt(br.readLine());
			int ans = 1;
			
			while(n -- > 0) {
				st = new StringTokenizer(br.readLine(), " ");
				int len = st.countTokens();
				st.nextToken();
				String value = st.nextToken();
				
				if(!operator.contains(value) && len > 2) {
					ans = 0;
				}
			}
			
			System.out.printf("#%d %d\n", t, ans);
			
			
		}
	}
}
