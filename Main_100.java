import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static TreeSet<Long> set = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());   // always 100
        N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            set.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < Q - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 200) addLamp();
            else if (cmd == 300) removeLamp(Long.parseLong(st.nextToken()));
            else if (cmd == 400) System.out.println(calcMinPower());
        }
    }

    // ---------------------------
    // 200 - 가로등 추가
    // ---------------------------
    static void addLamp() {
        long bestGap = -1;
        long bestPos = -1;

        // 1) 왼쪽 끝 구간(1 ~ 첫 가로등)
        long first = set.first();
        long gap = first - 1;
        if (gap > 0) {
            long pos = 1;  // 왼쪽 끝은 항상 1이 최적
            if (gap > bestGap || (gap == bestGap && pos < bestPos)) {
                bestGap = gap;
                bestPos = pos;
            }
        }

        // 2) 중간 구간
        Long prev = null;
        for (Long cur : set) {
            if (prev != null) {
                long d = cur - prev;
                if (d > 1) {
                    long pos = (prev + cur) / 2;
                    if ((prev + cur) % 2 != 0) pos++; // 올림 처리

                    long gapSize = Math.min(pos - prev, cur - pos);

                    if (gapSize > bestGap || (gapSize == bestGap && pos < bestPos)) {
                        bestGap = gapSize;
                        bestPos = pos;
                    }
                }
            }
            prev = cur;
        }

        // 3) 오른쪽 끝 구간(마지막 ~ N)
        long last = set.last();
        gap = N - last;
        if (gap > 0) {
            long pos = N; // 오른쪽 끝은 항상 N
            if (gap > bestGap || (gap == bestGap && pos < bestPos)) {
                bestGap = gap;
                bestPos = pos;
            }
        }

        set.add(bestPos);
    }

    // ---------------------------
    // 300 - 삭제
    // ---------------------------
    static void removeLamp(long x) {
        set.remove(x);
    }

    // ---------------------------
    // 400 - 최소 소비 전력
    // ---------------------------
    static long calcMinPower() {
        long maxGap = 0;

        // 1) 왼쪽 끝
        long first = set.first();
        maxGap = Math.max(maxGap, first - 1);

        // 2) 중간
        Long prev = null;
        for (Long cur : set) {
            if (prev != null) {
                long midGap = (cur - prev) / 2;
                maxGap = Math.max(maxGap, midGap);
            }
            prev = cur;
        }

        // 3) 오른쪽 끝
        long last = set.last();
        maxGap = Math.max(maxGap, N - last);

        return maxGap * 2;
    }
}
