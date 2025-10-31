import java.util.*;
import java.io.*;
public class Main_20 {

  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    Integer[] arr = new Integer[N];

    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr, Collections.reverseOrder());
    int k = Integer.parseInt(br.readLine());
    int[] dp = new int[k+1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for(int a : arr){
      for(int i = a; i <= k; i++){
        dp[i] = Math.min(dp[i], dp[i-a] + 1);
      }
    }
    System.out.println(dp[k]);
  
}
}