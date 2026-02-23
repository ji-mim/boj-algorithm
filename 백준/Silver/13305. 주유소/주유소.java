import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dist = new long[N];
        long[] fee = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1;  i < N ; i ++){
            dist[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= N ; i ++){
            fee[i] = Long.parseLong(st.nextToken());
        }

        long curMin = fee[1];
        long total = 0;

        for (int i = 1; i < N; i++) {
            if (fee[i] < curMin) curMin = fee[i];
            total += curMin * dist[i];
        }

        System.out.println(total);


    }
    
}
