import java.util.*;
import java.io.*;
public class Main_2178 {

  static int[][] map;
  static int cnt = 1;
  static boolean[][] visited;
  static int[] dx = {1,0,-1,0};
  static int[] dy = {0,1,0,-1};
  static int N;
  static int M;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for(int i = 0; i < N; i++){
      String line = br.readLine();
      for(int j = 0; j < M; j++){
        map[i][j] = line.charAt(j) - '0';
      }
    }
    visited = new boolean[N][M];

    int len = bfs(0,0,1);
    System.out.println(len);
  }

  static int bfs(int x,int y,int length){

    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{x,y,length});

    visited[x][y] = true;

    while(!q.isEmpty()){
      
      int[] p = q.poll();

      if(p[0] == N-1 && p[1] == M-1) return p[2]; 

      for(int i = 0; i< 4; i++){
        int nx = p[0] + dx[i];
        int ny = p[1] + dy[i];

        if(!inRange(nx,ny)) continue;

        if(!visited[nx][ny] && map[nx][ny] == 1){
          q.offer(new int[]{nx,ny,p[2]+1});
          visited[nx][ny] = true;
        }
      }
     
    }
    return -1;
  }

  static boolean inRange(int x, int y){
    return x >= 0 && x <= N-1 && y >= 0 && y <= M-1;
  }
  
}
