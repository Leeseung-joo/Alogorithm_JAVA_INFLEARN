import java.util.*;
import java.io.*;

public class Main_14502 {
    static int N, M;
    static int[][] map;
    static List<int[]> empty = new ArrayList<>();
    static List<int[]> virus = new ArrayList<>();
    static int answer = 0;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

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

                if (map[i][j] == 0) {
                    empty.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < empty.size(); i++) {
            for (int j = i + 1; j < empty.size(); j++) {
                for (int k = j + 1; k < empty.size(); k++) {

                    int[][] copy = copyMap();

                    int[] a = empty.get(i);
                    int[] b = empty.get(j);
                    int[] c = empty.get(k);

                    copy[a[0]][a[1]] = 1;
                    copy[b[0]][b[1]] = 1;
                    copy[c[0]][c[1]] = 1;

                    bfs(copy);

                    int safe = countSafe(copy);
                    answer = Math.max(answer, safe);
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int[][] map) {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for (int[] v : virus) {
            q.offer(new int[]{v[0], v[1]});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] != 0) continue;

                map[nx][ny] = 2;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    static int[][] copyMap() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    static int countSafe(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}