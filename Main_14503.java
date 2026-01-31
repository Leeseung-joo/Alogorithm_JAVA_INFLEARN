import java.util.*;
import java.io.*;

public class Main_14503 {
    static int N, M;
    static int[][] map;
    static int cnt = 0;

    // 0:북, 1:동, 2:남, 3:서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleanRoom(x, y, d);
        System.out.println(cnt);
    }

    static void cleanRoom(int x, int y, int d) {
        // 1) 현재 칸 청소
        if (map[x][y] == 0) {
            map[x][y] = 2;
            cnt++;
        }

        // 2) 왼쪽으로 4번 회전하며 탐색
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4; // 왼쪽 회전
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 청소되지 않은 빈칸이면 이동
            if (inRange(nx, ny) && map[nx][ny] == 0) {
                cleanRoom(nx, ny, d);
                return; // 이동했으면 여기서 끝 (그 자리에서 다시 시작해야 함)
            }
        }

        // 3) 4방향 모두 불가 -> 후진 (방향 유지)
        int backDir = (d + 2) % 4;
        int bx = x + dx[backDir];
        int by = y + dy[backDir];

        // 뒤가 벽이면 종료
        if (!inRange(bx, by) || map[bx][by] == 1) return;

        // 뒤가 벽이 아니면 후진 (방향은 그대로 d)
        cleanRoom(bx, by, d);
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
