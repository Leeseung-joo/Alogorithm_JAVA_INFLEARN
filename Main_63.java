import java.util.*;

public class Main_63 {

    static int[][] memo = new int[35][35];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int r = sc.nextInt();

        System.out.println(comb(n, r));
    }

    static int comb(int n, int r) {
        // 기저조건
        if (r == 0 || n == r) return 1;

        // 이미 계산된 값이면 그대로 반환 (메모이제이션)
        if (memo[n][r] > 0) return memo[n][r];

        // 조합 재귀식
        memo[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);

        return memo[n][r];
    }
}
