import java.util.*;
import java.io.*;

public class Main_18185 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2]; // i+1, i+2 접근 위해 여유 공간

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long total = 0;

        for(int i = 0; i < N; i++){
          if(arr[i+1] > arr[i+2]){
            int two = Math.min(arr[i], arr[i+1] - arr[i+2]);
          
          arr[i] -= two;
          arr[i+1] -= two;
          total += 5L * two;
        }
        int three = Math.min(arr[i], Math.min(arr[i + 1], arr[i + 2]));
        arr[i] -= three;
        arr[i + 1] -= three;
        arr[i + 2] -= three;
        total += 7L * three;

        int two = Math.min(arr[i], arr[i + 1]);
        arr[i] -= two;
        arr[i + 1] -= two;
        total += 5L * two;

        // 마지막 남은 건 1개씩 처리
        total += 3L * arr[i];
        arr[i] = 0;
      }

      System.out.println(total);
  }
}