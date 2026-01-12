import java.io.*;

public class Main_11057 {
    static final int MOD = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][10];

        // 초기값
        for (int j = 0; j < 10; j++) {
            dp[1][j] = 1;
        }

        // DP
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int j = 0; j < 10; j++) {
            ans = (ans + dp[N][j]) % MOD;
        }

        System.out.println(ans);
    }
}
