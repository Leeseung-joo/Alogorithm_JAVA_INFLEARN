import java.util.*;
import java.io.*;
public class Main_1679 {
  static int N;
  static int K;
  static int[] dist = new int[100001];
  static boolean[] visited = new boolean[100001];

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    bfs(N);
    System.out.println(dist[K]);
  }

  static void bfs(int start){
    ArrayDeque<Integer> dq = new ArrayDeque<>();
    dq.offer(N);
    dist[N] = 0;

    int[] dx = {1,-1};

    
    while(!dq.isEmpty()){
      int current = dq.poll();

      if(current == K) return;

      for(int i = 0; i < 2; i++){
        int nx = current + dx[i];

        if(nx < 0 || nx > 100000) continue;
        if(visited[nx]) continue;

        dq.offer(nx);
        visited[nx] = true;
        dist[nx] = dist[current] + 1;
      }

      int nx = current * 2;
      if(nx < 0 || nx > 100000) continue;
        if(visited[nx]) continue;

        dq.offer(nx);
        visited[nx] = true;
        dist[nx] = dist[current] + 1;



    


    }
  }
  
}
