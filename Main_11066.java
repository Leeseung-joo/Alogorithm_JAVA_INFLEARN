import java.util.*;
import java.io.*;
public class Main_11066 {

  public static void main(String[] args) throws IOException{
    
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();

    for(int t = 0; t < T; t++){
      int K = sc.nextInt();
      int[] files = new int[K];
      int[] sum = new int[K+1];

      for(int i = 0; i < K; i++){
        files[i] = sc.nextInt();
        sum[i+1] = sum[i] + files[i];
      }

      int[][] dp = new int[K][K]; //dp 테이블 초기화


      for(int len = 1; len < K; len++){
        for(int i = 0; i + len < K; i++){
          int j = i+ len;
          dp[i][j] = Integer.MAX_VALUE;
          for (int k = i; k < j; k++) {
            dp[i][j] = Math.min(dp[i][j], 
              dp[i][k] + dp[k + 1][j] + sum[j + 1] - sum[i]);
        }
      }
    }
    System.out.println(dp[0][K - 1]);

  }
  
}
