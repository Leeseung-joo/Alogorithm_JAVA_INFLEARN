import java.util.*;
import java.io.*;

public class Main_17142 {
    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<int[]> virusList = new ArrayList<>();
    static boolean[] selected;
    static int answer = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if (num == 2) {
                    virusList.add(new int[]{i, j});
                }
            }
        }

        selected = new boolean[virusList.size()];

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int start, int length) {
        if (length == M) {
            bfs();
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            selected[i] = true;
            dfs(i + 1, length + 1);
            selected[i] = false;
        }
    }

    static void bfs() {
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N];

        ArrayDeque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                int[] arr = virusList.get(i);
                q.offer(new int[]{arr[0], arr[1]});
                visited[arr[0]][arr[1]] = true;
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = current[0] + dx[k];
                int ny = current[1] + dy[k];

                if (!inRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                distance[nx][ny] = distance[current[0]][current[1]] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        int maxTime = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    if (!visited[i][j]) return;
                    maxTime = Math.max(maxTime, distance[i][j]);
                }
            }
        }

        if (answer == -1) answer = maxTime;
        else answer = Math.min(answer, maxTime);
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}