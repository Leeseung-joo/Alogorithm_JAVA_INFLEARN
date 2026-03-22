import java.util.*;
public class Main_349 {




    static class Node implements Comparable<Node> {
        int to;
        long cost;

        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;   // a 배열의 인덱스 기준 방문 체크
    static int N, K;
    static int[] a;
    static long answer;
    static final long INF = Long.MAX_VALUE / 4;

    public long solution(int N, int[] u, int[] v, int[] x, int[] y, int K, int[] a) {
        this.N = N;
        this.K = K;
        this.a = a;
        this.answer = INF;

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < u.length; i++) {
            graph.get(u[i]).add(new Node(v[i], x[i]));
            graph.get(v[i]).add(new Node(u[i], y[i]));
        }

        visited = new boolean[K];

        // 1에서 첫 번째 방문점 하나를 고르고 시작
        for (int i = 0; i < K; i++) {
            long firstCost = dijkstra(1, a[i]);
            if (firstCost >= INF) continue;

            visited[i] = true;
            dfs(a[i], firstCost, 1);
            visited[i] = false;
        }

        return answer;
    }

    static void dfs(int current, long totalCost, int count) {
        if (count == K) {
            answer = Math.min(answer, totalCost);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;

            long dist = dijkstra(current, a[i]);
            if (dist >= INF) continue;

            visited[i] = true;
            dfs(a[i], totalCost + dist, count + 1);
            visited[i] = false;
        }
    }

    static long dijkstra(int start, int destination) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > dist[now.to]) continue;

            if (now.to == destination) {
                return dist[destination];
            }

            for (Node next : graph.get(now.to)) {
                long nextCost = now.cost + next.cost;

                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.offer(new Node(next.to, nextCost));
                }
            }
        }

        return dist[destination];
    }

}
