import java.util.*;
import java.io.*;

public class Main_17136 {

    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5}; // 1~5 크기 색종이 각각 5개
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void dfs(int idx, int used) {
        // 가지치기
        if (used >= answer) {
            return;
        }

        // 끝까지 다 확인한 경우
        if (idx == 100) {
            answer = Math.min(answer, used);
            return;
        }

        int x = idx / 10;
        int y = idx % 10;

        // 현재 칸이 0이면 그냥 다음 칸으로
        if (map[x][y] == 0) {
            dfs(idx + 1, used);
            return;
        }

        // 현재 칸이 1이면 큰 색종이부터 붙여보기
        for (int size = 5; size >= 1; size--) {
            if (canAttach(x, y, size) && paper[size] > 0) {
                attach(x, y, size, 0);
                paper[size]--;

                dfs(idx + 1, used + 1);

                attach(x, y, size, 1);
                paper[size]++;
            }
        }
    }

    static boolean canAttach(int x, int y, int size) {
        // 범위 체크
        if (x + size > 10 || y + size > 10) {
            return false;
        }

        // 전부 1인지 확인
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    static void attach(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = value;
            }
        }
    }
}