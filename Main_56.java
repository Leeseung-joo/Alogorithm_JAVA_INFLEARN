import java.util.*;
import java.io.*;

public class Main_56 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] stall = new int[N];
        for (int i = 0; i < N; i++) {
            stall[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(stall);

        int left = 1;                          // 최소 거리
        int right = stall[N - 1] - stall[0];   // 최대 거리
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;      // 후보 거리 D

            if (canPlace(stall, C, mid)) {
                // mid 거리로 C마리 놓는 거 가능 → 더 늘려보자
                answer = mid;
                left = mid + 1;
            } else {
                // mid 거리로는 C마리 못 놓음 → 줄여야 함
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canPlace(int[] stall, int C, int dist) {
        int cnt = 1;
        int lastPos = stall[0];

        for (int i = 1; i < stall.length; i++) {
            if (stall[i] - lastPos >= dist) {
                cnt++;
                lastPos = stall[i];
            }
        }
        return cnt >= C;
    }
}
