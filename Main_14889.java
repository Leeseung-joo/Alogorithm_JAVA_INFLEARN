import java.util.*;
import java.io.*;

public class Main_14889 {
  static int[][] arr;
  static int N;
  static int minSum = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st;

    arr = new int[N+1][N+1];
    for(int i = 1; i <= N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j <= N; j++){
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    dfs(1, 0, new boolean[N+1]);
    System.out.println(minSum);

  }

  static void dfs(int idx,int cnt, boolean[] selected){
    if(cnt == N/2) {
      int sum = calculate(arr, selected);
      minSum = Math.min(sum, minSum);
      return;
    }
    if(idx > N) return;   //더 이상 볼 사람이 없으므로 종료

    dfs(idx + 1, cnt, selected);
    selected[idx] = true;  //스타트팀
    dfs(idx+1, cnt+1, selected);
    selected[idx] = false; 
  }

static int calculate(int[][] arr, boolean[] selected) {
    int startTeam = 0;
    int linkTeam = 0;

    for (int i = 1; i <= N; i++) {
        for (int j = i + 1; j <= N; j++) {
            if (selected[i] && selected[j]) {
                startTeam += arr[i][j] + arr[j][i];
            } else if (!selected[i] && !selected[j]) {
                linkTeam += arr[i][j] + arr[j][i];
            }
        }
    }

    return Math.abs(startTeam - linkTeam);
}


}
