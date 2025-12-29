import java.util.*;
import java.io.*;

public class Main_1726 {
    static int M; // 세로(행)
    static int N; // 가로(열)
    static int[][] map;

    // 방향: 문제 입력 (1:동, 2:서, 3:남, 4:북)
    // 0은 안 씀
    // (x,y)는 0-index: x=행, y=열
    static final int EAST = 1, WEST = 2, SOUTH = 3, NORTH = 4;

    static int[] dx = new int[5];
    static int[] dy = new int[5];

    static class State {
        int x, y, dir, cnt;
        State(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static State start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방향별 이동 벡터 세팅 (입력 방향 그대로 사용)
        dx[EAST] = 0;  dy[EAST] = 1;
        dx[WEST] = 0;  dy[WEST] = -1;
        dx[SOUTH] = 1; dy[SOUTH] = 0;
        dx[NORTH] = -1;dy[NORTH] = 0;

        // 시작 (입력은 1-index이므로 0-index로 통일)
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken()) - 1;
        int sy = Integer.parseInt(st.nextToken()) - 1;
        int sd = Integer.parseInt(st.nextToken());
        start = new State(sx, sy, sd, 0);

        // 도착 (입력은 1-index이므로 0-index로 통일)
        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;
        int ed = Integer.parseInt(st.nextToken());
        end = new State(ex, ey, ed, 0);

        System.out.println(bfs());
    }

    static int bfs() {
        // visited[x][y][dir] : (x,y)에서 dir 방향을 바라보는 상태를 방문했는지
        boolean[][][] visited = new boolean[M][N][5];

        ArrayDeque<State> q = new ArrayDeque<>();
        q.add(start);
        visited[start.x][start.y][start.dir] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            // BFS 특성상, 처음 도착이 최단거리
            if (cur.x == end.x && cur.y == end.y && cur.dir == end.dir) {
                return cur.cnt;
            }

            // 1) 회전: 제자리에서 방향만 바뀜 (비용 +1)
            // - left
            int leftDir = turnLeft(cur.dir);
            if (!visited[cur.x][cur.y][leftDir]) {
                visited[cur.x][cur.y][leftDir] = true;
                q.add(new State(cur.x, cur.y, leftDir, cur.cnt + 1));
            }
            // - right
            int rightDir = turnRight(cur.dir);
            if (!visited[cur.x][cur.y][rightDir]) {
                visited[cur.x][cur.y][rightDir] = true;
                q.add(new State(cur.x, cur.y, rightDir, cur.cnt + 1));
            }

            // 2) 전진: Go k (1~3) (비용 +1)
            // - 중간에 벽(1) 만나면 그 이상 진행 불가 => break
            for (int k = 1; k <= 3; k++) {
                int nx = cur.x + dx[cur.dir] * k;
                int ny = cur.y + dy[cur.dir] * k;

                if (!inRange(nx, ny)) break;     // 범위 밖이면 더 멀리는 의미 없음
                if (map[nx][ny] == 1) break;     // 벽 만나면 그 이후 k도 불가

                if (!visited[nx][ny][cur.dir]) {
                    visited[nx][ny][cur.dir] = true;
                    q.add(new State(nx, ny, cur.dir, cur.cnt + 1));
                }
            }
        }

        return -1; // 이 문제는 보통 도달 가능하지만, 안전하게
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    // 문제 방향 체계(1동 2서 3남 4북) 기준으로 좌회전/우회전 정의
    static int turnLeft(int d) {
        // 동->북, 북->서, 서->남, 남->동
        if (d == EAST) return NORTH;
        if (d == NORTH) return WEST;
        if (d == WEST) return SOUTH;
        return EAST; // SOUTH
    }

    static int turnRight(int d) {
        // 동->남, 남->서, 서->북, 북->동
        if (d == EAST) return SOUTH;
        if (d == SOUTH) return WEST;
        if (d == WEST) return NORTH;
        return EAST; // NORTH
    }
}
