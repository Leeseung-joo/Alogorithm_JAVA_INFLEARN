import java.util.*;
import java.io.*;

public class Main_17281 {
    static int[][] arr; // [이닝][선수번호]
    static int[] order = new int[9];      // order[타순] = 선수번호
    static boolean[] visited = new boolean[9];
    static int N;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        arr = new int[N][9];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 선수(인덱스 0)는 4번 타자 고정
        order[3] = 0;
        visited[0] = true;

        dfs(0);
        System.out.println(result);
    }

    static void dfs(int depth) {
        if (depth == 9) {
            simulate();
            return;
        }

        // 4번 타자 자리는 이미 고정
        if (depth == 3) {
            dfs(depth + 1);
            return;
        }

        for (int player = 1; player < 9; player++) {
            if (visited[player]) continue;

            visited[player] = true;
            order[depth] = player;

            dfs(depth + 1);

            visited[player] = false;
        }
    }

    static void simulate() {
        int score = 0;
        int batterIdx = 0; // 다음 타자 인덱스, 이닝 넘어가도 이어짐

        for (int inning = 0; inning < N; inning++) {
            int out = 0;
            boolean[] base = new boolean[3]; // 1루, 2루, 3루

            while (out < 3) {
                int player = order[batterIdx];
                int hit = arr[inning][player];

                if (hit == 0) {
                    out++;
                } else {
                    // 기존 주자 이동
                    for (int j = 2; j >= 0; j--) {
                        if (base[j]) {
                            if (j + hit >= 3) score++;
                            else base[j + hit] = true;
                            base[j] = false;
                        }
                    }

                    // 타자 이동
                    if (hit == 4) score++;
                    else base[hit - 1] = true;
                }

                batterIdx = (batterIdx + 1) % 9;
            }
        }

        result = Math.max(result, score);
    }
}