import java.util.*;

public class Main {

	public static void main(String[] args) {
		// 끝을 포함하면 안됨
		// y, x 좌표로 주어짐 
		
		Scanner sc = new Scanner(System.in);
		
		int[][] paper = new int[110][110];
		
		int n = sc.nextInt();
		
		while(n -- > 0) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			
			for (int i = x ; i < x + 10 ; i ++) {
				for (int j = y ; j < y + 10 ; j ++) {
					paper[i][j] = 1;
				}
			}
		}
		int count = 0 ;
		
		for (int i = 0 ; i < 110 ; i ++) {
			for (int j = 0 ; j < 110 ; j ++) {
				if(paper[i][j] == 1) {
					count ++;
				}
			}
		}
		
		System.out.println(count);
	}

}
