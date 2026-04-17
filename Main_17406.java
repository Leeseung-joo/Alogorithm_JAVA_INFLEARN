import java.util.*;
import java.io.*;
public class Main_17406 {
  static int[][] map;
  static int[][] rotate;
  static int result = Integer.MAX_VALUE;
  static int K;
  static boolean[] visited;
  static int N;
  static int M;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    rotate = new int[K][3];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0; i < K; i++){
      st = new StringTokenizer(br.readLine());

      rotate[i][0] = Integer.parseInt(st.nextToken());
      rotate[i][1] = Integer.parseInt(st.nextToken());
      rotate[i][2] = Integer.parseInt(st.nextToken());
    }
    visited =  new boolean[K];

    dfs(0,0,new int[N][M]);


  }

  static void dfs(int depth, int[][] current) {
    if (depth == K) {
        result = Math.min(result, getMinRowSum(current));
        return;
    }

    for (int i = 0; i < K; i++) {
        if (visited[i]) continue;

        visited[i] = true;
        int[][] next = copyArray(current);
        rotate(next, rotate[i]);   // i번째 회전 연산 적용
        dfs(depth + 1, next);
        visited[i] = false;
    }
}

static int[][] copyArray(int[][] current){
  int[][] next = new int[N][M];

  for(int i = 0; i < N; i++){
    next[i] = current[i].clone();
  }
  return next;
}

static void roate(int[][] arr, int[] op){
  int r = op[0];
  int c = op[1];
  int s = op[2];

  for(int layer = 1; layer <= s; layer++){

    int top = r - layer - 1;
        int left = c - layer - 1;
        int bottom = r + layer - 1;
        int right = c + layer - 1;

        int temp = arr[top][left];

        // 왼쪽 열 ↑
        for (int i = top; i < bottom; i++) {
          arr[i][left] = arr[i + 1][left];
      }

      // 아래 행 ←
      for (int j = left; j < right; j++) {
          arr[bottom][j] = arr[bottom][j + 1];
      }

      // 오른쪽 열 ↓
      for (int i = bottom; i > top; i--) {
          arr[i][right] = arr[i - 1][right];
      }

      // 위 행 →
      for (int j = right; j > left + 1; j--) {
          arr[top][j] = arr[top][j - 1];
      }

      arr[top][left + 1] = temp;
  }




}
static int getMinRowSum(int[][] arr) {
  int min = Integer.MAX_VALUE;

  for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = 0; j < arr[i].length; j++) {
          sum += arr[i][j];
      }
      min = Math.min(min, sum);
  }

  return min;
}
  
}
