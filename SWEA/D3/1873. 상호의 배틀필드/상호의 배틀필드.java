import java.io.*;
import java.util.*;


public class Solution {

	static int T, H, W;
	static char[][] map;
	static int rx, ry, dir;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			
			for (int i = 0 ; i < H ; i ++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0 ; j < W ; j ++) {
					if(input[j] == '^') { // 위방향
						map[i][j] = '.';
						rx = i;
						ry = j;
						dir = 0;
					}
					else if(input[j] == 'v') { // 아래방향
						map[i][j] = '.';
						rx = i;
						ry = j;
						dir = 1;
					}
					else if(input[j] == '<') { // 왼쪽방향
						map[i][j] = '.';
						rx = i;
						ry = j;
						dir = 2;
					}
					else if(input[j] == '>') { //오른쪽방향
						map[i][j] = '.';
						rx = i;
						ry = j;
						dir = 3;
					}else {
						map[i][j] = input[j];
					}
				}
			}
			
			int commandNum = Integer.parseInt(br.readLine());
			char[] commands = br.readLine().toCharArray();
			
			for (int i = 0 ; i < commandNum ; i ++) {
				command(commands[i]);
			}
			
			if (dir == 0) {
				map[rx][ry] = '^';
			}else if (dir == 1) {
				map[rx][ry] = 'v';
			}else if (dir == 2) {
				map[rx][ry] = '<';
			}else if (dir == 3) {
				map[rx][ry] = '>';
			}
			
			for (int i = 0 ; i < H ; i ++) {
				for (int j = 0 ; j < W ; j ++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.print(sb);
		}
	}
	
	public static void command(char type) {
		if(type == 'S') {
			int nx = rx;
			int ny = ry;
			
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) break;
				
				if(map[nx][ny] == '*') {
					map[nx][ny] = '.';
					break;
				}
				
				if(map[nx][ny] == '#') {
					break;
				}
			}
		}
		else if(type == 'U') {
			dir = 0;
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			
			if(nx < 0 || nx >= H || ny < 0 || ny >= W) return;
			if(map[nx][ny] == '*') return;
			if(map[nx][ny] == '#') return;
			if(map[nx][ny] == '-') return;
			
			rx = nx;
			ry = ny;
		}
		else if(type == 'D') {
			
			dir = 1;
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			
			if(nx < 0 || nx >= H || ny < 0 || ny >= W) return;
			if(map[nx][ny] == '*') return;
			if(map[nx][ny] == '#') return;
			if(map[nx][ny] == '-') return;
			
			rx = nx;
			ry = ny;
		}
		else if(type == 'L') {
			
			dir = 2;
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			
			if(nx < 0 || nx >= H || ny < 0 || ny >= W) return;
			if(map[nx][ny] == '*') return;
			if(map[nx][ny] == '#') return;
			if(map[nx][ny] == '-') return;
			
			rx = nx;
			ry = ny;
		}
		else if(type == 'R') {
			
			dir = 3;
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			
			if(nx < 0 || nx >= H || ny < 0 || ny >= W) return;
			if(map[nx][ny] == '*') return;
			if(map[nx][ny] == '#') return;
			if(map[nx][ny] == '-') return;
			
			rx = nx;
			ry = ny;
		}
	}

}
