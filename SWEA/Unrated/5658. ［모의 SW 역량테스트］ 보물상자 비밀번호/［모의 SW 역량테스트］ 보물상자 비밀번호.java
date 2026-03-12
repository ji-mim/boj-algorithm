import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
	
	static HashSet<Integer> nums = new HashSet<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int rotateNum = N/4;
			ArrayList<ArrayDeque<String>> qlist = new ArrayList<>();
			ArrayDeque<String> q1 = new ArrayDeque<>();
			ArrayDeque<String> q2 = new ArrayDeque<>();
			ArrayDeque<String> q3 = new ArrayDeque<>();
			ArrayDeque<String> q4 = new ArrayDeque<>();
			
			qlist.add(q1);
			qlist.add(q2);
			qlist.add(q3);
			qlist.add(q4);
			
			String[] input = br.readLine().split("");
			int c = 0;
			for (int i = 0 ; i < N ; i ++) {
				if(i % rotateNum == 0) c ++;
				if(c == 1) q1.add(input[i]);
				if(c == 2) q2.add(input[i]);
				if(c == 3) q3.add(input[i]);
				if(c == 4) q4.add(input[i]);
			}
			
			nums.clear();
			
			for (int i = 0 ; i < rotateNum ; i ++) {
				// 각 덱에서 만들어진 수 set에 넣어주기 
				for(ArrayDeque<String> q : qlist) {
					nums.add(getDemicalNum(q));
				}
				// 회전 
				q2.addFirst(q1.removeLast());
				q3.addFirst(q2.removeLast());
				q4.addFirst(q3.removeLast());
				q1.addFirst(q4.removeLast());
			}
		
			List<Integer> sortedNums = nums.stream().sorted().collect(Collectors.toList());
			Collections.reverse(sortedNums);
			int ans = sortedNums.get(K - 1);
			System.out.println("#" + tc + " " + ans);
		
		}
	}
	
	public static int getDemicalNum(ArrayDeque<String> q) {
		String hex = "";
		
		for(String s : q) {
			hex += s;
		}
		
		int demical = Integer.parseInt(hex, 16);
		
		return demical;
	}

}
