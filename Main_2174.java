import java.util.*;
import java.io.*;

public class Main_2174 {

    static int A, B;
    static int[][] map;          // map[y][x] = robotNum (1..N), 0 = empty
    static Robot[] robots;       // 1..N

    // dir: 0=N, 1=E, 2=S, 3=W
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static class Robot {
        int num;
        int x, y;
        int dir;

        Robot(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()); // width (x: 1..A)
        B = Integer.parseInt(st.nextToken()); // height (y: 1..B)

        map = new int[B + 1][A + 1];

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        robots = new Robot[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            int dir = dirToInt(d);

            robots[i] = new Robot(i, x, y, dir);
            map[y][x] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int robotNum = Integer.parseInt(st.nextToken());
            char command = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            boolean ok = simulation(robotNum, command, repeat);
            if (!ok) return; // crash printed inside
        }

        System.out.println("OK");
    }

    static int dirToInt(String d) {
        switch (d) {
            case "N": return 0;
            case "E": return 1;
            case "S": return 2;
            default:  return 3; // "W"
        }
    }

    static boolean simulation(int num, char command, int repeat) {
        Robot r = robots[num];

        if (command == 'L' || command == 'R') {
            int turn = repeat % 4;
            if (command == 'L') {
                r.dir = (r.dir - turn) % 4;
                if (r.dir < 0) r.dir += 4;
            } else { // 'R'
                r.dir = (r.dir + turn) % 4;
            }
            return true;
        }

        // command == 'F'
        for (int step = 0; step < repeat; step++) {
            int nx = r.x + dx[r.dir];
            int ny = r.y + dy[r.dir];

            // wall crash
            if (nx < 1 || nx > A || ny < 1 || ny > B) {
                System.out.println("Robot " + r.num + " crashes into the wall");
                return false;
            }

            // robot crash
            if (map[ny][nx] != 0) {
                System.out.println("Robot " + r.num + " crashes into robot " + map[ny][nx]);
                return false;
            }

            // move
            map[r.y][r.x] = 0;
            map[ny][nx] = r.num;
            r.x = nx;
            r.y = ny;
        }

        return true;
    }
}
