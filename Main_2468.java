import java.util.*;
import java.io.*;

public class Main_2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int maxLevel = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxLevel = Math.max(maxLevel, map[i][j]);
            }
        }

        int answer = 1; // h=0에서 최소 1개일 수 있으니 기본 1
        for (int h = 0; h <= maxLevel; h++) {
            visited = new boolean[N][N];
            int cnt = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c] && map[r][c] > h) {
                        cnt++;
                        dfs(r, c, h);
                    }
                }
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }

    static void dfs(int r, int c, int h) {
        visited[r][c] = true;

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (visited[nr][nc]) continue;
            if (map[nr][nc] <= h) continue; // 잠긴 곳

            dfs(nr, nc, h);
        }
    }
}
