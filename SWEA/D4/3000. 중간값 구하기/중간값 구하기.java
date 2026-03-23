import java.io.*;
import java.util.*;

public class Solution {

    static final int MOD = 20171109;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minPq = new PriorityQueue<>();

            maxPq.offer(first);
            long result = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                addNumber(a, maxPq, minPq);
                addNumber(b, maxPq, minPq);

                result = (result + maxPq.peek()) % MOD;
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void addNumber(int x, PriorityQueue<Integer> maxPq, PriorityQueue<Integer> minPq) {
        // 1. 우선 적절한 힙에 넣기
        if (maxPq.isEmpty() || x <= maxPq.peek()) {
            maxPq.offer(x);
        } else {
            minPq.offer(x);
        }

        // 2. 크기 균형 맞추기
        if (maxPq.size() < minPq.size() + 1) {
            maxPq.offer(minPq.poll());
        } else if (maxPq.size() > minPq.size() + 1) {
            minPq.offer(maxPq.poll());
        }

        // 3. 순서 조건 보정
        if (!minPq.isEmpty() && maxPq.peek() > minPq.peek()) {
            int maxTop = maxPq.poll();
            int minTop = minPq.poll();
            maxPq.offer(minTop);
            minPq.offer(maxTop);
        }
    }
}