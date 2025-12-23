import java.io.*;
import java.util.*;

public class Main_2589 {
    static int N, M;
    static char[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Node {
        int x, y, d;
        Node(int x, int y, int d) {
            this.x = x; this.y = y; this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) map[i][j] = line.charAt(j);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }

        System.out.println(ans);
    }

    static int bfs(int sx, int sy) {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> q = new ArrayDeque<>();
        visited[sx][sy] = true;
        q.add(new Node(sx, sy, 0));

        int far = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            far = Math.max(far, cur.d);

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 'L') continue;

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, cur.d + 1));
            }
        }

        return far; // (sx,sy)에서 가장 먼 땅까지의 최단거리
    }
}
