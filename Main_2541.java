import java.util.*;
import java.io.*;
public class Main_2541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + k - 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 원형 처리
        for (int i = 0; i < k - 1; i++) {
            arr[N + i] = arr[i];
        }

        int[] count = new int[d + 1];
        int distinct = 0;

        // 첫 윈도우 세팅
        for (int i = 0; i < k; i++) {
            if (count[arr[i]] == 0) distinct++;
            count[arr[i]]++;
        }

        int answer = distinct + (count[c] == 0 ? 1 : 0);

        // 슬라이딩
        for(int i = 1; i < N; i++){
          int remove = arr[i-1];
          count[remove]--;
          if(count[remove] == 0) distinct--;


          int add = arr[i + k - 1];
          if (count[add] == 0) distinct++;
          count[add]++;

          int current = distinct + (count[c] == 0 ? 1 : 0);
          answer = Math.max(answer, current);
      }

      System.out.println(answer);
    }}