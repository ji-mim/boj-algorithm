import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<String> set = new HashSet<>();
		TreeSet<String> ts = new TreeSet<>();
		StringBuilder sb = new StringBuilder();
		
		while(N -- > 0) {
			set.add(br.readLine());
		}
		
		while(M -- > 0) {
			String word = br.readLine();
			if(set.contains(word)) ts.add(word);
		}
		
		for(String s : ts) {
			sb.append(s).append("\n");
		}
		
		System.out.println(ts.size());
		System.out.println(sb);
		
		
		
	}

}
