import java.util.*;

public class Main {

    static int[][] arr; // arr[len][first] = 길이가 len이고 첫 자리가 first인 감소하는 수의 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 감소하는 수는 총 1023개 (0 ~ 1022번째)
        if (N >= 1023) {
            System.out.println(-1);
            return;
        }

        // 0 ~ 9
        if (N <= 9) {
            System.out.println(N);
            return;
        }

        arr = new int[11][10]; // 길이 1~10, 첫 자리 0~9
        int[] prefixSum = new int[10];

        // 길이 1인 감소하는 수
        for (int i = 0; i <= 9; i++) {
            arr[1][i] = 1;
        }

        // DP 채우기
        for (int len = 2; len <= 10; len++) {
            getPrefixSum(len - 1, prefixSum);

            for (int first = 0; first <= 9; first++) {
                // 길이가 len이면 첫 자리는 적어도 len-1 이상이어야 함
                // 예: 길이 3이면 첫 자리는 최소 2
                if (first >= len - 1) {
                    arr[len][first] = prefixSum[first - 1];
                }
            }
        }

        // totalSum[len] = 1자리 ~ len자리 감소하는 수의 누적 개수
        int[] totalSum = new int[11];
        totalSum[1] = 10; // 0~9

        for (int len = 2; len <= 10; len++) {
            int cnt = 0;
            for (int first = 0; first <= 9; first++) {
                cnt += arr[len][first];
            }
            totalSum[len] = totalSum[len - 1] + cnt;
        }

        // N번째 수가 몇 자리인지 찾기
        int len = 1;
        while (totalSum[len] <= N) {
            len++;
        }

        // len자리 감소수들 중 몇 번째인지 (1-based)
        int k = N - totalSum[len - 1] + 1;

        StringBuilder sb = new StringBuilder();
        int maxDigit = 9;

        while (len > 0) {
            for (int digit = 0; digit <= maxDigit; digit++) {
                // 남은 len자리 수를 만들려면 현재 digit은 최소 len-1 이상이어야 함
                if (digit < len - 1) continue;

                int cnt;
                if (len == 1) {
                    cnt = 1;
                } else {
                    cnt = arr[len][digit];
                }

                if (k > cnt) {
                    k -= cnt;
                } else {
                    sb.append(digit);
                    maxDigit = digit - 1;
                    len--;
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }

    static void getPrefixSum(int r, int[] prefix) {
        prefix[0] = arr[r][0];
        for (int i = 1; i <= 9; i++) {
            prefix[i] = prefix[i - 1] + arr[r][i];
        }
    }
}