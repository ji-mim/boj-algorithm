import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int ROW, COL, T, MAP[][], robot[];
	public static int[] dr = {0, 0, 1, -1, 0};
	public static int[] dc = {1, -1, 0, 0, 0};
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		robot = new int[2];
		MAP = new int[ROW][COL];
		for(int i = 0; i < ROW; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < COL; j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				if(MAP[i][j] == -1) {
					robot[0] = i-1;
					robot[1] = i;
				}
			}
		}
		for(int t = 1; t <= T; t++) {
			spread();
			move();
		}
		int sum = 0;
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COL; j++) {
				if(MAP[i][j] == -1) continue;
				sum += MAP[i][j];
			}
		}
		System.out.println(sum);
		
	}


	private static void move() {
        int up = robot[0];
        int down = robot[1];

        // 위쪽: 반시계
        // 1) 왼쪽 세로 (위로)
        for (int i = up - 1; i > 0; i--) MAP[i][0] = MAP[i - 1][0];
        // 2) 위 가로 (왼쪽으로)
        for (int j = 0; j < COL - 1; j++) MAP[0][j] = MAP[0][j + 1];
        // 3) 오른쪽 세로 (아래로)
        for (int i = 0; i < up; i++) MAP[i][COL - 1] = MAP[i + 1][COL - 1];
        // 4) 아래 가로 (오른쪽으로)
        for (int j = COL - 1; j > 1; j--) MAP[up][j] = MAP[up][j - 1];
        MAP[up][1] = 0; // 청정기에서 나온 바람은 0

        // 아래쪽: 시계
        // 1) 왼쪽 세로 (아래로)
        for (int i = down + 1; i < ROW - 1; i++) MAP[i][0] = MAP[i + 1][0];
        // 2) 아래 가로 (왼쪽으로)
        for (int j = 0; j < COL - 1; j++) MAP[ROW - 1][j] = MAP[ROW - 1][j + 1];
        // 3) 오른쪽 세로 (위로)
        for (int i = ROW - 1; i > down; i--) MAP[i][COL - 1] = MAP[i - 1][COL - 1];
        // 4) 위 가로 (오른쪽으로)
        for (int j = COL - 1; j > 1; j--) MAP[down][j] = MAP[down][j - 1];
        MAP[down][1] = 0;

        // 공기청정기 위치 유지
        MAP[up][0] = -1;
        MAP[down][0] = -1;
	}


	private static void spread() {

		int[][] temp = new int[ROW][COL];
        // 공기청정기 위치 고정
        temp[robot[0]][0] = -1;
        temp[robot[1]][0] = -1;
        
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COL; j++) {
				if(MAP[i][j] <= 0) continue;

				int dust = MAP[i][j];
				int amount = dust / 5;
				int cnt = 0;
				
				
				for(int d = 0; d < 4; d++) {
					int r = dr[d] + i;
					int c = dc[d] + j;
					if(r < 0 || r >= ROW || c < 0 || c >= COL) {
						continue;
					}
					if(MAP[r][c] == -1) {
						continue;
					}
					temp[r][c] += amount;
					cnt++;
				}
				
				temp[i][j] += dust - amount * cnt;
			}
		}
		
		MAP = temp;
		
	}
}
