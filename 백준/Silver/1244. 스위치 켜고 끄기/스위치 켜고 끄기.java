import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine());
		int[] LEDs = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < size; i++) {
			LEDs[i] = Integer.parseInt(st.nextToken());
		}
		
		int sn = Integer.parseInt(br.readLine());
		int[][] students = new int[sn][2];
		for(int i = 0; i < sn; i++) {
			String[] line = br.readLine().split(" ");
			students[i][0] = Integer.parseInt(line[0]);
			students[i][1] = Integer.parseInt(line[1]);
		}
		
		for(int num = 0; num < sn; num++) {
			int gender = students[num][0];
			int where = students[num][1] - 1;
			
			if(gender == 1) {
				for(int i = where; i < size; i += (where + 1)) {
					if(LEDs[i] == 1) {
						LEDs[i] = 0;
					} else {
						LEDs[i] = 1;
					}
				}
			} else if(gender == 2) {
				int start = where;
				int end = where;
				while(start > 0 && end < (size -1) && LEDs[start] == LEDs[end]) {
					start--;
					end++;
					if(LEDs[start] != LEDs[end]) {
						start++;
						end--;
						break;
					}
				}
				for(int i = start; i <= end; i++) {
					if(LEDs[i] == 1) {
						LEDs[i] = 0;
					} else {
						LEDs[i] = 1;
					}
				}
			}
			
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++) {
			if(i % 20 == 0) {
				if(i == 0) {
					sb.append(LEDs[i]);
					sb.append(' ');
					continue;
				}
				sb.append('\n');
			}
			sb.append(LEDs[i]);
			sb.append(' ');
		}
		System.out.println(sb);
		
	}

}
