import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_37 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int left = 1;
        int right = 1;
        int sum = 1;
        int cnt = 0;

        // 최소 길이 2이므로 left는 대략 N/2까지만 보면 된다
        while (left <= N / 2 + 1) {
            if (sum < N) {
                right++;
                if (right > N) break;   // 더 이상 늘릴 수 없음
                sum += right;
            } else if (sum > N) {
                sum -= left;
                left++;
            } else { // sum == N
                // 길이 2 이상인 구간만 센다
                if (right - left + 1 >= 2) {
                    cnt++;
                }
                sum -= left;
                left++;
            }
        }

        System.out.println(cnt);
    }
}
