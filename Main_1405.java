import java.io.*;
import java.util.*;

public class Main_1405 {
    static int N;
    static double[] p = new double[4]; // 동 서 남 북 확률 (0~1)
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};   // 동, 서, 남, 북
    static int[] dy = {1, -1, 0, 0};

    static double ans = 0.0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            p[i] = Integer.parseInt(st.nextToken()) / 100.0; // 퍼센트 -> 확률
        }

        // N번 이동이면 좌표가 최대 N만큼 뻗으니까 (2N+1) 격자면 안전
        int size = 2 * N + 1;
        visited = new boolean[size][size];

        int start = N; // 중앙
        visited[start][start] = true;

        dfs(start, start, 0, 1.0);

        System.out.println(ans);
    }

    /**
     * (x,y) 현재 위치
     * cnt: 지금까지 이동 횟수
     * prob: 지금까지 경로가 만들어질 확률(방향확률의 곱)
     */
    static void dfs(int x, int y, int cnt, double prob) {
        // N번 다 움직였으면 "단순 경로 하나" 완성 -> 확률 누적
        if (cnt == N) {
            ans += prob;
            return;
        }

        // 4방향으로 다음 칸 시도
        for (int dir = 0; dir < 4; dir++) {
            if (p[dir] == 0.0) continue; // 확률 0인 방향은 볼 필요 없음

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 재방문이면 단순 경로가 아니므로 제외
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1, prob * p[dir]);
            visited[nx][ny] = false;
        }
    }
}
