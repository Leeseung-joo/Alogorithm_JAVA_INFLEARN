import java.util.*;
import java.io.*;
public class Main_1753{
	static int V;
	static int E;
	static int start;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<Node>[] graph;


	static class Node implements Comparable<Node>{
		int to;
		int cost;

		Node(int to, int cost){
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o){
			return this.cost - o.cost;
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		start = Integer.parseInt(br.readLine());

		graph = new ArrayList[V+1];

		for(int i = 0; i <= V; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i = 0; i < E; i++){
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to,cost));
}

		int[] result = dijkstra(start);

		for(int i = 1; i <= V; i++){
			if(result[i] == INF){
				System.out.println("INF");
			}else{
				System.out.println(result[i]);
			}
		}

	}

	static int[] dijkstra(int start){

		boolean[] visited = new boolean[V+1];
		int[] dist = new int[V+1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.offer(new Node(start,0));

		while(!pq.isEmpty()){

			int nowVertex = pq.poll().to;

			if(visited[nowVertex]) continue;
			visited[nowVertex] = true;

			for(Node next: graph[nowVertex]){
				if(dist[next.to] > dist[nowVertex] + next.cost){
					dist[next.to] = dist[nowVertex] + next.cost;

					pq.offer(new Node(next.to, dist[next.to]));
				}
			}



		}

		return dist;




	}
}