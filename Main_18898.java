import java.util.*;
import java.io.*;

public class Main_18898 {

    static int N, M, G, R;
    static int[][] map;
    static ArrayList<Point> candidates = new ArrayList<>();
    static int answer = 0;

    static int[] select;         // 후보들 중 G+R개를 뽑은 인덱스 저장
    static boolean[] greenPick;  // select 안에서 초록으로 쓸 위치 표시

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int x, y, color, time; // 0: green, 1: red

        Node(int x, int y, int color, int time) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                map[i][j] = temp;

                // 배양액을 놓을 수 있는 후보 위치만 저장
                if (temp == 2) {
                    candidates.add(new Point(i, j));
                }
            }
        }

        select = new int[G + R];
        greenPick = new boolean[G + R];

        // 1단계: 후보 중 G+R개 선택
        selectTotal(0, 0);

        System.out.println(answer);
    }

    static void selectTotal(int depth, int start) {
        if (depth == G + R) {
            Arrays.fill(greenPick, false);

            // 2단계: 뽑은 G+R개 중 G개를 초록으로 선택
            selectGreen(0, 0);
            return;
        }

        for (int i = start; i < candidates.size(); i++) {
            select[depth] = i;
            selectTotal(depth + 1, i + 1);
        }
    }

    // select[] 안에서 G개를 초록으로 뽑기
    static void selectGreen(int depth, int start) {
        if (depth == G) {
            int flowerCount = bfs();
            answer = Math.max(answer, flowerCount);
            return;
        }

        for (int i = start; i < G + R; i++) {
            greenPick[i] = true;
            selectGreen(depth + 1, i + 1);
            greenPick[i] = false;
        }
    }

    static int bfs() {
        int[][] greenTime = new int[N][M];
        int[][] redTime = new int[N][M];
        boolean[][] flower = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(greenTime[i], -1);
            Arrays.fill(redTime[i], -1);
        }

        Queue<Node> q = new LinkedList<>();

        // 시작점 세팅
        for (int i = 0; i < G + R; i++) {
            Point p = candidates.get(select[i]);

            if (greenPick[i]) {
                greenTime[p.x][p.y] = 0;
                q.offer(new Node(p.x, p.y, 0, 0));
            } else {
                redTime[p.x][p.y] = 0;
                q.offer(new Node(p.x, p.y, 1, 0));
            }
        }

        int flowerCount = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 이미 꽃이 된 칸이면 더 퍼지면 안 됨
            if (flower[cur.x][cur.y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 0) continue;     // 호수
                if (flower[nx][ny]) continue;       // 이미 꽃

                if (cur.color == 0) { // green
                    // 초록이 처음 도착하는 경우
                    if (greenTime[nx][ny] == -1) {
                        // 빨강이 같은 시간에 이미 도착했다면 꽃
                        if (redTime[nx][ny] == cur.time + 1) {
                            flower[nx][ny] = true;
                            flowerCount++;
                        }
                        // 빨강이 아직 안 왔다면 초록 퍼짐
                        else if (redTime[nx][ny] == -1) {
                            greenTime[nx][ny] = cur.time + 1;
                            q.offer(new Node(nx, ny, 0, cur.time + 1));
                        }
                    }
                } else { // red
                    // 빨강이 처음 도착하는 경우
                    if (redTime[nx][ny] == -1) {
                        // 초록이 같은 시간에 이미 도착했다면 꽃
                        if (greenTime[nx][ny] == cur.time + 1) {
                            flower[nx][ny] = true;
                            flowerCount++;
                        }
                        // 초록이 아직 안 왔다면 빨강 퍼짐
                        else if (greenTime[nx][ny] == -1) {
                            redTime[nx][ny] = cur.time + 1;
                            q.offer(new Node(nx, ny, 1, cur.time + 1));
                        }
                    }
                }
            }
        }

        return flowerCount;
    }
}