import java.io.*;
import java.util.*;

public class Solution {

    static int cur, ans;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            ans = 0;
            cur = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());

            long s = a + b;
            long x = Math.min(a,b);

            long pow = modPow(2, n, s);
            long k = (x * pow) % s;
            long ans = Math.min(k, s - k);

            System.out.println("#" + tc + " " + ans);
        }
    }

    public static long modPow(long base, long exp, long mod) {
        if (exp == 0) return 1;
        if (exp == 1) return base % mod;

        long half = modPow(base, exp / 2, mod);
        long result = (half * half) % mod;

        if (exp % 2 == 1) {
            result = (result * base) % mod;
        }

        return result;
    }

}