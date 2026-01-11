import java.io.*;
import java.util.*;

public class Main_1799 {
    static final long MOD = 1_000_000_009L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[] ns = new int[T];
        int maxN = 0;

        for (int i = 0; i < T; i++) {
            ns[i] = Integer.parseInt(br.readLine());
            if (ns[i] > maxN) maxN = ns[i];
        }

        long[] dp = new long[Math.max(4, maxN + 1)];
        dp[0] = 1;           // (보통 안 쓰지만 안전)
        dp[1] = 1;           // 1
        dp[2] = 2;           // 11, 2
        dp[3] = 4;           // 111, 12, 21, 3

        for (int i = 4; i <= maxN; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(dp[ns[i]]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
