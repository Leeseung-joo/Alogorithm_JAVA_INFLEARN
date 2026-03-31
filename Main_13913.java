import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX];
        int[] prev = new int[MAX];
        boolean[] visited = new boolean[MAX];

        bfs(N, K, dist, prev, visited);

        System.out.println(dist[K]);

        ArrayDeque<Integer> path = new ArrayDeque<>();
        int cur = K;
        while (cur != N) {
            path.push(cur);
            cur = prev[cur];
        }
        path.push(N);

        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs(int start, int target, int[] dist, int[] prev, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == target) return;

            int[] next = {now - 1, now + 1, now * 2};

            for (int nx : next) {
                if (nx < 0 || nx >= MAX) continue;
                if (visited[nx]) continue;

                visited[nx] = true;
                dist[nx] = dist[now] + 1;
                prev[nx] = now;
                q.offer(nx);
            }
        }
    }
}