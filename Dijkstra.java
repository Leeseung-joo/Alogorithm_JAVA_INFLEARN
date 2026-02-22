import java.util.*;
public class Dijkstra {

  static class Node {
    int idx;
    int cost;
  
    // 생성자
    Node(int idx, int cost) {
      this.idx = idx;
      this.cost = cost;
    }
  }
  
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int E  = sc.nextInt();

    int start = sc.nextInt(); //출발지점

    //인접리스트를 이용한 그래프 초기화
    List<List<Node>> graph = new ArrayList<>();

    	// 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기만 한다.
		for (int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<>());
		}

    for(int i = 0; i < E; i++){
      int a = sc.nextInt();
      int b = sc.nextInt();
      int cost = sc.nextInt();

      graph.get(a).add(new Node(b,cost));
    }

    boolean[] visited = new boolean[V+1];
    int[] dist = new int[V+1];

    for (int i = 0; i < V + 1; i++) {
			// 출발 지점 외 나머지 지점까지의 최소 비용은 최대로 지정해둔다.
			dist[i] = Integer.MAX_VALUE;
		}

    dist[start] = 0;

    //다익스트라 알고리즘 진행
    for(int i = 0; i < V; i++){

      int nodeValue = Integer.MAX_VALUE;
      int nodeIdx = 0;

      for(int j = 1; j < V+1; j++){

        if(!visited[j] && dist[j] < nodeValue){
          nodeValue = dist[j];
          nodeIdx = j;
        }
      }

      visited[nodeIdx] = true;

      for(int j = 0; j < graph.get(nodeIdx).size(); j++){
        Node adjNode = graph.get(nodeIdx).get(j);
        //인접 노드가 현재 가지는 최소 비용과 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교하여 더 작은 값으로 갱신
        if(dist[adjNode.idx] > dist[nodeIdx] + adjNode.cost){
          dist[adjNode.idx] = dist[nodeIdx] + adjNode.cost;
        }
      }
    
    }
    for (int i = 1; i < V + 1; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
		sc.close();

    
  }
  
}
