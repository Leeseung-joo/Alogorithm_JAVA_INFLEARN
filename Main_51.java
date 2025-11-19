import java.io.*;
import java.util.*;

public class Main_51 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        ArrayDeque<Point> q = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            q.add(new Point(i, arr[i]));   // index, priority(target) 같이 저장
        }

        int cnt = 0;

        while (true) {
            boolean flag = true;      // 매 턴마다 초기화

            Point temp = q.poll();    // 맨 앞 문서 하나 꺼냄

            // 큐 안에 temp보다 우선순위가 높은 문서가 있는지 확인
            for (Point p : q) {
                if (p.target > temp.target) {   // 우선순위 비교
                    q.add(temp);                // 뒤로 보냄
                    flag = false;
                    break;
                }
            }

            if (flag) {              // 출력되는 경우
                cnt++;
                if (temp.index == M) {   // 내가 찾던 그 인덱스면 종료
                    System.out.println(cnt);
                    return;
                }
            }
        }
    }

    static class Point {
        int index;   // 원래 문서 위치
        int target;  // 우선순위 (이름이 좀 헷갈리지만 그대로 둠)

        Point(int index, int target) {
            this.index = index;
            this.target = target;
        }
    }
}
