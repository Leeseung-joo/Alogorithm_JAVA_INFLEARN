\import java.util.*;
import java.io.*;

public class Main_2933 {
    static int R, C, N;
    static char[][] map;
    static int[] order;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static class Pos {
        int r, c;
        Pos(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()); // 전역 C에 넣기
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());  // 전역 N에 넣기
        order = new int[N];

        st = new StringTokenizer(br.readLine()); // 반드시 새로 읽기
        for (int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int h = order[i];
            int r = R - h; // 던지는 행
            boolean leftToRight = (i % 2 == 0);

            boolean broken = throwStick(r, leftToRight);
            if (!broken) continue; // 부순 게 없으면 변화 없음

            boolean[][] stable = markStableFromBottom(); // 바닥과 연결된 안정 클러스터

            List<Pos> floating = getOneFloatingCluster(stable);
            if (!floating.isEmpty()) {
                dropCluster(floating, stable);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(map[i]).append('\n');
        }
        System.out.print(sb.toString());
    }

    static boolean throwStick(int r, boolean leftToRight) {
        if (leftToRight) {
            for (int c = 0; c < C; c++) { // 
                if (map[r][c] == 'x') {
                    map[r][c] = '.';
                    return true;
                }
            }
        } else {
            for (int c = C - 1; c >= 0; c--) {
                if (map[r][c] == 'x') {
                    map[r][c] = '.';
                    return true;
                }
            }
        }
        return false;
    }

    // 바닥과 연결된 안정 클러스터 BFS로 마킹
    static boolean[][] markStableFromBottom() {
        boolean[][] vis = new boolean[R][C];
        ArrayDeque<Pos> q = new ArrayDeque<>();

        for (int c = 0; c < C; c++) {
            if (map[R - 1][c] == 'x' && !vis[R - 1][c]) {
                vis[R - 1][c] = true;
                q.add(new Pos(R - 1, c));

                while (!q.isEmpty()) {
                    Pos cur = q.poll();
                    for (int k = 0; k < 4; k++) {
                        int nr = cur.r + dr[k];
                        int nc = cur.c + dc[k];

                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue; 
                        if (vis[nr][nc]) continue;
                        if (map[nr][nc] != 'x') continue;

                        vis[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                    }
                }
            }
        }
        return vis;
    }

    // 안정 마킹 안 된 x중 하나의 클러스터만 골라서 반환
    static List<Pos> getOneFloatingCluster(boolean[][] stable) {
        boolean[][] vis = new boolean[R][C];
        ArrayDeque<Pos> q = new ArrayDeque<>();
        List<Pos> cluster = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && !stable[i][j] && !vis[i][j]) {
                    vis[i][j] = true;
                    q.add(new Pos(i, j));
                    cluster.add(new Pos(i, j));

                    while (!q.isEmpty()) {
                        Pos cur = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nr = cur.r + dr[k];
                            int nc = cur.c + dc[k];

                            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                            if (vis[nr][nc]) continue;
                            if (map[nr][nc] != 'x') continue; 
                            if (stable[nr][nc]) continue;

                            vis[nr][nc] = true;
                            q.add(new Pos(nr, nc));
                            cluster.add(new Pos(nr, nc));
                        }
                    }
                    return cluster;
                }
            }
        }
        return cluster; // 비어있으면 empty
    }

    // 떠있는 클러스터 낙하 처리
    static void dropCluster(List<Pos> cluster, boolean[][] stable) {
        // 자기 자신 충돌 방지 위해 먼저 지움
        for (Pos p : cluster) map[p.r][p.c] = '.';

        int drop = Integer.MAX_VALUE;

        // 각 칸이 아래로 얼마나 갈 수 있는지 계산 -> 최소 낙하 거리
        for (Pos p : cluster) {
            int r = p.r;
            int c = p.c;

            int d = 0;
            int nr = r + 1;
            while (nr < R && map[nr][c] == '.' && !stable[nr][c]) {
                nr++;
                d++;
            }
            drop = Math.min(drop, d);
        }

        for (Pos p : cluster) {
            map[p.r + drop][p.c] = 'x';
        }
    }
}
