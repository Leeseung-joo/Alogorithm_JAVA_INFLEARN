import java.util.*;
import java.io.*;

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        for (int target = 0; target <= 3; target++) {
            if (dfs(0, target, 1, 1)) {
                answer = target;
                break;
            }
        }

        System.out.println(answer);
    }

    static boolean dfs(int count, int target, int startRow, int startCol) {
        if (count == target) {
            return check();
        }

        for (int i = startRow; i <= H; i++) {
            int c = (i == startRow) ? startCol : 1;

            for (int j = c; j < N; j++) {
                if (ladder[i][j]) continue;
                if (j > 1 && ladder[i][j - 1]) continue;
                if (j < N - 1 && ladder[i][j + 1]) continue;

                ladder[i][j] = true;
                if (dfs(count + 1, target, i, j)) return true;
                ladder[i][j] = false;
            }
        }

        return false;
    }

    static boolean check() {
        for (int start = 1; start <= N; start++) {
            int col = start;

            for (int row = 1; row <= H; row++) {
                if (ladder[row][col]) col++;
                else if (col > 1 && ladder[row][col - 1]) col--;
            }

            if (col != start) return false;
        }
        return true;
    }
}