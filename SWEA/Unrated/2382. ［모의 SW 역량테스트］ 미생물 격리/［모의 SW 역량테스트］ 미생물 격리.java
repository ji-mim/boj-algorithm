import java.io.*;
import java.util.*;

public class Solution {
	
	static class Cell {
	    int x, y;
	    int sum;      // 총 미생물 수
	    int maxCnt;   // 들어온 군집 중 최대 개수
	    int dir;      // maxCnt를 가진 군집의 방향

	    public Cell(int x, int y, int sum, int maxCnt, int dir) {
	        this.x = x;
	        this.y = y;
	        this.sum = sum;
	        this.maxCnt = maxCnt;
	        this.dir = dir;
	    }
	}	
	static int T, N, M, K;
	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1};
	static ArrayList<Cell> microList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T ; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			microList.clear();
			for (int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken()) - 1;
				Cell micro = new Cell(x, y, cnt, cnt, d);
				microList.add(micro);
			}
			
			while(M -- > 0) {
				moveAll();
			}
			
			int totalCnt =  microList.stream().mapToInt(m -> m.sum).sum();
			
			System.out.println("#" + tc + " " + totalCnt);
		}
	}
	
	public static void moveAll() {
	    HashMap<Integer, Cell> nextMap = new HashMap<>();

	    for (Cell m : microList) {
	        int nx = m.x + dx[m.dir];
	        int ny = m.y + dy[m.dir];
	        int nd = m.dir;
	        int ncnt = m.sum;

	        if (nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) {
	            nd = changeDir(nd);
	            ncnt /= 2;
	        }

	        if (ncnt == 0) continue;

	        int key = nx * N + ny;

	        if (!nextMap.containsKey(key)) {
	            nextMap.put(key, new Cell(nx, ny, ncnt, ncnt, nd));
	        } else {
	            Cell cell = nextMap.get(key);
	            cell.sum += ncnt;

	            if (ncnt > cell.maxCnt) {
	                cell.maxCnt = ncnt;
	                cell.dir = nd;
	            }
	        }
	    }

	    ArrayList<Cell> nextList = new ArrayList<>();
	    for (Cell cell : nextMap.values()) {
	        nextList.add(new Cell(cell.x, cell.y, cell.sum, cell.maxCnt, cell.dir));
	    }

	    microList = nextList;
	}
	
	
	public static int changeDir(int d) {
		if (d == 0) return 1;
		else if (d == 1) return 0;
		else if (d == 2) return 3;
		else return 2;
	}
	
}
