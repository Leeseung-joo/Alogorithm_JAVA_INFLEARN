import java.util.*;
import java.io.*;

public class Main_20 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine().trim());

        final int INF = 1_000_000; // 충분히 큰 값(오버플로우 방지)
        int[] dp = new int[k + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int c : coins) {
            for (int sum = c; sum <= k; sum++) {
                if (dp[sum - c] != INF) {           // 도달 가능할 때만 갱신
                    dp[sum] = Math.min(dp[sum], dp[sum - c] + 1);
                }
            }
        }

        System.out.println(dp[k] == INF ? -1 : dp[k]);
    }
}
