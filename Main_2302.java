import java.util.*;
import java.io.*;
public class Main_2302 {
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    
    int[] dp = new int[41];
    int M = Integer.parseInt(br.readLine());
    
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;

    for(int i = 3; i <= N; i++){
      dp[i] = dp[i-1] + dp[i-2];
    }

    int ans = 1;

    int beforeSeat = 0;

    for(int i = 0; i  < M; i++){
      int temp = Integer.parseInt(br.readLine());
      ans *= dp[temp-beforeSeat -1];
      beforeSeat = temp;
    }

    ans *= dp[N - beforeSeat];
    System.out.println(ans);




  }
  
}

//2번, 4번, 7번 고정
