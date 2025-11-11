import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);  // 처음엔 전부 소수라고 가정
        isPrime[0] = isPrime[1] = false; // 0, 1은 소수 아님

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) count++;
        }

        System.out.println(count);
    }
}
