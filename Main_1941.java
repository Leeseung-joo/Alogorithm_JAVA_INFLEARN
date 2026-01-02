import java.io.*;
import java.util.*;

public class Main_1941 {
    static char[][] grid = new char[5][5];
    static boolean[] sel = new boolean[25]; // 0~24 중 선택된 칸 표시
    static int answer = 0;

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 0); // idx, picked, sCnt
        System.out.println(answer);
    }

    // idx: 현재 0~24 탐색 위치
    // picked: 현재까지 선택한 칸 수
    // sCnt: 선택한 칸 중 'S' 개수
    static void dfs(int idx, int picked, int sCnt) {
        // 가지치기: Y가 4명 이상이면 불가능 (S는 최소 4명 필요)
        int yCnt = picked - sCnt;
        if (yCnt > 3) return;

        // 7명 다 뽑았으면 연결성 검사
        if (picked == 7) {
            if (isConnected()) answer++;
            return;
        }

        // 더 이상 볼 칸이 없으면 종료
        if (idx == 25) return;

        // 1) 현재 칸 선택
        sel[idx] = true;
        int r = idx / 5, c = idx % 5;
        dfs(idx + 1, picked + 1, sCnt + (grid[r][c] == 'S' ? 1 : 0));

        // 2) 현재 칸 미선택
        sel[idx] = false;
        dfs(idx + 1, picked, sCnt);
    }

    // sel[]에 체크된 7칸이 4방향으로 모두 연결인지 BFS로 확인
    static boolean isConnected() {
        // 선택된 칸 중 시작점 찾기
        int start = -1;
        for (int i = 0; i < 25; i++) {
            if (sel[i]) {
                start = i;
                break;
            }
        }
        if (start == -1) return false;

        boolean[] visited = new boolean[25];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        int cnt = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int x = cur / 5;
            int y = cur % 5;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                int ni = nx * 5 + ny;
                if (!sel[ni]) continue;      // 선택된 칸만 이동
                if (visited[ni]) continue;

                visited[ni] = true;
                q.add(ni);
                cnt++;
            }
        }

        return cnt == 7;
    }
}
