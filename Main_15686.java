import java.util.*;
import java.io.*;

public class Main_15686 {
    static int N;
    static int M;
    static int[][] map;
    static ArrayList<int[]> list = new ArrayList<>(); // 치킨집 위치
    static ArrayList<int[]> selected = new ArrayList<>(); // 선택된 치킨집
    static ArrayList<int[]> house = new ArrayList<>(); // 집 위치
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    list.add(new int[]{i, j});
                }
                if (map[i][j] == 1) {
                    house.add(new int[]{i, j});
                }
            }
        }

        selectChicken(0, 0);
        System.out.println(answer);
    }

    static void selectChicken(int depth, int start) {
        if (depth == M) {
            int total = 0;  // 이번 조합의 도시 치킨 거리

            for (int[] h : house) {
                int hx = h[0];
                int hy = h[1];

                int minDist = Integer.MAX_VALUE;

                for (int[] c : selected) {
                    int dist = Math.abs(hx - c[0]) + Math.abs(hy - c[1]);
                    minDist = Math.min(minDist, dist);
                }

                total += minDist;
            }

            answer = Math.min(answer, total);
            return;
        }

        for (int i = start; i < list.size(); i++) {
            selected.add(list.get(i));
            selectChicken(depth + 1, i + 1);
            selected.remove(selected.size() - 1);
        }
    }
}