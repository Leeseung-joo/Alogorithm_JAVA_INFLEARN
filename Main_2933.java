import java.io.*;
import java.util.*;

public class Main_2933 {

    static int R, C, N;
    static char[][] map;
    static int[] order;

    // 4방향
    static final int[] dr = { -1, 1, 0, 0 };
    static final int[] dc = { 0, 0, -1, 1 };

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        order = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) order[i] = Integer.parseInt(st.nextToken());

        for (int turn = 0; turn < N; turn++) {
            int height = order[turn];
            int row = R - height; // 입력 높이(바닥 기준) -> row index로 변환

            Point broken = throwStick(row, turn);
            if (broken == null) continue; // 이번 턴에 아무것도 못 부숨

            // 부서진 칸 주변(최대 4개)만 클러스터 검사
            for (int d = 0; d < 4; d++) {
                int nr = broken.r + dr[d];
                int nc = broken.c + dc[d];
                if (!inRange(nr, nc)) continue;
                if (map[nr][nc] != 'x') continue;

                // 이웃 미네랄을 시작점으로 클러스터를 모으고,
                // 떠있으면 dropCluster 호출
                checkAndDropIfFloating(nr, nc);

                // 떠있는 클러스터는 보통 1개만 생김.
                // dropCluster가 실제로 떨어뜨렸다면 더 볼 필요가 없어서 break해도 됨.
                // (dropCluster 구현 방식에 따라 boolean 리턴받아 break 하는 방식도 가능)
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(map[i]).append('\n');
        }
        System.out.print(sb);
    }

    /**
     * 막대를 던져서 row에서 미네랄('x') 하나를 부순다.
     * turn이 짝수면 왼->오, 홀수면 오->왼.
     * 부순 좌표를 반환, 못 부수면 null
     */
    static Point throwStick(int row, int turn) {
        if (turn % 2 == 0) { // 왼 -> 오른
            for (int c = 0; c < C; c++) {
                if (map[row][c] == 'x') {
                    map[row][c] = '.';
                    return new Point(row, c);
                }
            }
        } else { // 오른 -> 왼
            for (int c = C - 1; c >= 0; c--) {
                if (map[row][c] == 'x') {
                    map[row][c] = '.';
                    return new Point(row, c);
                }
            }
        }
        return null;
    }

    static void checkAndDropIfFloating(int sr, int sc) {
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<Point> q = new ArrayDeque<>();
        ArrayList<Point> cluster = new ArrayList<>();

        boolean touchGround = false;

        visited[sr][sc] = true;
        q.add(new Point(sr, sc));

        while (!q.isEmpty()) {
            Point p = q.poll();
            cluster.add(p);

            if (p.r == R - 1) touchGround = true;

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (!inRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != 'x') continue;

                visited[nr][nc] = true;
                q.add(new Point(nr, nc));
            }
        }

        if (!touchGround) {
            dropCluster(cluster); 
        }
    }

    static void dropCluster(List<Point> cluster) {
        // 1) 클러스터 표시 (빠른 포함 체크)
        boolean[][] inCluster = new boolean[R][C];
        for (Point p : cluster) {
            inCluster[p.r][p.c] = true;
        }
    
        // 2) 맵에서 클러스터를 일단 제거
        for (Point p : cluster) {
            map[p.r][p.c] = '.';
        }
    
        // 3) 얼마나 떨어질 수 있는지(minDist) 계산
        int minDist = Integer.MAX_VALUE;
    
        for (Point p : cluster) {
            int r = p.r;
            int c = p.c;
    
            int dist = 0;
            int nr = r + 1;
            while (nr < R && map[nr][c] == '.') {
                dist++;
                nr++;
            }
            // nr==R이면 바닥까지 '.'였던 것 (dist가 바닥까지 거리)
            // nr<R이면 map[nr][c]가 'x'인 장애물 만난 것 (dist는 그 바로 위까지)
    
            minDist = Math.min(minDist, dist);
        }
    
        // 4) minDist 만큼 아래로 다시 배치
        for (Point p : cluster) {
            map[p.r + minDist][p.c] = 'x';
        }
    }
    

    static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
