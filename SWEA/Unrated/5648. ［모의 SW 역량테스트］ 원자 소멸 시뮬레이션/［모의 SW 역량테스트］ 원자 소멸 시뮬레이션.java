import java.io.*;
import java.util.*;

class Atom{
	int x, y, dist, e;
	
	public Atom(int x, int y, int dist, int e) {
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.e = e;
	}
	
	@Override
	public String toString() {
		return "[x = " + this.x + " y = " + this.y + " dist = " + dist + " e = " + e + "]";
	}
}


public class Solution {
	
	static final int MAX_LEN = 4050;
	static final int OFFSET = 1000; // 입력 받을 때 2배로 받아야 함 
	static int[][] grid = new int[MAX_LEN][MAX_LEN];
	static ArrayList<Atom> atomList = new ArrayList<>();
	static ArrayList<Atom> boomList = new ArrayList<>();
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N, T;
	static int totalEnergy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			atomList.clear();
			totalEnergy = 0 ;
			N = Integer.parseInt(br.readLine());
			while(N -- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int y = (Integer.parseInt(st.nextToken()) + OFFSET) * 2;
				int x = (Integer.parseInt(st.nextToken()) + OFFSET) * 2;
				int d = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				atomList.add(new Atom(x,y,d,e));
			}
			
			for (int _  = 0 ; _ < MAX_LEN ; _ ++) {
				for (int i = atomList.size() - 1 ; i >= 0 ; i --) {
					moveAtom(i); // 원소 이동, 좌표에 기록
				}
				checkBoom(); // 좌표 확인하면서 1 이상인 곳 터트리기 
//				restore(); // 좌표 복구 
			}
			
			System.out.println("#" + tc + " " + totalEnergy);
			
		}
	}
	
	public static void checkBoom() {
		boomList.clear();
		
		for(Atom a : atomList) {
			if(grid[a.x][a.y] > 1) {
				boomList.add(a);
			}
		}
		
		boom();
	}
	
	public static void boom() {
		//boomList 보면서 터트리고 에너지 추가하고, 해당 좌표가 여전히 3이면 0으로 만들기 
		
		for (int i = boomList.size() - 1 ; i >= 0 ; i --) {
			Atom boomAtom = boomList.get(i);
			totalEnergy += boomAtom.e;
			grid[boomAtom.x][boomAtom.y] = 0;
			atomList.remove(boomAtom);
		}
	}
	
//	public static void restore() {
//		for(Atom a : atomList) {
//			grid[a.x][a.y] = 0;
//		}
//	}
	
	public static void moveAtom(int idx) {
		Atom a = atomList.get(idx);
		grid[a.x][a.y] = 0;
		a.x += dx[a.dist];
		a.y += dy[a.dist];
		
		if(!inRange(a.x, a.y)) {
			atomList.remove(idx);
			return;
		}
		
		grid[a.x][a.y] += 1;
			
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x < MAX_LEN && 0<= y && y < MAX_LEN;
	}
}
