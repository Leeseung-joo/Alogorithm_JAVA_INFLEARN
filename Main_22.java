package string;
import java.util.*;
import java.io.*;
public class Main_22 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());

    int[] T = new int[N+1];
    int[] P = new int[N+1];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());

      T[i+1] = Integer.parseInt(st.nextToken());
      P[i+1] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[N+2];

    for(int i = N; i >=1; i--){
      if(i+T[i] <= N+1){
        dp[i] = Math.max(dp[i+1], dp[i+T[i]]+P[i]);
      }else{
        dp[i] = dp[i+1];
      }
    }
    System.out.println(dp[1]);

    
  }
  
}
