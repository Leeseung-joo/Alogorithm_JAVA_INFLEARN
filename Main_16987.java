import java.util.*;
import java.io.*;

public class Main_16987 {
    static int N;
    static int[] arr;     // 내구도
    static int[] weight;  // 무게
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        weight = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int idx, int cnt) {

        // 최대값 갱신
        answer = Math.max(answer, cnt);

        // 끝까지 들었으면 종료
        if (idx == N) return;

        // 현재 계란이 이미 깨졌으면 그냥 다음
        if (arr[idx] <= 0) {
            dfs(idx + 1, cnt);
            return;
        }

        boolean hit = false; 

        for (int i = 0; i < N; i++) {
            if (i == idx) continue;
            if (arr[i] <= 0) continue;

            hit = true;

            int broken = 0;

            // 상태 변경
            arr[idx] -= weight[i];
            arr[i] -= weight[idx];

            if (arr[idx] <= 0) broken++;
            if (arr[i] <= 0) broken++;

            dfs(idx + 1, cnt + broken);

            // 상태 복구
            arr[idx] += weight[i];
            arr[i] += weight[idx];
        }

        // 칠 수 있는 계란이 하나도 없을 때
        if (!hit) {
            dfs(idx + 1, cnt);
        }
    }
}
