import java.io.*;
import java.util.*;

public class Solution {
	
	public static int T;
	public static int minPrice;
	
	public static int[] prices = new int[4];
	public static int[] attendDays = new int[13];
	public static int[] offSetMonth = {1,1,3};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < 4 ; i ++) { // 마지막 가격은 빼고 계산 해야 함 
				prices[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1 ; i < 13 ; i ++) {
				attendDays[i] = Integer.parseInt(st.nextToken());
			}
			
			minPrice = prices[3];
			
			findMinPrice(1,0);
			
			System.out.println("#" + tc + " " + minPrice);
		}
	}
	
	public static void findMinPrice(int month, int price) {
		if(price >= minPrice) return;
		if(month > 12) {
			minPrice = Math.min(minPrice, price);
			return;
		}
		
		if(attendDays[month] == 0) findMinPrice(month + 1, price); // 볼 필요 없으면 넘기기 
		
		findMinPrice(month + 1, price + prices[0] * attendDays[month]);
		findMinPrice(month + 1, price + prices[1]);
		findMinPrice(month + 3, price + prices[2]);
	}
	

}
