import java.io.*;
import java.util.*;

/**
 * BOJ 9328 열쇠
 * 정석: 바깥 패딩 + BFS + 문 대기(door waiting list)
 */
public class Main_1525 {
    static int H, W;
    static char[][] a;                 // 맵(패딩 포함)
    static boolean[][] visited;
    static boolean[] hasKey;           // a~z 보유 여부
    static ArrayDeque<int[]> q;        // BFS 큐

    // 문 대기 리스트: 특정 문(A~Z)을 못 열어서 기다리는 좌표들
    static ArrayList<int[]>[] waiting; // 26개

    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // 패딩: (H+2) x (W+2), 바깥은 '.'로 채움
            a = new char[H + 2][W + 2];
            for (int i = 0; i < H + 2; i++) Arrays.fill(a[i], '.');

            // 입력 맵을 (1..H, 1..W)에 넣기
            for (int i = 1; i <= H; i++) {
                String line = br.readLine();
                for (int j = 1; j <= W; j++) {
                    a[i][j] = line.charAt(j - 1);
                }
            }

            // 열쇠 입력
            hasKey = new boolean[26];
            String keysLine = br.readLine().trim();
            if (!keysLine.equals("0")) {
                for (int i = 0; i < keysLine.length(); i++) {
                    char c = keysLine.charAt(i);
                    hasKey[c - 'a'] = true;
                }
            }

            // 문 대기 리스트 초기화
            waiting = new ArrayList[26];
            for (int i = 0; i < 26; i++) waiting[i] = new ArrayList<>();

            visited = new boolean[H + 2][W + 2];
            q = new ArrayDeque<>();

            // 바깥(0,0)에서 시작
            q.add(new int[]{0, 0});
            visited[0][0] = true;

            int documents = bfsCollectDocuments();
            out.append(documents).append('\n');
        }

        System.out.print(out);
    }

    static int bfsCollectDocuments() {
        int docCnt = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= H + 2 || ny < 0 || ny >= W + 2) continue;
                if (visited[nx][ny]) continue;
                if (a[nx][ny] == '*') continue; // 벽

                char cell = a[nx][ny];

                // 1) 문서
                if (cell == '$') {
                    docCnt++;
                    a[nx][ny] = '.'; // 먹었으면 빈칸 처리
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    continue;
                }

                // 2) 열쇠 (a~z)
                if ('a' <= cell && cell <= 'z') {
                    int k = cell - 'a';

                    // 새 열쇠를 얻는 순간, 해당 문 대기열 모두 풀어줌
                    if (!hasKey[k]) {
                        hasKey[k] = true;

                        // 대기 중이던 문(대문자 = k에 대응)을 전부 큐에 넣는다
                        for (int[] pos : waiting[k]) {
                            int px = pos[0], py = pos[1];
                            if (!visited[px][py]) {
                                visited[px][py] = true;
                                q.add(new int[]{px, py});
                            }
                        }
                        waiting[k].clear();
                    }

                    a[nx][ny] = '.'; // 열쇠 먹고 빈칸 처리
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    continue;
                }

                // 3) 문 (A~Z)
                if ('A' <= cell && cell <= 'Z') {
                    int door = cell - 'A';
                    if (hasKey[door]) {
                        // 열쇠 있으면 통과
                        a[nx][ny] = '.';
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    } else {
                        // 열쇠 없으면 "대기 리스트"에 좌표 저장(나중에 열쇠 얻으면 풀기)
                        waiting[door].add(new int[]{nx, ny});
                        // visited는 아직 false로 유지하는 방식도 되지만,
                        // 여기서는 "중복 대기 추가"를 막기 위해 방문처리 해버리는 구현도 가능.
                        // 다만 방문처리하면, 열쇠 얻었을 때 큐에 넣는 것으로 접근 재개됨.
                        visited[nx][ny] = true;
                    }
                    continue;
                }

                // 4) 빈칸 '.' 등
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return docCnt;
    }
}
