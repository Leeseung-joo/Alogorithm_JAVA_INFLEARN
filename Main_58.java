import java.util.*;
import java.io.*;

public class Main_58 {
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int S = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    int[] dx = new int[]{1,-1,5};

    ArrayDeque<Integer> q = new ArrayDeque<>();
    boolean[] visited = new boolean[100001];
    q.add(S);
    int level = 0;
    visited[S] = true;
    while(!q.isEmpty()){
      int size = q.size();
      for(int j = 0; j < size; j++){
      int current = q.poll();

      if(current == E){
       System.out.println(level);
       return;
      }
      for(int i = 0; i < 3; i++){
        int nx = current + dx[i];

        if(nx < 1 || nx > 10000 || visited[nx]) continue;
        q.offer(nx);
        visited[nx] = true;
      }
      
    }
    level++;
  }
  }
  
}
