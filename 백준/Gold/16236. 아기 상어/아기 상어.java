import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish>{
	int x, y, size, time;
	
	public Fish(int x, int y, int size, int time){
		this.x = x;
		this.y = y;
		this.size = size;
		this.time = time;
	}
	
	@Override
	public int compareTo(Fish f) {
		if(this.time == f.time && this.x == f.x) return Integer.compare(this.y, f.y);
		if(this.time == f.time) return Integer.compare(this.x, f.x);
		return Integer.compare(this.time, f.time);
	}
}

class Pos{
	int x, y;
	
	public Pos(int x, int y) {
		this.x = x; 
		this.y = y;
	}
}

public class Main {
	
	public static int [][] map;
	public static int [][] timeMap;
	public static boolean [][] visited;
	public static  ArrayList<Fish> foods = new ArrayList<>();
	public static ArrayDeque<Pos> que = new ArrayDeque<>();
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	public static int N, size, full, time;
	public static Pos sharkPos;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		size = 2; 
		full = 0;
		time = 0;
		map = new int[N][N];
		timeMap = new int[N][N];
		visited = new  boolean[N][N];
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) sharkPos = new Pos(i,j);
			}
		}
		
		while(true) {
			initVisited(); // visited 배열 초기화 
			initTimeMap(); // visited 배열 초기화 
			que.clear();
			foods.clear();
			
			push(sharkPos, 0);
			
			bfs();
			
//			printTime();
			
			if(foods.isEmpty()) break;
			
			eatFish();
			
		}
		
		System.out.println(time);
		
		
		
	}
	
	public static void eatFish() {
		Collections.sort(foods);
		Fish food = foods.get(0);
		full ++ ;
		time += timeMap[food.x][food.y];
		if(full == size) {
			full = 0 ;
			size ++;
		}
//		System.out.println("상어 위치: " + sharkPos.x + " , " + sharkPos.y);
		
		map[sharkPos.x][sharkPos.y] = 0;
		map[food.x][food.y] = 9;
		sharkPos.x = food.x;
		sharkPos.y = food.y;
		
//		System.out.println("[먹은 생선 : ( x = " +food.x + " ,y = " + food.y  + ", size = " + food.size + ") time = " + food.time +"]");
	}
	
	public static void bfs() {
		
		while(!que.isEmpty()) {
			Pos curr = que.poll();
			int x = curr.x;
			int y = curr.y;
			
			for (int i = 0 ; i < 4 ; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!canGo(nx,ny)) continue;
				push(new Pos(nx,ny), timeMap[x][y] + 1);
				if(map[nx][ny] != 0 && map[nx][ny] < size) {
					foods.add(new Fish(nx,ny,map[nx][ny], timeMap[x][y] + 1));
				}
				
				
				
//				if(canEat(nx, ny)) {
//					push(new Pos(nx,ny), timeMap[x][y] + 1);
//					if(map[nx][ny] != 0) foods.add(new Fish(nx,ny,map[nx][ny], timeMap[x][y] + 1));
//					x = nx;
//					y = ny;
//				}
//				else if(canGo(nx, ny)) {
//					push(new Pos(nx,ny), timeMap[x][y] + 1);
//					x = nx;
//					y = ny;
//				}

			}
		}
	}
	
	public static boolean canEat(int x, int y) {
		return (inRange(x,y) && map[x][y] < size && !visited[x][y]);
	}
	
//	public static boolean canGo(int x, int y) {
//		return (inRange(x,y) && map[x][y] <= size && !visited[x][y]);
//	}
	public static boolean canGo(int x, int y) {
		return (inRange(x,y) && !visited[x][y] && map[x][y] <= size);
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < N && 0<= y && y < N;
	}
	
	public static void push(Pos p, int time) {
		visited[p.x][p.y] = true;
		timeMap[p.x][p.y] = time;
		que.offer(p);
	}
	
	
	public static void initVisited() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				visited[i][j] = false;
			}
		}
	}
	
	public static void initTimeMap() {
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				timeMap[i][j] = 0;
			}
		}
	}
	
	public static void printTime() {
		System.out.println("===============================");
		for(int[] time: timeMap) {
			System.out.println(Arrays.toString(time));
		}
	}
	
	
}
