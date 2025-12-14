import java.io.*;
import java.util.*;

public class Main {

    // 박스 객체
    static class Box {
        int k, h, w;   // 번호, 높이, 너비
        int r, c;      // 현재 박스의 좌상단 위치 (r=행, c=열)
        boolean removed; // 제거 여부

        Box(int k, int h, int w, int r, int c) {
            this.k = k;
            this.h = h;
            this.w = w;
            this.r = r;
            this.c = c;
            this.removed = false;
        }
    }

    static int N, M;
    static int[][] A;               // 맵 (0=빈칸, k=박스번호)
    static List<Box> boxes = new ArrayList<>();

    // 방향 정의: 0=아래, 1=왼쪽, 2=오른쪽
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};

    // 범위 체크
    static boolean inRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    // ===============================================
    //  현재 위치 (r,c) 에서 방향 d 로 한 칸 이동 가능한지 확인
    //  핵심: “진입하게 되는 경계선(frontier)”만 검사하면 됨
    // ===============================================
    static boolean canPut(int h, int w, int r, int c, int d) {

        // 박스가 차지하는 전체 범위: (r ~ r+h-1), (c ~ c+w-1)
        int r1 = r, r2 = r + h - 1;
        int c1 = c, c2 = c + w - 1;

        // 이동 방향에 따라 검사해야 할 “새로운 경계선”을 설정한다.
        if (d == 0) {
            // ↓ 아래로 이동 → 기존 bottom 라인이 새로 들어가게 됨
            r1 = r + h - 1;  
        }
        else if (d == 1) {
            // ← 왼쪽 이동 → 기존 left 라인이 새로 들어가게 됨
            c2 = c; 
        }
        else {
            // → 오른쪽 이동 → 기존 right 라인이 새로 들어가게 됨
            c1 = c + w - 1;
        }

        // frontier(새로 들어가는 라인) 영역 검사
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {

                // 맵 밖으로 나가면 이동 불가
                if (!inRange(i, j)) return false;

                // 빈칸이 아니면 이동 불가
                if (A[i][j] != 0) return false;
            }
        }
        return true;
    }

    // ===============================================
    //  방향 d 로 끝까지 이동시키기 (중력, 좌/우 이동 공통)
    //  한 칸씩 이동해보고 가능하면 반복 이동
    // ===============================================
    static int[] moveBox(int h, int w, int r, int c, int d) {
        int rr = r, cc = c;

        while (true) {
            int nr = rr + dx[d];
            int nc = cc + dy[d];

            // 한 칸 이동 가능하면 이동 진행
            if (canPut(h, w, nr, nc, d)) {
                rr = nr;
                cc = nc;
            } else break; // 이동 불가 → 종료
        }

        return new int[]{rr, cc};
    }

    // ===============================================
    //  박스를 맵에서 제거 (맵에서 번호를 0으로 지움)
    // ===============================================
    static void removeBox(Box b) {
        b.removed = true;
        for (int i = b.r; i < b.r + b.h; i++)
            for (int j = b.c; j < b.c + b.w; j++)
                A[i][j] = 0;
    }

    // ===============================================
    //  박스를 맵에 다시 그림
    // ===============================================
    static void putBox(Box b) {
        b.removed = false;
        for (int i = b.r; i < b.r + b.h; i++)
            for (int j = b.c; j < b.c + b.w; j++)
                A[i][j] = b.k;
    }

    // ===============================================
    //  메인 실행 로직
    // ===============================================
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 박스 개수
        A = new int[N][N];                   // 맵

        // ---------------------------
        // 1. 박스 입력 + 초기 낙하 처리
        // ---------------------------
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());

            int r0 = 0;        // 처음에는 맨 위 행(0)에 놓고
            int c0 = c1 - 1;   // 입력은 1-based이므로 -1

            // 아래로 끝까지 떨어뜨림
            int[] res = moveBox(h, w, r0, c0, 0);
            Box b = new Box(k, h, w, res[0], res[1]);

            boxes.add(b);
            putBox(b);  // 맵에 그리기
        }

        // ---------------------------
        // 2. k 오름차순 정렬 (매 step 에서 pop 대상 찾기 용)
        // ---------------------------
        boxes.sort(Comparator.comparingInt(b -> b.k));

        StringBuilder out = new StringBuilder();

        // ---------------------------
        // 3. 총 M번 pop 시도
        // ---------------------------
        for (int turn = 0; turn < M; turn++) {

            boolean isLeft = (turn % 2 == 0); // 짝수 턴=왼쪽 pop

            // ---------------------------
            // 3-1. 이번 턴에서 pop 가능한 박스 찾기 (k 작은 순)
            // ---------------------------
            for (Box b : boxes) {
                if (b.removed) continue; // 이미 제거된 박스 skip

                removeBox(b); // 일단 맵에서 빼고 이동 테스트

                // 왼쪽으로 끝까지 밀기(d=1) / 오른쪽(d=2)
                int[] res = moveBox(b.h, b.w, b.r, b.c, isLeft ? 1 : 2);

                int rr = res[0];
                int cc = res[1];

                boolean canExit;

                // 왼쪽 pop 조건
                if (isLeft) {
                    canExit = (cc == 0);    // 열 0까지 도달하면 성공
                }
                // 오른쪽 pop 조건
                else {
                    canExit = (cc + b.w == N); // 오른쪽 끝에 닿으면 성공
                }

                if (canExit) {
                    // pop 성공 → 출력 + 박스 완전 제거 확정
                    out.append(b.k).append('\n');
                    break;
                } else {
                    // pop 실패 → 원위치 복구
                    putBox(b);
                }
            }

            // ---------------------------
            // 3-2. pop 후 남은 박스들 '중력' 처리
            //      아래에 있는 박스부터 처리해야 충돌이 없다!
            // ---------------------------
            boxes.sort((a, b) -> Integer.compare((b.r + b.h), (a.r + a.h)));

            for (Box b : boxes) {
                if (b.removed) continue;

                // 맵에서 박스 제거 후
                removeBox(b);

                // 아래로 끝까지 떨어뜨림
                int[] fall = moveBox(b.h, b.w, b.r, b.c, 0);
                b.r = fall[0];
                b.c = fall[1];

                // 다시 맵에 그리기
                putBox(b);
            }

            // 다음 pop 탐색을 위해 번호 오름차순 복구
            boxes.sort(Comparator.comparingInt(b -> b.k));
        }

        // ---------------------------
        // 4. pop된 박스 번호 순서대로 출력
        // ---------------------------
        System.out.print(out.toString());
    }
}
