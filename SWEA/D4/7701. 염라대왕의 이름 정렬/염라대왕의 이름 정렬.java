import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		List<String> list = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			int N = Integer.parseInt(br.readLine());
			list.clear();
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0 ; i < N ; i ++) {
				list.add(br.readLine());
			}
			
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if(o1.length() != o2.length()) return Integer.compare(o1.length(), o2.length());
					return o1.compareTo(o2);
				}
			});
			
			for (int i = N - 1 ; i > 0 ; i --) {
				if(list.get(i).equals(list.get(i - 1))) {
					list.remove(i);
				}
			}
			
			sb.append("#").append(tc).append("\n");
			for(int i = 0 ; i < list.size() - 1 ; i ++) {
				sb.append(list.get(i)).append("\n");
			}
			sb.append(list.get(list.size() - 1));
			
			System.out.println(sb);
			
		}
		
	}

}
