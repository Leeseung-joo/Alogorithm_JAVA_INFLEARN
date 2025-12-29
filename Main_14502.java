import java.util.*;
import java.io.*;

public class Main_14502 {

    static int N, M;
    static int[][] map;
    static ArrayList<int[]> empties = new ArrayList<>(); // 빈칸(0) 좌표들
    static ArrayList<int[]> viruses = new ArrayList<>(); // 바이러스(2) 좌표들

    // 4방향
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; 

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 빈칸 / 바이러스 위치 저장
                if (map[i][j] == 0) empties.add(new int[]{i, j});
                else if (map[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        // 빈칸 중 3개를 조합으로 선택 (i < j < k)
        int E = empties.size();
        for (int i = 0; i < E; i++) {
            for (int j = i + 1; j < E; j++) {
                for (int k = j + 1; k < E; k++) {

                    // 1) 벽 3개 세우기
                    int[] a = empties.get(i);
                    int[] b = empties.get(j);
                    int[] c = empties.get(k);

                    map[a[0]][a[1]] = 1;
                    map[b[0]][b[1]] = 1;
                    map[c[0]][c[1]] = 1;

                    // 2) 바이러스 확산 후 안전영역 계산
                    answer = Math.max(answer, simulateAndCountSafe());

                    // 3) 원복
                    map[a[0]][a[1]] = 0;
                    map[b[0]][b[1]] = 0;
                    map[c[0]][c[1]] = 0;
                }
            }
        }

        System.out.println(answer);
    }

    // 현재 map 상태에서 바이러스 퍼뜨린 뒤 안전영역(0) 개수 반환
    static int simulateAndCountSafe() {

        // 원본 맵을 그대로 쓰면 다음 조합에 영향 -> 복사해서 시뮬레이션
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, M);
        }

        // BFS 큐에 모든 바이러스 넣고 시작 (멀티소스 BFS)
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int[] v : viruses) {
            q.add(new int[]{v[0], v[1]});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 밖이면 패스
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 빈칸이면 바이러스 퍼짐
                if (copy[nx][ny] == 0) {
                    copy[nx][ny] = 2;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        // 안전영역(0) 개수 세기
        int safe = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) safe++;
            }
        }
        return safe;
    }
}
