import java.util.*;
import java.io.*;

public class Main {
	
	//주사위 위랑 밑의 좌표를 정해두고 이동할 때마다 
	//방향에 따라 주사위 배열을 옮겨주고
	//지도에 배열이 0이면 지도에 값을 넣고, 0이 아니면 주사위에 값을 넣고 지도 숫자를 0으로 바꾸기
	
	public static int N, M, x, y, K;
	
	public static int[][] map = new int[25][25];
	
	public static int topx = 1;
	public static int topy = 1;
	public static int bottomx = 3;
	public static int bottomy = 1;
	
	public static int[][] dice = {
			{0,0,0},
			{0,0,0},
			{0,0,0},
			{0,0,0}
	};
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st; 
	public static int [] dx = {0, 0, 0, -1, 1}; // 동서북남
	public static int [] dy = {0, 1, -1, 0, 0};
	public static boolean flag = false;
	
	public static void main(String args[]) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		initMap();
		StringBuilder sb = new StringBuilder();
		//TODO K번의 명령어를 받고, 명령 받을 때마다 1) 주사위 움직이기 (배열 바깥인지 확인), 지도 체크
		st = new StringTokenizer(br.readLine());
		while(K -- > 0) {
			flag = false;
			move(Integer.parseInt(st.nextToken()));
			if(flag) {
				sb.append(dice[topx][topy]);
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
		
	}
	public static void move(int dir) {
		
		if(dir == 1) {
			int nx = x + dx[1];
			int ny = y + dy[1];
			if(!inRange(nx,ny)) {
				return;
			}
			flag = true;
			x = nx;
			y = ny;
			rollDice(dir);
			interactWithMap();
		}			
		else if (dir == 2) {
			int nx = x + dx[2];
			int ny = y + dy[2];
			if(!inRange(nx,ny)) {
				return;
			}
			flag = true;
			x = nx;
			y = ny;
			rollDice(dir);
			interactWithMap();

		}
		else if (dir == 3) {
			int nx = x + dx[3];
			int ny = y + dy[3];
			if(!inRange(nx,ny)) {
				return;
			}
			flag = true;
			x = nx;
			y = ny;
			rollDice(dir);
			interactWithMap();

		}
		else if (dir == 4){
			int nx = x + dx[4];
			int ny = y + dy[4];
			if(!inRange(nx,ny)) {
				return;
			}
			flag = true;
			x = nx;
			y = ny;
			rollDice(dir);
			interactWithMap();
		}
		
		
	}
	
	public static void rollDice(int dir) {
		if(dir == 1) { // 동
			int temp = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = temp;
		}			
		else if (dir == 2) { // 서
			int temp = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = temp;
		}
		else if (dir == 3) { // 북
			int temp = dice[0][1];
			for(int i = 0; i < 3 ; i ++) {
				dice[i][1] = dice[i + 1][1];
			}
			dice[3][1] = temp;
		}
		else if (dir == 4){ // 남
			int temp = dice[3][1];
			for(int i = 3; i > 0 ; i --) {
				dice[i][1] = dice[i - 1][1];
			}
			dice[0][1] = temp;

		}
	}
	
	public static void interactWithMap() {
		if (map[x][y] != 0) {
			dice[bottomx][bottomy] = map[x][y];
			map[x][y] = 0 ;
		}else {
			map[x][y] = dice[bottomx][bottomy];
		}
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<=y && y < M ;
	}
	
	public static void initMap() throws IOException {
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
