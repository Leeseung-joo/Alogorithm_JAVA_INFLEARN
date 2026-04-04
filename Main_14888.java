import java.util.*;
import java.io.*;

public class Main_14888 {
    static int N;
    static int[] numbers;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static int plus, minus, multiply, divide;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        multiply = Integer.parseInt(st.nextToken());
        divide = Integer.parseInt(st.nextToken());

        dfs(1, numbers[0]);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int current) {
        if (depth == N) {
            max = Math.max(max, current);
            min = Math.min(min, current);
            return;
        }

        if (plus > 0) {
            plus--;
            dfs(depth + 1, current + numbers[depth]);
            plus++;
        }

        if (minus > 0) {
            minus--;
            dfs(depth + 1, current - numbers[depth]);
            minus++;
        }

        if (multiply > 0) {
            multiply--;
            dfs(depth + 1, current * numbers[depth]);
            multiply++;
        }

        if (divide > 0) {
            divide--;
            dfs(depth + 1, current / numbers[depth]);
            divide++;
        }
    }
}