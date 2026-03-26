import java.util.*;
import java.io.*;

public class Main_8979 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 국가 번호
            arr[i][1] = Integer.parseInt(st.nextToken()); // 금
            arr[i][2] = Integer.parseInt(st.nextToken()); // 은
            arr[i][3] = Integer.parseInt(st.nextToken()); // 동
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] != o2[1]) return o2[1] - o1[1]; // 금 내림차순
            if (o1[2] != o2[2]) return o2[2] - o1[2]; // 은 내림차순
            return o2[3] - o1[3]; // 동 내림차순
        });

        int rank = 1;

        for (int i = 0; i < N; i++) {
            if (i > 0) {
                if (arr[i][1] != arr[i - 1][1] ||
                    arr[i][2] != arr[i - 1][2] ||
                    arr[i][3] != arr[i - 1][3]) {
                    rank = i + 1;
                }
            }

            if (arr[i][0] == K) {
                System.out.println(rank);
                break;
            }
        }
    }
}