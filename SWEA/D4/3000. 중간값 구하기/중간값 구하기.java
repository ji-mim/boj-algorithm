import java.io.*;
import java.util.*;

public class Solution {
	
	static PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> minPq = new PriorityQueue<>();
	static int mod = 20171109;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			maxPq.clear();
			minPq.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int firstNum = Integer.parseInt(st.nextToken());
			int result = 0;
			
			maxPq.add(firstNum);
			
			for (int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()) {
					maxPq.add(Integer.parseInt(st.nextToken()));
				}
				
				// min이 비어있으면 개수 맞춰주기
				if(minPq.isEmpty()) {
					share();
//					System.out.println("max: " + Arrays.toString(maxPq.toArray()));
//					System.out.println("min: " + Arrays.toString(minPq.toArray()));
					result += maxPq.peek() % mod;
					continue;
				}
				
				//max가 min보다 작을 때 까지 옮겨주기 
				while(!(maxPq.peek() <= minPq.peek())) {
					minPq.add(maxPq.poll());
				}
				
				share();
				
//				System.out.println("max: " + Arrays.toString(maxPq.toArray()));
//				System.out.println("min: " + Arrays.toString(minPq.toArray()));
				result += (maxPq.peek()) % mod;
				result %= mod;
				
			}
			
			System.out.println("#" + tc + " " + result);
			
			
		}
		
	}
	
	public static void share() {
		if(maxPq.size() > minPq.size() + 1) {
			while(maxPq.size() != minPq.size() + 1) {
				minPq.add(maxPq.poll());
			}
		}else if (maxPq.size() < minPq.size() + 1) {
			while(maxPq.size() != minPq.size() + 1) {
				maxPq.add(minPq.poll());
			}
		}
	}

}
