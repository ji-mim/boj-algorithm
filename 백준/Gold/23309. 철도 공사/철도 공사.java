import java.util.*;
import java.io.*;

class Station{
    int val;
    Station prev;
    Station next;
    
    public Station() {}
    
    public Station(int val) {
    	this.val = val;
    }

    public Station(int val, Station prev, Station next){
        this.val = val;
        this.prev = prev;
        this.next = next;

    }
}

public class Main  {
    // N <= 500_000, M <= 1_500_000

    // M번의 명령어가 주어졌을 때, N개의 역을 순회하면서 찾으면 시간 터짐 
    // N 길이의 배열을 만들어서 거기 내부에 이전 값이랑 이후 값을 기록해둬야하나? 

    public static int MAX_N = 1_000_010;
    public static Station [] stationArr = new Station[MAX_N];
    public static int N, M;
    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();
    public static int[] nums = new int[MAX_N];

    public static void main(String args[]) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0 ; i < N ; i ++) {
        	int num = Integer.parseInt(st.nextToken());
        	nums[i] = num;
        	stationArr[num] = new Station();
        }
        
        linkedStation(nums);
        
        while (M -- > 0) {
        	st = new StringTokenizer(br.readLine());
        	String command = st.nextToken();
        	
        	execute(command);
        }
       
        
        System.out.println(sb);

        
    }
    
    private static void execute(String command) {
    	if (command.equals("BN")) {
    		int findStationNum = Integer.parseInt(st.nextToken());
    		int newStationNum  = Integer.parseInt(st.nextToken());
    		
    		if (stationArr[newStationNum] != null) {
    			return;
    		}
    		
    		Station findStation = stationArr[findStationNum];
    		
    		sb.append(findStation.next.val);
    		sb.append("\n");
    		
    		// 새로운 역을 기존 역 다음에 추가 
    		Station newStation = new Station(newStationNum, findStation, findStation.next);
    		findStation.next.prev = newStation;
    		findStation.next = newStation;
    		stationArr[newStationNum] = newStation;

    		
    	}else if(command.equals("BP")) {
    		int findStationNum = Integer.parseInt(st.nextToken());
    		int newStationNum  = Integer.parseInt(st.nextToken());
    		
    		if (stationArr[newStationNum] != null) {
    			return;
    		}

    		
    		Station findStation = stationArr[findStationNum];
    		
    		sb.append(findStation.prev.val);
    		sb.append("\n");
    		
    		// 새로운 역을 기존 역 이전에 추가 
    		Station newStation = new Station(newStationNum, findStation.prev, findStation);
    		findStation.prev.next = newStation;
    		findStation.prev = newStation;
    		stationArr[newStationNum] = newStation;

    	}else if(command.equals("CN")){
    		int findStationNum = Integer.parseInt(st.nextToken());
    		Station findStation = stationArr[findStationNum];
    		Station del = findStation.next;
    		sb.append(del.val);
    		sb.append("\n");
    		
    		findStation.next = del.next;
    		del.next.prev = findStation;
    		stationArr[del.val] = null;
    		
    		
    	}else if(command.equals("CP")) {
    		int findStationNum = Integer.parseInt(st.nextToken());
    		Station findStation = stationArr[findStationNum];
    		Station del = findStation.prev;
    		sb.append(del.val);
    		sb.append("\n");
    		
    		findStation.prev = del.prev;
    		del.prev.next = findStation;
    		stationArr[del.val] = null;
    	}
    }
    
    private static void linkedStation(int[] nums) {
        for (int i = 0; i < N; i++) {
            int val = nums[i];
            stationArr[val] = new Station(val);   // 여기서 val 포함해서 생성
        }

        for (int i = 0; i < N; i++) {
            int cur = nums[i];
            int prevVal = nums[(i - 1 + N) % N];
            int nextVal = nums[(i + 1) % N];

            stationArr[cur].prev = stationArr[prevVal];
            stationArr[cur].next = stationArr[nextVal];
        }
    }


	private static void linkedStation(ArrayList<Integer> nums) {
		for (int i = 0 ; i < N ; i ++) {
        	Station station = stationArr[nums.get(i)];
        	if (i == 0) {
        		station.val = nums.get(i);
        		station.prev = stationArr[nums.get(N-1)];
        		station.next = stationArr[nums.get(i + 1)];
        		continue;
        	}
        	
        	if (i == N - 1) {
        		station.val = nums.get(i);
        		station.prev = stationArr[nums.get(i - 1)];
        		station.next = stationArr[nums.get(0)];
        		continue;
        	}
        	
    		station.val = nums.get(i);
    		station.prev = stationArr[nums.get(i - 1)];
    		station.next = stationArr[nums.get(i + 1)];
        }
	}
    
}
