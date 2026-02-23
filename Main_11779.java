import java.util.*;
import java.io.*;
public class Main_11779 {
  static class Node{
    int idx;
    int cost;

    Node(int idx, int cost){
      this.idx = idx;
      this.cost = cost;
    }
  }
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    StringTokenizer st = null;

    ArrayList<List<Node>> graph = new ArrayList<>();

    for(int i = 0; i < N+1; i++){
      graph.add(new ArrayList<Node>());
    }

    
    for(int i = 0; i < M; i++){
      st = new StringTokenizer(br.readLine());
      
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      graph.get(start).add(new Node(end, c));

    }
    st = new StringTokenizer(br.readLine());
    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());




    int[] dist = new int[N+1]; //A에서 출발하여 도착하는 거리의 최소 비용
    int[] prev = new int[N + 1]; // 경로 복원용 (이전 도시)

    Arrays.fill(prev, -1);
    for(int i = 0; i < N+1; i++){
      dist[i] =Integer.MAX_VALUE;
    }
    dist[A] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost)); //우선순위큐에 비교기준 넣기
    pq.offer(new Node(A,0));

    while(!pq.isEmpty()){
      Node cur = pq.poll();

      int u = cur.idx;
      int curCost = cur.cost;

      if (curCost != dist[u]) continue;

      for(Node next: graph.get(u)){
        if(dist[next.idx] > dist[u] + next.cost){
          dist[next.idx] = dist[u] + next.cost;
          prev[next.idx] = u;
          pq.offer(new Node(next.idx, dist[next.idx]));
        }
      }
    }
    System.out.println(dist[B]);

    //경로 복원(역추적)

    ArrayList<Integer> path = new ArrayList<>();

    int cur = B;
    while(cur!= -1){
      path.add(cur);
      if(cur == A) break;
      cur = prev[cur];
    }

    Collections.reverse(path);

    System.out.println(path.size());
    StringBuilder sb = new StringBuilder();
    for(int city: path) sb.append(city).append(' ');
    System.out.println(sb.toString().trim());









  }
  
}
