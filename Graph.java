import java.util.*;
public class Graph {

  static class Node{
    int end; //연결되는 정점
    int cost; //비용

    Node(int end, int cost){
      this.end = end;
      this.cost = cost;
    }
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    
    int V = sc.nextInt();
    int E = sc.nextInt();

    List<List<Node>> graph = new ArrayList<>();
    for(int i = 0; i < V+1; i++){
      graph.add(new ArrayList<Node>());
    }

    int a;
    int b;
    int cost;
    for(int i = 0; i < E; i++){
      a = sc.nextInt();
      b = sc.nextInt();
      cost = sc.nextInt();

      graph.get(a).add(new Node(b,cost));
      //만일 무향 그래프라면 반대의 상황도 추가한다.
      graph.get(b).add(new Node(a,cost));
    }
    
  }
  
}
