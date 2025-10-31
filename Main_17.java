import java.util.*;
import java.io.*;
public class Main_17 {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[] dp = new int[N+2];
    dp[1] = 1;
    dp[2] = 2;

    for(int i = 3; i <= N+1; i++){
      dp[i] = dp[i-1] + dp[i-2];
    }

    System.out.println(dp[N+1]);




  }
  
}
