import java.util.*;
import java.io.*;

public class Main_10217 {
    static int N, M, K;
    static ArrayList<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int to;
        int cost; // 지금까지 사용한 총 비용
        int time; // 지금까지 걸린 총 시간

        Node(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static class Edge {
        int to;
        int cost; // 이 간선을 타는 비용
        int time; // 이 간선을 타는 시간

        Edge(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                graph[from].add(new Edge(to, cost, time));
            }

            int answer = dijkstra();

            if (answer == INF) sb.append("Poor KCM").append('\n');
            else sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static int dijkstra() {
        int[][] dist = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[1][0] = 0;
        pq.offer(new Node(1, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.to][cur.cost] < cur.time) continue;

            for (Edge next : graph[cur.to]) {
                int nextCost = cur.cost + next.cost;
                int nextTime = cur.time + next.time;

                if (nextCost > M) continue;

                if (dist[next.to][nextCost] > nextTime) {
                    dist[next.to][nextCost] = nextTime;
                    pq.offer(new Node(next.to, nextCost, nextTime));
                }
            }
        }

        int answer = INF;
        for (int cost = 0; cost <= M; cost++) {
            answer = Math.min(answer, dist[N][cost]);
        }

        return answer;
    }
}