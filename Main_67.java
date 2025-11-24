import java.util.*;
import java.io.*;

public class Main_67 {

    static class Lecture {
        int money;
        int day;

        Lecture(int money, int day) {
            this.money = money;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 강연료
            int d = Integer.parseInt(st.nextToken()); // 마감일
            lectures[i] = new Lecture(m, d);
        }

        // 마감일 기준 오름차순 정렬
        Arrays.sort(lectures, (a, b) -> {
            if (a.day == b.day) return a.money - b.money;
            return a.day - b.day;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소 힙

        // 강연 선택
        for (Lecture lec : lectures) {
            pq.offer(lec.money);
            if (pq.size() > lec.day) {
                pq.poll(); // 가장 돈 적은 강연 제거
            }
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
