import java.io.*;

public class Main_9663 {
    static int N;
    static boolean[] colUsed;   // 열 사용 여부
    static boolean[] diag1;     // row + col
    static boolean[] diag2;     // row - col + (N - 1)
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        colUsed = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];

        dfs(0); // 0번째 행부터 시작
        System.out.println(count);
    }

    // row번째 행에 퀸을 놓는 모든 경우 탐색
    static void dfs(int row) {
        // N행까지 모두 배치 성공
        if (row == N) {
            count++;
            return;
        }

        // row행에 놓을 열(col) 선택
        for (int col = 0; col < N; col++) {
            int d1 = row + col;            // \ 대각선
            int d2 = row - col + (N - 1);  // / 대각선

            // 열/대각선에 이미 퀸이 있으면 불가
            if (colUsed[col] || diag1[d1] || diag2[d2]) continue;

            // 퀸 배치(사용 처리)
            colUsed[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // 다음 행으로
            dfs(row + 1);

            // 백트래킹(원복)
            colUsed[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
