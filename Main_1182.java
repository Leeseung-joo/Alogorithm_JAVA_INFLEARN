import java.util.*;
import java.io.*;

public class Main_1182 {
    static int[] arr;
    static int answer = 0;
    static int N, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);

        // 문제 조건: "크기가 양수인 부분수열"
        // S == 0이면 공집합(아무것도 안 뽑음)도 sum=0으로 세어지므로 1개 빼줘야 함
        if (S == 0) answer--;

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {
        if (idx == N) {           // 끝까지 결정했을 때만 센다
            if (sum == S) answer++;
            return;
        }

        // 현재 원소를 안 뽑는 경우
        dfs(idx + 1, sum);

        // 현재 원소를 뽑는 경우
        dfs(idx + 1, sum + arr[idx]);
    }
}
