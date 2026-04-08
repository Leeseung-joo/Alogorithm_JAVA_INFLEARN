import java.util.*;
import java.io.*;

public class Main_15683 {
    static int N, M;
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    // 상 우 하 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // CCTV 종류별 가능한 방향 조합
    static int[][][] dir = {
        {},
        {{0}, {1}, {2}, {3}},                        // 1번
        {{0, 2}, {1, 3}},                            // 2번
        {{0, 1}, {1, 2}, {2, 3}, {3, 0}},            // 3번
        {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번
        {{0, 1, 2, 3}}                               // 5번
    };

    static class CCTV {
        int x, y, type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);

        System.out.println(answer);
    }

    // CCTV를 하나씩 보면서 방향 조합 완전탐색
    static void dfs(int depth, int[][] office) {
        if (depth == cctvs.size()) {
            answer = Math.min(answer, countBlindSpot(office));
            return;
        }

        CCTV cctv = cctvs.get(depth);

        for (int[] directions : dir[cctv.type]) {
            int[][] copy = copyMap(office);

            // 현재 CCTV가 바라보는 방향들 처리
            for (int d : directions) {
                watch(copy, cctv.x, cctv.y, d);
            }

            dfs(depth + 1, copy);
        }
    }

    // 한 방향으로 감시 영역 표시
    static void watch(int[][] office, int x, int y, int direction) {
        int nx = x;
        int ny = y;

        while (true) {
            nx += dx[direction];
            ny += dy[direction];

            if (!inRange(nx, ny) || office[nx][ny] == 6) break;

            if (office[nx][ny] == 0) {
                office[nx][ny] = -1; // 감시된 칸 표시
            }
        }
    }

    // 사각지대 개수 세기
    static int countBlindSpot(int[][] office) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    // 맵 복사
    static int[][] copyMap(int[][] office) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = office[i].clone();
        }
        return copy;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}