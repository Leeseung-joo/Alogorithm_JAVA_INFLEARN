import java.io.*;
import java.util.*;

public class Main_1854 {
    static int n, m, k;
    static List<City>[] list;
    static PriorityQueue<Integer>[] dis;

    static class City implements Comparable<City> {
        int t;  // to
        int w;  // weight (누적거리 or 간선가중치, 상황에 따라)
        public City(int t, int w) {
            this.t = t;
            this.w = w;
        }
        @Override
        public int compareTo(City o) {
            return this.w - o.w; // 전역 PQ에서는 누적거리 작은 게 먼저
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dis = new PriorityQueue[n + 1];
        list = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            dis[i] = new PriorityQueue<>(Collections.reverseOrder()); // 각 노드별: 최대힙 (k개 중 제일 큰 값이 peek)
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[f].add(new City(t, w)); // 여기서는 간선 가중치로 저장
        }

        solve(1);

        for (int i = 1; i <= n; i++) {
            if (dis[i].size() == k) System.out.println(dis[i].peek());
            else System.out.println(-1);
        }
    }

    static void solve(int start) {
        PriorityQueue<City> q = new PriorityQueue<>(); // 전역 PQ: (노드, 누적거리) 최소힙처럼 사용
        q.add(new City(start, 0));
        dis[start].add(0);

        while (!q.isEmpty()) {
            City pos = q.poll();
            int to = pos.t;
            int weight = pos.w; // start -> to 까지의 "누적거리"

            // 가지치기(선택): 이미 to에 k개가 꽉 차 있고, 그 중 최악값(최대값)보다 현재가 더 크면 확장할 가치가 거의 없음
            if (dis[to].size() == k && dis[to].peek() < weight) continue;

            for (City next : list[to]) {
                int nextTo = next.t;
                int nd = weight + next.w; // 누적거리 = 현재 누적 + 간선 비용

                // 1) 아직 k개 미만이면 그냥 넣기
                if (dis[nextTo].size() < k) {
                    dis[nextTo].add(nd);
                    q.add(new City(nextTo, nd));
                }
                // 2) k개 꽉 찼으면: 현재 저장된 k개 중 가장 큰 값(=peek)보다 더 좋은 nd면 교체
                else if (dis[nextTo].peek() > nd) {
                    dis[nextTo].poll();   // 최악(가장 큰 거리) 제거
                    dis[nextTo].add(nd);  // 더 좋은 후보 추가
                    q.add(new City(nextTo, nd));
                }
            }
        }
    }
}