import java.util.*;
import java.io.*;
public class Main_18 {
  public static void main(String[] args) throws IOException {
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int[] arr = new int[N+1];

      for(int i = 0; i < N; i++){
        arr[i] = Integer.parseInt(st.nextToken());
      }

      int[] dp = new int[arr.length];
      dp[0] = 1;

      int answer = 0;
      for(int i = 1; i  < arr.length; i++){
        int max = 0;
        for(int j = i-1; j>=0; j--){
          if(arr[j] < arr[i] && dp[j] > max) max = dp[j];
        }
        dp[i] = max + 1; 
        answer = Math.max(dp[i], answer);
      }
      System.out.println(answer);
      }
  }

