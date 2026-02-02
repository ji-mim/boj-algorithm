import java.util.*;
import java.io.*;

public class Main {
	// 길이가 50보다 작으니까 앞으로만 가도 50, 전체 배열을 110 * 110 정도로 잡아두고 가운데서 남쪽 방향으로 시작한다고 해보자
	// 거기서 입력을 쭉 받고 한번이라도 갔던 행,열을 잡아두고, 그 격자를 출력하는데 그 중에서 갔던 곳이 아니면 #(벽), 갔던 곳은 .(길)
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int ARR_LEN = 110;
	public static String [][] arr = new String[ARR_LEN][ARR_LEN];
	public static int x = 55;
	public static int y = 55;
	public static int dir = 0 ; 
	public static int [] dx = {1,0,-1,0}; // 남 서 북 동 
	public static int [] dy = {0,-1,0,1};
	
	
	public static void main(String[] arg) throws IOException {
		int n = Integer.parseInt(br.readLine());
		String[] ways = br.readLine().split("");
		
		for (int i = 0 ; i < ARR_LEN ; i ++) {
			for (int j = 0 ; j < ARR_LEN ; j ++) {
				arr[i][j] = "#";
			}
		}
		
		arr[x][y] = ".";
		
		for (int i = 0 ; i < n ; i ++) {
			String command = ways[i];
			execute(command);
//			System.out.printf("x = %d, y = %d, dir = %d \n", x, y, dir);
		}
		
		//x1,x2,y1,y2 찾기 
		
		int x1 = Integer.MAX_VALUE;
		int x2 = -Integer.MAX_VALUE;
		int y1 = Integer.MAX_VALUE;
		int y2 = -Integer.MAX_VALUE;
		
		for (int i = 0 ; i < ARR_LEN ; i ++) {
			for (int j = 0 ; j < ARR_LEN ; j ++) {
				if (arr[i][j].equals(".")) {
					x1 = Math.min(x1, i);
					y1 = Math.min(y1, j);
					x2 = Math.max(x2, i);
					y2 = Math.max(y2, j);
				}
			}
		}
		
//		System.out.printf("x1 = %d, x2 = %d, y1 = %d, y2 = %d", x1,x2,y1,y2);
		
		for (int i = x1 ; i <= x2; i ++) {
			for (int j = y1 ; j <= y2 ; j ++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		
		
		
	}
	
	public static void execute(String command) {
		switch(command) {
			case "F" :
				x += dx[dir];
				y += dy[dir];
				arr[x][y] = ".";
				break;
			
			case "R" : 
				dir = (dir + 1) % 4;
				break;
			
			case "L" : 
				dir = (dir + 3) % 4;
				break;
		}
	}
	

}
