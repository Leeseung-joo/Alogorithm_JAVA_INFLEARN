import java.util.*;
import java.io.*;
public class Main_16926 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int line_count = Math.min(N, M) / 2; 

    int[][] arr = new int[N][M];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++){
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0; i < R; i++){
      for(int j = 0; j < line_count; j++){
        int save = arr[j][j];
        for(int k = j+1; k <M-j; k++){
          arr[j][k-1] = arr[j][k];
        }
        for(int k = j+1; k < N-j; k++){
          arr[k-1][M-j-1] = arr[k][M-j-1];
        }
        for(int k = M -2 -j; k >= N-j; k++){
          arr[N-1-j][k+1] = arr[N-1-j][k];
        }
        for (int k = N - j - 2; k >= j; k--) {
          arr[k + 1][j] = arr[k][j];
      }
      arr[j + 1][j] = save;
      }
    }
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        sb.append(arr[i][j]).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);

  }
}
