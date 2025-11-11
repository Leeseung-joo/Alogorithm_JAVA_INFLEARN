import java.io.*;
import java.util.*;

public class Main_25 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int max = 0;
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            // 뒤집은 값의 최대도 100000을 넘지 않으니까 일단 입력 최대만 기억해도 됨
            max = Math.max(max, arr[i]);
        }

        // 문제 조건: 각 자연수의 크기는 100,000을 넘지 않는다.
        // 뒤집어도 100000 이하 -> 그냥 100000까지 체 만들어두면 안전
        int LIMIT = 100000;
        boolean[] isPrime = new boolean[LIMIT + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= LIMIT; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= LIMIT; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int rev = reverse(arr[i]);
            if (isPrime[rev]) {
                sb.append(rev).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }

    // 숫자 뒤집기 (앞의 0은 자연스럽게 사라짐)
    private static int reverse(int x) {
        int res = 0;
        while (x > 0) {
            res = res * 10 + (x % 10);
            x /= 10;
        }
        return res;
    }
}
