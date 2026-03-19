import java.util.*;
import java.io.*;
public class Main_11053 {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    int[] dp = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int result = 0;

    for(int i = 0; i < N; i++){ //i번쨰 원소를 마지막으로 하는 LIS 길이
      dp[i] = 1;
      for(int j = 0; j < i; j++){
        if(arr[i] > arr[j]){
          dp[i] = Math.max(dp[i], dp[j] + 1);
          result = Math.max(dp[i], result);

        }
      }
    }
    System.out.println(result);
  }
  
}
