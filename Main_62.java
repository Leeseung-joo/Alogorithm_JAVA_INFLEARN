import java.util.*;
import java.io.*;
public class Main_62 {

  static int cnt = Integer.MAX_VALUE;
  static int N, M;
  static Integer[] coins;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    coins = new Integer[N];
    for(int i = 0; i < N; i++){
      coins[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(coins, Collections.reverseOrder());
    M = Integer.parseInt(br.readLine());
    
    dfs(0,0,M);
    System.out.println(cnt);
    

  }

  static void dfs(int idx, int number, int remain){
    if(number >= cnt) return; // 이미 최적보다 크면 볼 필요 없음

    if(remain == 0){
      cnt = Math.min(cnt, number);
      return;
    }

    if(idx == N) return;
  
    int coin = coins[idx];
    int maxCount = remain / coin; //사용할 수 있는 최대 개수

    for(int i = maxCount; i>= 0; i--){
      dfs(idx+1, number + i, remain - i*coin);
    }
}
}

//coins = Arrays.stream(coins).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();