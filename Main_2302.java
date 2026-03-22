import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] vip = new int[M];
        for (int i = 0; i < M; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        if (N >= 1) dp[1] = 1;
        if (N >= 2) dp[2] = 2;

        for (int i = 3; i <= N; i++) {
          dp[i] = dp[i - 1] + dp[i - 2];
      }

      int answer = 1;
      int prev = 0;

      for(int i = 0; i < M; i++){
        int len = vip[i] - prev -1;
        answer *= dp[len];
         prev = vip[i];
      }

      answer *= dp[N - prev];

      System.out.println(answer);

      }
    }