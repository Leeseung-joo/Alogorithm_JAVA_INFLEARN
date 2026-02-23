import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int idx, cost;
        Node(int idx, int cost) { this.idx = idx; this.cost = cost; }
        @Override public int compareTo(Node o) { return Integer.compare(this.cost, o.cost); }
    }

    static ArrayList<List<Node>> graph = new ArrayList<>();
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = dijkstra(N, 1);
        int[] distV1 = dijkstra(N, v1);
        int[] distV2 = dijkstra(N, v2);

        long case1 = add(dist1[v1], distV1[v2], distV2[N]);
        long case2 = add(dist1[v2], distV2[v1], distV1[N]);

        long ans = Math.min(case1, case2);
        System.out.println(ans >= INF ? -1 : ans);
    }

    static int[] dijkstra(int N, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.idx;
            int curCost = cur.cost;

            if (curCost != dist[u]) continue;

            for (Node nxt : graph.get(u)) {
                int v = nxt.idx;
                int nd = dist[u] + nxt.cost;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new Node(v, nd));
                }
            }
        }
        return dist;
    }

    static long add(int a, int b, int c) {
        if (a == INF || b == INF || c == INF) return INF;
        return (long)a + b + c;
    }
}