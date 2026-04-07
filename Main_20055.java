import java.util.*;
import java.io.*;

public class Main_20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belt = new int[2 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] robot = new boolean[N + 1];
        int step = 0;

        while (true) {
            step++;

            // 1. 벨트 회전
            int temp = belt[2 * N];
            for (int i = 2 * N; i >= 2; i--) {
                belt[i] = belt[i - 1];
            }
            belt[1] = temp;

            // 로봇 회전
            for (int i = N - 1; i >= 1; i--) {
                robot[i + 1] = robot[i];
            }
            robot[1] = false;
            robot[N] = false;

            // 2. 로봇 이동
            for (int i = N - 1; i >= 1; i--) {
                if (robot[i] && !robot[i + 1] && belt[i + 1] >= 1) {
                    robot[i] = false;
                    robot[i + 1] = true;
                    belt[i + 1]--;
                }
            }
            robot[N] = false;

            // 3. 새 로봇 올리기
            if (belt[1] >= 1 && !robot[1]) {
                robot[1] = true;
                belt[1]--;
            }

            // 4. 종료 조건
            int cnt = 0;
            for (int i = 1; i <= 2 * N; i++) {
                if (belt[i] == 0) cnt++;
            }

            if (cnt >= K) {
                System.out.println(step);
                break;
            }
        }
    }
}