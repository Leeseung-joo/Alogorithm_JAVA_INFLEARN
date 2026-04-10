import java.util.*;
import java.io.*;

public class Main_5427 {
    static char[][] map;
    static int startX, startY;
    static int W, H;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            ArrayList<int[]> fires = new ArrayList<>();

            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);

                    if (map[i][j] == '@') {
                        startX = i;
                        startY = j;
                    } else if (map[i][j] == '*') {
                        fires.add(new int[]{i, j});
                    }
                }
            }

            String result = bfs(fires);
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    static String bfs(ArrayList<int[]> fires) {
        ArrayDeque<int[]> fireQ = new ArrayDeque<>();
        ArrayDeque<int[]> personQ = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        for (int[] arr : fires) {
            fireQ.offer(new int[]{arr[0], arr[1]});
        }

        personQ.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        int time = 0;

        while (!personQ.isEmpty()) {
            time++;

            int fSize = fireQ.size();
            for (int i = 0; i < fSize; i++) {
                int[] cur = fireQ.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];

                    if (!inRange(nx, ny)) continue;
                    if (map[nx][ny] == '#' || map[nx][ny] == '*') continue;

                    map[nx][ny] = '*';
                    fireQ.offer(new int[]{nx, ny});
                }
            }

            int pSize = personQ.size();
            for (int i = 0; i < pSize; i++) {
                int[] cur = personQ.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = cur[0] + dx[j];
                    int ny = cur[1] + dy[j];

                    if (!inRange(nx, ny)) {
                        return String.valueOf(time);
                    }

                    if (visited[nx][ny]) continue;
                    if (map[nx][ny] == '#' || map[nx][ny] == '*') continue;

                    visited[nx][ny] = true;
                    personQ.offer(new int[]{nx, ny});
                }
            }
        }

        return "IMPOSSIBLE";
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}