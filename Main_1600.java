import java.util.*;
import java.io.*;

public class Main_1600 {

    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    // 원숭이 이동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 말 이동
    static int[] horseDx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] horseDy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static class Node {
        int x;
        int y;
        int horseUsed;
        int dist;

        Node(int x, int y, int horseUsed, int dist) {
            this.x = x;
            this.y = y;
            this.horseUsed = horseUsed;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == H - 1 && cur.y == W - 1) {
                return cur.dist;
            }

            // 원숭이 이동
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (!isInRange(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny][cur.horseUsed]) continue;

                visited[nx][ny][cur.horseUsed] = true;
                q.offer(new Node(nx, ny, cur.horseUsed, cur.dist + 1));
            }

            // 말 이동
            if (cur.horseUsed < K) {
                for (int dir = 0; dir < 8; dir++) {
                    int nx = cur.x + horseDx[dir];
                    int ny = cur.y + horseDy[dir];

                    if (!isInRange(nx, ny)) continue;
                    if (map[nx][ny] == 1) continue;
                    if (visited[nx][ny][cur.horseUsed + 1]) continue;

                    visited[nx][ny][cur.horseUsed + 1] = true;
                    q.offer(new Node(nx, ny, cur.horseUsed + 1, cur.dist + 1));
                }
            }
        }

        return -1;
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}