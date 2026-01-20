import java.io.*;
import java.util.*;

public class Main_11501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long[] a = new long[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());

            long max = 0;
            long profit = 0;

            for (int i = n - 1; i >= 0; i--) {
                if (a[i] > max) max = a[i];
                else profit += (max - a[i]);
            }

            sb.append(profit).append('\n');
        }
        System.out.print(sb.toString());
    }
}
