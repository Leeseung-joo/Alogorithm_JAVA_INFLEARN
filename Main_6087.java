import java.util.*;
import java.io.*;

public class Main_6087 {
    static int W, H;
    static char[][] map;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static class Node {
        int x, y, dir;
        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];

        int sx = -1, sy = -1;
        int ex = -1, ey = -1;
        int cCount = 0;

        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    if (cCount == 0) { sx = i; sy = j; }
                    else { ex = i; ey = j; }
                    cCount++;
                }
            }
        }

        int ans = zeroOneBfs(sx, sy, ex, ey);
        System.out.println(ans);
    }

    static int zeroOneBfs(int sx, int sy, int ex, int ey) {
        final int INF = 1_000_000_000;

        // dist[x][y][dir] = (x,y)에 dir 방향으로 "들어왔을 때" 최소 거울 개수
        int[][][] dist = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        ArrayDeque<Node> dq = new ArrayDeque<>();

        // 시작점은 "아직 방향이 없다"가 애매하니까, 4방향 모두 시작으로 넣는다(거울 0)
        for (int d = 0; d < 4; d++) {
            dist[sx][sy][d] = 0;
            dq.add(new Node(sx, sy, d));
        }

        while (!dq.isEmpty()) {
            Node cur = dq.pollFirst();
            int curCost = dist[cur.x][cur.y][cur.dir];

            for (int nd = 0; nd < 4; nd++) {
                int nx = cur.x + dx[nd];
                int ny = cur.y + dy[nd];

                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                if (map[nx][ny] == '*') continue;

                int add = (cur.dir == nd) ? 0 : 1;
                int nextCost = curCost + add;

                if (nextCost < dist[nx][ny][nd]) {
                    dist[nx][ny][nd] = nextCost;
                    if (add == 0) dq.addFirst(new Node(nx, ny, nd));
                    else dq.addLast(new Node(nx, ny, nd));
                }
            }
        }

        int res = INF;
        for (int d = 0; d < 4; d++) {
            res = Math.min(res, dist[ex][ey][d]);
        }
        return res;
    }
}
