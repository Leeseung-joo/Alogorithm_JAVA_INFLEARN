import java.io.*;

public class Main_1788 {
  static final int MOD = 1_000_000_000;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int absN = Math.abs(N);

    // 0 처리
    if (absN == 0) {
      System.out.println(0);
      System.out.println(0);
      return;
    }

    // 양수 피보나치만 계산 (mod)
    long[] dp = new long[absN + 2];
    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2; i <= absN; i++) {
      dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
    }

    long val = dp[absN]; // |F(N)| mod MOD

    // 부호 결정
    int sign;
    if (N > 0) {
      sign = 1;
    } else {

      sign = (absN % 2 == 0) ? -1 : 1;
    }

    System.out.println(sign);
    System.out.println(val);
  }
}
