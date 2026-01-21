import java.io.*;
import java.util.*;

public class Main_220922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        int maxVal = 100000; 
        int[] cnt = new int[maxVal + 1];

        for (int i = 0; i < N; i++) a[i] = Integer.parseInt(st.nextToken());

        int l = 0, ans = 0;
        for (int r = 0; r < N; r++) {
            cnt[a[r]]++;

            while (cnt[a[r]] > K) {  
                cnt[a[l]]--;
                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        System.out.println(ans);
    }
}
