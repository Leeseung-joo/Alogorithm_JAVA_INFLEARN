import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static final int INF = 1_000_000;
	
    static int V, E, K;
    static int[] dist;
    static ArrayList[] adjList;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        
        initDistArr();
        initAdjList();
        
        for (int i = 1; i <= E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	adjList[u].add(new Edge(v, w));
        }
  
        dijkstra();
        
        for (int i = 1; i <= V; i++) {
        	if (dist[i] == INF)
        		sb.append("INF").append('\n');
        	else 
        		sb.append(dist[i]).append('\n');
        }
        System.out.println(sb);
    }
    
    private static void initDistArr() {
    	dist = new int[V + 1];
    	for (int i = 1; i <= V; i++) {
    		dist[i] = INF;
    	}
    }
    
    private static void initAdjList() {
    	adjList = new ArrayList[V + 1];
    	for (int i = 0; i <= V; i++) {
    		adjList[i] = new ArrayList<>();
    	}
    }
    
    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        // 시작점 설정
        dist[K] = 0;
        pq.offer(new Edge(K, 0));
        
        while (!pq.isEmpty()) {
        	Edge now = pq.poll();
        	
        	// 더 큰 가중치로 도착한 경우
        	if (dist[now.target] < now.cost) {
        		continue;
        	}
        	
        	int size = adjList[now.target].size();
        	for (int i = 0; i < size; i++) {
        		Edge next = (Edge) adjList[now.target].get(i);
        		
        		if (dist[next.target] > dist[now.target] + next.cost) {
        			dist[next.target] = dist[now.target] + next.cost;
        			pq.offer(new Edge(next.target, dist[next.target]));
        		}
        	}
        }
    }
    
    static class Edge implements Comparable<Edge> {
    	int target, cost;
    	
    	public Edge(int target, int cost) {
    		this.target = target;
    		this.cost = cost;
    	}

		@Override
		public int compareTo(Edge edge) {
			return this.cost - edge.cost;
		}
    }
  }