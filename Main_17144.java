import java.io.*;
import java.util.*;

public class Main_17144 {
    static int R;
    static int C;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int top = -1;
    static int bottom = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    if (top == -1) top = i;
                    else bottom = i;
                }
            }
        }

        while (T-- > 0) {

            int[][] copy = new int[R][C];

            // 공기청정기 위치 유지
            copy[top][0] = -1;
            copy[bottom][0] = -1;

            // 1. 확산
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        int cnt = 0;
                        int num = map[i][j] / 5;

                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (!inRange(nx, ny) || map[nx][ny] == -1) continue;

                            copy[nx][ny] += num;
                            cnt++;
                        }

                        copy[i][j] += map[i][j] - cnt * num;
                    }
                }
            }

            map = copy;

            // 2. 공기청정기 작동
            operate();
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    static void operate() {
        // 위쪽 공기청정기 - 반시계

        // 왼쪽 열 위로
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }

        // 윗줄 왼쪽으로
        for (int j = 0; j < C - 1; j++) {
            map[0][j] = map[0][j + 1];
        }

        // 오른쪽 열 아래로
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        // 청정기 행 오른쪽으로
        for (int j = C - 1; j > 1; j--) {
            map[top][j] = map[top][j - 1];
        }

        map[top][1] = 0;
        map[top][0] = -1;

        // 아래쪽 공기청정기 - 시계

        // 왼쪽 열 아래로
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        // 아랫줄 왼쪽으로
        for (int j = 0; j < C - 1; j++) {
            map[R - 1][j] = map[R - 1][j + 1];
        }

        // 오른쪽 열 위로
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        // 청정기 행 오른쪽으로
        for (int j = C - 1; j > 1; j--) {
            map[bottom][j] = map[bottom][j - 1];
        }

        map[bottom][1] = 0;
        map[bottom][0] = -1;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}