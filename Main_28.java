import java.io.*;
import java.util.*;

public class Main_28 {  
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;

        // 1️⃣ 각 행의 합
        for (int i = 0; i < N; i++) {
            int tempSum = 0;
            for (int j = 0; j < N; j++) {
                tempSum += map[i][j];
            }
            max = Math.max(max, tempSum);
        }

        // 2️⃣ 각 열의 합
        for (int j = 0; j < N; j++) {
            int tempSum = 0;
            for (int i = 0; i < N; i++) {
                tempSum += map[i][j];
            }
            max = Math.max(max, tempSum);
        }

        // 3️⃣ 대각선 2개 합
        int diag1 = 0, diag2 = 0;
        for (int i = 0; i < N; i++) {
            diag1 += map[i][i];           // 왼쪽 위 → 오른쪽 아래
            diag2 += map[i][N - 1 - i];   // 오른쪽 위 → 왼쪽 아래
        }

        max = Math.max(max, diag1);
        max = Math.max(max, diag2);

        // 4️⃣ 최댓값 출력
        System.out.println(max);
    }
}
