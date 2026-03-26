import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());

            long answer = -1;

            long value = 1 + 8 * N;
            long sqrt = (long) Math.sqrt(value);

            // 완전제곱수 체크
            if (sqrt * sqrt == value) {
                long k = (-1 + sqrt) / 2;

                // 검증
                if (k * (k + 1) / 2 == N) {
                    answer = k;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}