import java.util.*;
import java.io.*;

public class Main_7576 {
  static int M, N;
  static int[][] box;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      M = Integer.parseInt(st.nextToken()); // 가로
      N = Integer.parseInt(st.nextToken()); // 세로

      box = new int[N][M];
      ArrayDeque<int[]> q = new ArrayDeque<>();

      for (int i = 0; i < N; i++) {
          st = new StringTokenizer(br.readLine());
          for (int j = 0; j < M; j++) {
              box[i][j] = Integer.parseInt(st.nextToken());
              if (box[i][j] == 1) {
                  q.offer(new int[]{i, j}); // 익은 토마토 전부 큐에
              }
          }
      }

      while(!q.isEmpty()){
        int[] cur = q.poll();
        int x = cur[0], y = cur[1];

        for(int dir = 0; dir < 4; dir++){
          int nx = x + dx[dir];
          int ny = y + dy[dir];

          if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
          if (box[nx][ny] != 0) continue; // 0(안 익음)만 퍼뜨림

          box[nx][ny] = box[x][y] + 1;
          q.offer(new int[]{nx,ny});
        }
      }


      int maxDay = 1;
      for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
          if(box[i][j] == 0){
            System.out.println(-1);
            return;
          }
          maxDay = Math.max(maxDay, box[i][j]);
        }
      }
      System.out.println(maxDay - 1); 
    }
}