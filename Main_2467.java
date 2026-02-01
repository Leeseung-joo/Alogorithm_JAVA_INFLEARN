import java.util.*;
import java.io.*;

public class Main_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;

        long bestAbs = Long.MAX_VALUE;
        int ansL = arr[left], ansR = arr[right];

        while (left < right) {
            long sum = (long) arr[left] + arr[right];
            long abs = Math.abs(sum);

            if (abs < bestAbs) {
                bestAbs = abs;
                ansL = arr[left];
                ansR = arr[right];
            }

            // 합이 너무 작으면(음수) 값을 키워야 하니까 left++
            if (sum < 0) {
                left++;
            } 
            // 합이 너무 크면(양수) 값을 줄여야 하니까 right--
            else {
                right--;
            }
        }

        System.out.println(ansL + " " + ansR);
    }
}
