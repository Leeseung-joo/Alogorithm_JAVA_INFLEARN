import java.util.*;
import java.io.*;

public class Main_1952 {
    static int M;
    static int N;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[M][N];

        dfs(0, 0, 0);
        System.out.println(cnt);
    }

    static void dfs(int x, int y, int direction) {
        visited[x][y] = true;

        int nx = x + dx[direction];
        int ny = y + dy[direction];

        if (inRange(nx, ny) && !visited[nx][ny]) {
            dfs(nx, ny, direction);
            return;
        }

        direction = (direction + 1) % 4;
        cnt++;

        nx = x + dx[direction];
        ny = y + dy[direction];

        if (inRange(nx, ny) && !visited[nx][ny]) {
            dfs(nx, ny, direction);
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}