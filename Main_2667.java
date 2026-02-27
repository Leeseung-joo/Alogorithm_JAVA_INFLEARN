import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());          
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';     
            }
        }

        visited = new boolean[N][N];
        List<Integer> sizes = new ArrayList<>();      // 단지별 집 개수 저장

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int cnt = dfs(i, j);              
                    sizes.add(cnt);
                }
            }
        }

        Collections.sort(sizes);

        System.out.println(sizes.size());             
        for (int s : sizes) System.out.println(s);    // 단지 크기들
    }

    static int dfs(int x, int y) {
        visited[x][y] = true;
        int count = 1; // 자기 자신 포함

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny)) continue;

            if (!visited[nx][ny] && map[nx][ny] == 1) {
                count += dfs(nx, ny); 
            }
        }
        return count;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}