import java.util.*;
import java.io.*;

public class Main_14499 {
  static int[] dice;
  static int[] dx = {0,0,-1,1};
  static int[] dy = {1,-1,0,0};
  static int N;
  static int M;
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] arr = new int[N][M];
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++){
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dice = new int[7];

    int[] command = new int[K];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < K; i++){
      command[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 0; i < K; i++){
      int dir = command[i];

      int nx = x +dx[dir-1];
      int ny = y +dy[dir-1];

      if(nx < 0 || nx >= N || ny < 0 || ny >= M){
        continue;
    }

    // 2. 이동
    x = nx;
    y = ny;

      if(command[i] == 1) east();
      else if(command[i] == 2) west();
      else if(command[i] ==3 ) north();
      else south();

      if(arr[x][y] == 0){
        arr[x][y] = dice[6];
      }else{
        dice[6] = arr[x][y];
        arr[x][y] = 0;
      }
      System.out.println(dice[1]);
    }


    




  }

  static void east(){
    int temp = dice[1];
    dice[1] = dice[4];
    dice[4] = dice[6];
    dice[6] = dice[3];
    dice[3] = temp;
  }
  static void west() {
    int temp = dice[1];
    dice[1] = dice[3];
    dice[3] = dice[6];
    dice[6] = dice[4];
    dice[4] = temp;
}

static void north() {
    int temp = dice[1];
    dice[1] = dice[5];
    dice[5] = dice[6];
    dice[6] = dice[2];
    dice[2] = temp;
}

static void south() {
    int temp = dice[1];
    dice[1] = dice[2];
    dice[2] = dice[6];
    dice[6] = dice[5];
    dice[5] = temp;
}
  
}
