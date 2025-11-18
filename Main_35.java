import java.io.*;
import java.util.*;

public class Main_35 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        long sum = 0;      // M이 최대 1e8이라 int도 되지만, 안전하게 long 써도 됨
        int cnt = 0;

        while (true) {
            if (sum >= M) {
                // 현재 구간 합이 M 이상이면 왼쪽을 줄인다
                if (sum == M) cnt++;
                sum -= arr[left++];
            } else {
                // sum < M
                if (right == N) break;    // 오른쪽을 더 늘릴 수 없으면 종료
                sum += arr[right++];
            }
        }

        System.out.println(cnt);
    }
}
