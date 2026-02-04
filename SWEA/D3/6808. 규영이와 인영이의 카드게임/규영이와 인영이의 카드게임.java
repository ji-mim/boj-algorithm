import java.io.*;
import java.util.*;

public class Solution {
	// 9개의 카드가 주어지니까, 다른 카드 9개를 가지고 
	//모든 순열을 비교해 값을 비교한 후 규영, 인영이의 카드 점수를 매겨 이기는 경우와 지는 경우의 경우의 수룰 구한다 
	public static int MAX_LEN = 19;
	public static int[] cards = new int[MAX_LEN];
	public static boolean[] visited = new boolean[MAX_LEN];
	public static StringTokenizer st; 
	public static List<Integer> gyu = new ArrayList<>();
	public static List<Integer> young = new ArrayList<>();
	public static List<Integer> selected = new ArrayList<>(); // youn리스트에서 선택한 카드의 인덱스 순서
	public static int gWin, yWin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1 ; tc <= T ; tc ++) {
			gWin = yWin = 0;
			st = new StringTokenizer(br.readLine());
			initCard();
			
			select(0);
			
			// 규영 이긴거, 인영 이긴거 출력 
			System.out.printf("#%d %d %d\n", tc, gWin, yWin);
		}

	}
	
	public static void initCard() {
		gyu.clear();
		young.clear();
		selected.clear();
		
		for (int i = 0 ; i < 9 ; i ++) {
			gyu.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1 ; i <= 18 ; i ++) {
			if(!gyu.contains(i)) {
				young.add(i);
			}
		}
		
	}
	
	public static void select(int idx) {
		if(idx == 9) {
//			System.out.println(Arrays.toString(selected.toArray()));
			calculateWin();
			return;
		}
		
		for (int i = 0 ; i < 9 ; i ++) {
			if(visited[i]) continue;
			selected.add(i);
			visited[i] = true;
			select(idx + 1);
			selected.remove(selected.size() - 1);
			visited[i] = false;
		}
	}
	
	public static void calculateWin() {
		int gCount = 0;
		int yCount = 0;
		for (int i = 0 ; i < 9 ; i ++) {
			if(gyu.get(i) > young.get(selected.get(i))) {
				gCount += gyu.get(i) + young.get(selected.get(i));
			}else {
				yCount += gyu.get(i) + young.get(selected.get(i));
			}
		}
		
		if (gCount > yCount) {
			gWin ++;
		}else {
			yWin ++;
		}
	}
}
