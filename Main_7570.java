import java.util.*;
import java.io.*;
public class Main_7570 {
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
          int x = arr[i];
          dp[x] = dp[x - 1] + 1;
          max = Math.max(max, dp[x]);
      }
      System.out.println(N - max);
  } //움직여야하는 수 -> N- 안움직여야하는수(본인의 자리인 애)
}


//줄 서있는 어린이 중 한명을 맨 앞이나 맨 뒤로 보내야함