import java.io.*;
import java.util.*;

public class Main {

    static int r, c, ex, ey;       // ex: row, ey: col (너 스타일)
    static char[][] map;
    static boolean[][] check;

    static Queue<int[]> wq, sq;
    // x=row, y=col 기준의 이동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        wq = new LinkedList<>();
        sq = new LinkedList<>();

        int sx = -1, sy = -1;   // sx: row, sy: col

        for (int x = 0; x < r; x++) {
            String line = br.readLine();
            for (int y = 0; y < c; y++) {
                map[x][y] = line.charAt(y);

                if (map[x][y] == 'L') {
                    if (sx == -1) {     // 첫 백조
                        sx = x;
                        sy = y;
                    } else {            // 둘째 백조
                        ex = x;
                        ey = y;
                    }
                    map[x][y] = '.';    // 백조 위치는 물로 취급
                }

                if (map[x][y] == '.') {
                    wq.add(new int[]{x, y}); // x=row, y=col
                }
            }
        }

        check = new boolean[r][c];
        sq.add(new int[]{sx, sy});
        check[sx][sy] = true;

        int time = 0;
        while (true) {
            if (move()) break;
            melting();
            time++;
        }

        System.out.println(time);
    }

    // 백조 이동 BFS (오늘 가능한 곳 sq, 내일 예약 q)
    static boolean move() {
        Queue<int[]> next = new LinkedList<>();

        while (!sq.isEmpty()) {
            int[] p = sq.poll();
            int x = p[0], y = p[1]; // x=row, y=col

            if (x == ex && y == ey) return true;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if (check[nx][ny]) continue;

                check[nx][ny] = true;

                if (map[nx][ny] == '.') {
                    sq.add(new int[]{nx, ny});     // 오늘 계속 탐색
                } else if (map[nx][ny] == 'X') {
                    next.add(new int[]{nx, ny});   // 내일 녹으면 갈 곳 예약
                }
            }
        }

        sq = next;
        return false;
    }

    // 빙판 녹이기 BFS (오늘 물 wq 기준으로 인접 X를 녹여서 다음 날 물로)
    static void melting() {
        int size = wq.size();

        for (int i = 0; i < size; i++) {
            int[] p = wq.poll();
            int x = p[0], y = p[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                if (map[nx][ny] == 'X') {
                    map[nx][ny] = '.';
                    wq.add(new int[]{nx, ny}); // 새로 물 된 칸 → 내일 확장 후보
                }
            }
        }
    }
}
