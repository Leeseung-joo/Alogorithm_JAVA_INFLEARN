import java.util.*;
import java.io.*;

public class Main_2178 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dis;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M];
        dis = new int[N][M];

        bfs(0, 0);

        System.out.println(dis[N-1][M-1]);
    }

    static void bfs(int x, int y) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});

        visited[x][y] = true;
        dis[x][y] = 1; // 시작점

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {

                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!inRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                dis[nx][ny] = dis[cur[0]][cur[1]] + 1;

                q.offer(new int[]{nx, ny});
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}