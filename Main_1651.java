import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long left = 1;
        long right = arr[K - 1];   // 최대 랜선 길이
        long result = 0;

        while (left <= right) {
            long mid = left + (right - left) / 2; // overflow 방지

            if (countPieces(mid, arr) < N) {
                right = mid - 1;
            } else {
                result = mid;      // mid는 가능한 길이, 더 크게 탐색
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    static long countPieces(long mid, int[] arr) {
        long sum = 0;
        for (int v : arr) {
            sum += (v / mid);
        }
        return sum;
    }
}