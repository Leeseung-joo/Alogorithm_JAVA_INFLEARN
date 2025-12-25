import java.util.*;
import java.io.*;
public class Main_13460 {
  static char[][] map;
  static int N,M;

  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  static class State{
    int rx,ry,bx,by,depth;

    State(int rx,int ry, int bx,int by, int depth){
      this.rx = rx;
      this.ry = ry;
      this.bx = bx;
      this.by = by;
      this.depth = depth;
    }
  }

  static class RollResult{
    int x,y;
    int moved;
    boolean inHole;

    RollResult(int x,int y, int moved, boolean inHole){
      this.x = x;
      this.y = y;
      this.moved = moved;
      this.inHole = inHole;
    } 

  }
  static boolean[][][][] visited; //방문 체크: 4차원 상태 방문 여부
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new char[N][M];

    // 빨강/파랑 시작 위치 저장
    int startRx = -1, startRy = -1, startBx = -1, startBy = -1;

    for(int i = 0; i < N; i++){
      String line = br.readLine();
      for(int j = 0; j < M; j++){
        map[i][j] = line.charAt(j);

        if (map[i][j] == 'R') {
          startRx = i;
          startRy = j;
          map[i][j] = '.'; // 보드에는 빈칸으로 바꿔두고 시뮬레이션은 좌표로만 관리
      } else if (map[i][j] == 'B') {
          startBx = i;
          startBy = j;
          map[i][j] = '.';
      }
      }
    }
    System.out.println(bfs(startRx, startRy, startBx, startBy));
  }

  static int bfs(int startRx, int startRy, int startBx, int startBy){
    visited = new boolean[N][M][N][M];

    ArrayDeque<State> q = new ArrayDeque<>();
    q.add(new State(startRx, startRy, startBx, startBy, 0));
    visited[startRx][startRy][startBx][startBy] = true;

    while(!q.isEmpty()){
      State cur = q.poll();

      // 이동 횟수가 10이면 더 확장하지 않음(문제 조건)
      if(cur.depth == 10) continue;

      for(int dir = 0; dir < 4; dir++){

        // 1) 빨강/파랑을 각각 dir 방향으로 굴린다(벽/구멍까지)
                RollResult rRes = roll(cur.rx, cur.ry, dir);
                RollResult bRes = roll(cur.bx, cur.by, dir);

        // 2) 파랑이 구멍에 빠지면 이 방향은 무조건 실패(버림)
         if(bRes.inHole) continue;

         //3) 빨강만 구멍에 빠졌으면 성공
         if (rRes.inHole) return cur.depth + 1;

         if(rRes.x == bRes.x && rRes.y == bRes.y){
          if(rRes.moved > bRes.moved){
            rRes.x -= dx[dir];
            rRes.y -= dy[dir];
          }else{
            bRes.x -= dx[dir];
            bRes.y -= dy[dir];
          }
         }
 // 5) 다음 상태가 방문한 적 없으면 BFS 큐에 추가
 if (!visited[rRes.x][rRes.y][bRes.x][bRes.y]) {
  visited[rRes.x][rRes.y][bRes.x][bRes.y] = true;
  q.add(new State(rRes.x, rRes.y, bRes.x, bRes.y, cur.depth + 1));
}
  
      }















    }
    return -1;

  }

  //현재 방향에서 dir방향으로 계속 이동, 벽이면 멈춤, 구멍을 만나면 즉시 빠짐 처리
  static RollResult roll(int x,int y, int dir){
    int moved = 0;

    while(true){
      int nx = x + dx[dir];
      int ny = y + dy[dir];

      if(map[nx][ny] == '#'){
        return new RollResult(x,y,moved,false);
      }

       // 한 칸 이동
       x = nx;
       y = ny;
       moved++;

       // 구멍이면 빠짐 처리 (구멍 위치 좌표로 반환해도 되고, 여부만 써도 됨)
       if (map[x][y] == 'O') {
           return new RollResult(x, y, moved, true);
       }
    }
  }





  
  
}
