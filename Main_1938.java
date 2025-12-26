import java.io.*;
import java.util.*;

/**
 * BOJ 1938 통나무 옮기기
 * - 정석: BFS + 상태(통나무 중심좌표 cx,cy + 방향 dir)
 *   dir = 0 (세로), 1 (가로)
 * - 이동 4방향 + 회전(총 5가지 액션)
 * - visited[cx][cy][dir]로 중복 방지
 */
public class Main_1938 {

    static int N;
    static char[][] map;

    // 4방향 (아래, 오른쪽, 위, 왼쪽) - 순서는 상관없음
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    // BFS 상태: 중심좌표 + 방향 + 이동횟수
    static class State {
        int x, y;   // 중심 좌표
        int dir;    // 0:세로, 1:가로
        int dist;   // 동작 횟수

        State(int x, int y, int dir, int dist) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        map = new char[N][N];

        // B(시작 통나무) 좌표 3개, E(목표 통나무) 좌표 3개 수집
        List<int[]> Bs = new ArrayList<>();
        List<int[]> Es = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'B') Bs.add(new int[]{i, j});
                if (map[i][j] == 'E') Es.add(new int[]{i, j});
            }
        }

        // 시작/목표 상태(중심, 방향) 계산
        State start = buildStateFrom3Cells(Bs);
        State goal = buildStateFrom3Cells(Es);

        int ans = bfs(start, goal);
        System.out.println(ans);
    }

    /**
     * 3개의 칸 좌표로부터 통나무의 중심 좌표와 방향(dir)을 계산한다.
     * - 3칸은 일직선(세로 또는 가로)으로 주어진다.
     * - 중심은 정렬 후 가운데 칸
     */
    static State buildStateFrom3Cells(List<int[]> cells) {
        // x, y 기준으로 정렬하면 가운데가 중심
        cells.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int[] c0 = cells.get(0);
        int[] c1 = cells.get(1);
        int[] c2 = cells.get(2);

        int cx = c1[0];
        int cy = c1[1];

        // 세로면 x가 다르고 y가 동일, 가로면 x가 동일하고 y가 다름
        int dir;
        if (c0[0] != c2[0]) dir = 0; // 세로
        else dir = 1;               // 가로

        return new State(cx, cy, dir, 0);
    }

    /**
     * BFS로 최소 동작 횟수 탐색
     */
    static int bfs(State start, State goal) {
        boolean[][][] visited = new boolean[N][N][2];
        ArrayDeque<State> q = new ArrayDeque<>();

        visited[start.x][start.y][start.dir] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            State cur = q.poll();

            // 목표 도달 체크
            if (cur.x == goal.x && cur.y == goal.y && cur.dir == goal.dir) {
                return cur.dist;
            }

            // 1) 4방향 이동
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!inRange(nx, ny)) continue;
                if (visited[nx][ny][cur.dir]) continue;

                // 이동했을 때 통나무 3칸이 모두 유효(범위 내 & '0'/'B'/'E')인지 체크
                if (!canMove(nx, ny, cur.dir)) continue;

                visited[nx][ny][cur.dir] = true;
                q.offer(new State(nx, ny, cur.dir, cur.dist + 1));
            }

            // 2) 회전
            int ndir = 1 - cur.dir;
            if (!visited[cur.x][cur.y][ndir] && canRotate(cur.x, cur.y)) {
                visited[cur.x][cur.y][ndir] = true;
                q.offer(new State(cur.x, cur.y, ndir, cur.dist + 1));
            }
        }

        // 도달 불가
        return 0;
    }

    /**
     * 중심(cx,cy), 방향(dir)인 통나무가 그 위치에 "존재 가능"한지 체크.
     * - 세로(dir=0): (cx-1,cy), (cx,cy), (cx+1,cy)
     * - 가로(dir=1): (cx,cy-1), (cx,cy), (cx,cy+1)
     * - 벽('1')이면 불가, 범위 밖이면 불가
     */
    static boolean canMove(int cx, int cy, int dir) {
        int[][] parts = getParts(cx, cy, dir);

        for (int[] p : parts) {
            int x = p[0], y = p[1];
            if (!inRange(x, y)) return false;
            if (map[x][y] == '1') return false; // 장애물
        }
        return true;
    }

    /**
     * 회전 가능 조건:
     * - 중심(cx,cy)을 기준으로 3x3 영역이 전부 범위 내이고 장애물('1')이 없어야 함.
     */
    static boolean canRotate(int cx, int cy) {
        for (int i = cx - 1; i <= cx + 1; i++) {
            for (int j = cy - 1; j <= cy + 1; j++) {
                if (!inRange(i, j)) return false;
                if (map[i][j] == '1') return false;
            }
        }
        return true;
    }

    /**
     * 중심과 방향으로 통나무의 3칸 좌표 반환
     */
    static int[][] getParts(int cx, int cy, int dir) {
        if (dir == 0) { // 세로
            return new int[][]{
                    {cx - 1, cy},
                    {cx, cy},
                    {cx + 1, cy}
            };
        } else { // 가로
            return new int[][]{
                    {cx, cy - 1},
                    {cx, cy},
                    {cx, cy + 1}
            };
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
