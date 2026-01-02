import java.io.*;
import java.util.*;

public class Main_6603 {

    static int N;
    static int[] arr;
    static int[] pick = new int[6];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if (N == 0) break;

            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            System.out.println();
        }
    }

    // idx: 현재 arr 인덱스
    // depth: 현재 뽑은 개수
    static void dfs(int idx, int depth) {

        // 6개 다 뽑았으면 출력
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(pick[i] + " ");
            }
            System.out.println();
            return;
        }

        // 더 이상 뽑을 게 없으면 종료
        if (idx == N) return;

        //  현재 숫자 선택
        pick[depth] = arr[idx];
        dfs(idx + 1, depth + 1);

        // 현재 숫자 선택 안 함
        dfs(idx + 1, depth);
    }
}
