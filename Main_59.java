import java.util.*;
import java.io.*;

public class Main_59 {
    static int sum = 0;
    static boolean flag = false;
    static String answer = "NO";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        // 합이 홀수면 애초에 불가능
        if (sum % 2 != 0) {
            System.out.println("NO");
            return;
        }

        dfs(0, arr, 0);
        System.out.println(answer);
    }

    static void dfs(int length, int[] arr, int temp) {
        if (flag) return;            // 이미 답 찾았으면 더 안 내려감
        if (temp > sum / 2) return;  // 가지치기: 절반 넘으면 의미 없음

        if (length == arr.length) {  // 더 볼 원소 없음 = 리프 노드
            if (temp * 2 == sum) {
                answer = "YES";
                flag = true;
            }
            return;                  // ★ 여기서 종료해야 함
        }

        // 1) 현재 원소를 선택하는 경우
        dfs(length + 1, arr, temp + arr[length]);

        // 2) 현재 원소를 선택하지 않는 경우
        dfs(length + 1, arr, temp);
    }
}
