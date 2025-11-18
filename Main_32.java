import java.io.*;
import java.util.*;

public class Main_32 {
  static int[][] map;
  static int[] archers = new int[3];
  static int N;
  static int M;
  static int D;
  static int answer = 0;
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());

    map = new int[N][M];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    combination(0,0);

    System.out.println(answer);


  }

  static void combination(int start, int depth){ //시작
    if(depth == 3){
      simulate();
      return;
    }

    for(int i = start; i < M; i++){
      archers[depth] = i;
      combination(i+1, depth+1);
    }
  }

  static void simulate(){

    int[][] newMap = copyMap(map);
    int kill = 0;

    while(enemyExists(newMap)){

      List<int[]> targets = new ArrayList<>(); //이번 턴 궁수들이 쏠 적 찾기

      for(int i = 0; i < 3; i++){
        int point = archers[i];
        int[] t = findTarget(newMap, point);

        if(t!=null) targets.add(t);
        
      }
 
      for(int[] p : targets){ //찾은 적 제거(중복 제거)
        int r = p[0];
        int c = p[1];

        if(newMap[r][c] == 1){
          newMap[r][c] = 0;
          kill++;
        }
      }

      moveEnemies(newMap);
    }
    answer = Math.max(answer, kill);
  }

  static int[][] copyMap(int[][] map){
    int N = map.length;
    int M = map[0].length;

    int[][] newMap = new int[N][M];

    for(int i = 0; i < N; i++){
      System.arraycopy(map[i], 0, newMap[i], 0, M);
    }
    return newMap;
  }

  static int[] findTarget(int[][] board, int archerCol){
    
    int minDist = Integer.MAX_VALUE;
    int targetR = -1;
    int targetC = -1;

    for(int r = 0; r < N; r++){
      for(int c = 0; c < M; c++){
        if(board[r][c] == 1){
          int dist = Math.abs(N-r) + Math.abs(archerCol - c);
          if(dist <= D){

            if(dist < minDist){
              minDist = dist;
              targetR = r;
              targetC = c;
            }
            else if(dist == minDist){
              if(c < targetC){
                targetR = r;
                targetC = c;
              }
            }
          }

        }
      }
    }
    if(targetR == -1) return null;
    return new int[]{targetR, targetC};
  }

  static void moveEnemies(int[][] board){
    for(int r = N-1; r >0; r--){
      for(int c = 0; c < M; c++){
        board[r][c] = board[r-1][c];
      }
    }

    for(int c = 0; c < M; c++){
      board[0][c] = 0;
    }
  }
  static boolean enemyExists(int[][] board){
    for(int[] row : board){
      for(int v : row){
        if(v == 1) return true;
      }
    }
    return false;
  }
  
}
