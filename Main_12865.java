import java.util.*;
import java.io.*;

public class Main_12865 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] weight = new int[N];
    int[] value = new int[N];
    for(int i = 0; i < N; i++){
      
      st = new StringTokenizer(br.readLine());
      weight[i] = Integer.parseInt(st.nextToken());
      value[i] = Integer.parseInt(st.nextToken()); 
    }

    int[][] dp = new int[N+1][K+1]; //i번째 물건까지 봤을 때, 최대 무게 w에서의 최대 가치
    dp[0][0] = 0;

    for(int i = 1; i <= N; i++){
      int wi = weight[i-1];
      int vi = value[i-1];

      for(int w = 0; w <= K; w++){
        dp[i][w] = dp[i-1][w]; //담지 않음

        if(wi <= w){
          dp[i][w] = Math.max(dp[i][w], dp[i-1][w - wi] + vi);
        }
      }
      
    }
    System.out.println(dp[N][K]);
  
}
//dp[i][W] : 최대 무게가 W인 가방에서 i번째 물건까지 판단했을 떄의 최대 가치
}