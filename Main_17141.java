import java.util.*;
import java.io.*;

public class Main_17141 {
    static int[][] map;
    static ArrayList<int[]> virusList = new ArrayList<>();
    static int N;
    static int M;
    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {   // N x N 이어야 함
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virusList.add(new int[]{i, j});
                }
            }
        }

        selected = new boolean[virusList.size()];

        select(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void select(int start, int count) {
        if (count == M) {   // M개 선택
            bfs();
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            selected[i] = true;
            select(i + 1, count + 1);
            selected[i] = false;
        }
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];

        for (int i = 0; i < virusList.size(); i++) {
            if (selected[i]) {
                int[] v = virusList.get(i);
                q.offer(new int[]{v[0], v[1]});
                visited[v[0]][v[1]] = true;
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) continue;
                if (map[nx][ny] == 1) continue; // 벽
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        int maxTime = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 벽은 제외
                if (map[i][j] == 1) continue;

                // 빈 칸 또는 비활성 바이러스 칸까지 모두 도달해야 함
                if (!visited[i][j]) return;

                maxTime = Math.max(maxTime, dist[i][j]);
            }
        }

        answer = Math.min(answer, maxTime);
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}