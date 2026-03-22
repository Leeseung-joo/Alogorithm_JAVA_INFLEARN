import java.util.*;
import java.io.*;

public class Main_14442 {
    static int[][] map;
    static boolean[][][] visited;
    static int N;
    static int M;
    static int K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Point {
        int x, y;
        int k;   // 남은 벽 부수기 횟수
        int cnt; // 현재까지 이동 거리

        Point(int x, int y, int k, int cnt) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    static int bfs(int x, int y) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.offer(new Point(x, y, K, 1));
        visited[x][y][K] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == N - 1 && p.y == M - 1) {
                return p.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (!isRange(nx, ny)) continue;

                // 빈 칸으로 이동
                if (map[nx][ny] == 0 && !visited[nx][ny][p.k]) {
                    visited[nx][ny][p.k] = true;
                    q.offer(new Point(nx, ny, p.k, p.cnt + 1));
                }

                // 벽을 부수고 이동
                else if (map[nx][ny] == 1 && p.k > 0 && !visited[nx][ny][p.k - 1]) {
                    visited[nx][ny][p.k - 1] = true;
                    q.offer(new Point(nx, ny, p.k - 1, p.cnt + 1));
                }
            }
        }

        return -1;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}