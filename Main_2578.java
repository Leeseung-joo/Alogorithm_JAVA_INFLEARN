import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[5][5];
        int[][] pos = new int[26][2];

        // 빙고판 입력
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                pos[n][0] = i;
                pos[n][1] = j;
            }
        }

        int[] num = new int[25];

        // 사회자 숫자 입력
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                num[i * 5 + j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 25; i++) {
            int number = num[i];

            int r = pos[number][0];
            int c = pos[number][1];
            map[r][c] = 0;

            // ⭐ 매번 검사
            if (bingo(map)) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    static boolean bingo(int[][] map) {
        int cnt = 0;

        // 가로
        for (int i = 0; i < 5; i++) {
            boolean flag = true;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }

        // 세로
        for (int i = 0; i < 5; i++) {
            boolean flag = true;
            for (int j = 0; j < 5; j++) {
                if (map[j][i] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
        }

        // 대각선 \
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) cnt++;

        // 대각선 /
        flag = true;
        for (int i = 0; i < 5; i++) {
            if (map[i][4 - i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) cnt++;

        return cnt >= 3;
    }
}