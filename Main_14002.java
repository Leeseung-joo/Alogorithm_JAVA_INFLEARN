import java.util.*;
import java.io.*;

public class Main_14002 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];
    int[] dp = new int[N];
    int[] prev = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

    Arrays.fill(dp, 1);
    Arrays.fill(prev, -1);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
    }

    int idx = 0;
    for (int i = 1; i < N; i++) if (dp[i] > dp[idx]) idx = i;

    Stack<Integer> s = new Stack<>();
    while (idx != -1) {
      s.push(arr[idx]);
      idx = prev[idx];
    }

    StringBuilder sb = new StringBuilder();
    sb.append(s.size()).append('\n');
    while (!s.isEmpty()) sb.append(s.pop()).append(' ');
    System.out.println(sb);
  }
}
