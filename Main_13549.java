import java.util.*;
import java.io.*;
public class Main_13549 {

  static int[] map = new int[100001];
  static int cnt = 0;
  static int[] dx = {1,-1};
  

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    for(int i = 0; i <= 100000; i++){
      map[i] = Integer.MAX_VALUE;
    }
    map[N] = 0;

    bfs(N, K);
    System.out.println(map[K]);

  }

  static void bfs(int n, int k){
    if(n == k) return;
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.add(new int[]{n,0});

    while(!q.isEmpty()){
      int[] current = q.poll();

      if(current[0]== k);

      for(int i = 0; i < 2; i++){
        int nx = current[0] + dx[i];

        if(nx < 0 || nx >  100000) continue;

        if(map[nx] > current[1]+1){
          map[nx] = current[1]+1;
          q.add(new int[]{nx,map[nx]});
        }

      }
      
      int nx = current[0] * 2;
      if(nx < 0 || nx >  100000) continue;
      if(map[nx] > current[1]){
        map[nx] = current[1];
        q.addFirst(new int[]{nx,map[nx]});
      }

    }



  }
  
}
