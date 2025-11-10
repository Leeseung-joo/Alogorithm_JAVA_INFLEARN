import java.io.*;
import java.util.*;

public class Main_21 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] score = new int[N];
        int[] time  = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
            time[i]  = Integer.parseInt(st.nextToken());
        }

        // 0/1 Knapsack: 1차원 DP, 시간 역순 순회
        int[] dp = new int[M + 1];
        for (int i = 0; i < N; i++) {
            for (int t = M; t >= time[i]; t--) {
                dp[t] = Math.max(dp[t], dp[t - time[i]] + score[i]);
            }//남은시간이 t일때, 획득한 최대 점수
        }

        System.out.println(dp[M]);
    }
}
