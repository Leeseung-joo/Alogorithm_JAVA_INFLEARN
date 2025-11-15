import java.util.*;
import java.io.*;
public class Main_31 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] rank = new int[N+1][M];//i번째학생의 j번째 시험의 등수 

    for(int i = 0; i < M; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j <= N; j++){
      int student = Integer.parseInt(st.nextToken());
      rank[student][i] = j;
    }
  }
  int answer = 0;
  for(int i = 1; i <=N; i++){
    for(int j = 1; j <= N; j++){
      if(i == j) continue;

      boolean ok = true;
      for(int k = 0; k < M; k++){
        if(rank[i][k] >= rank[j][k]){
          ok = false;
          break;
        }
      }
      if(ok) answer++;
    
    }
  }
  System.out.println(answer);

  }
}
