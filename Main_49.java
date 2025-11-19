import java.util.*;
import java.io.*;

public class Main_49 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] map = new int[N][N];
    StringTokenizer st;

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int M = Integer.parseInt(br.readLine());
    int[] moves = new int[M];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < M; i++){
      moves[i] = Integer.parseInt(st.nextToken());
    }
    ArrayDeque<Integer> stack = new ArrayDeque<>();
//map[j][i-1], j가 움직임
    int cnt = 0;
    for(int i = 0; i < moves.length; i++){
      for(int j = 0; j < N; j++){
        if(map[j][moves[i] -1] != 0){
          if(!stack.isEmpty() && stack.peek() == map[j][moves[i] -1]){
            stack.pop();
            cnt += 2;
            map[j][moves[i] -1] = 0;
            break;


          }else{
          stack.push(map[j][moves[i] -1]);
          map[j][moves[i] -1] = 0;
          break;
          }
        }
      }
    }
    System.out.println(cnt);
  }
  
}
