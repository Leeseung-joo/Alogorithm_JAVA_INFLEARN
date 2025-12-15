import java.util.*;
import java.io.*;

public class Main_2109 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;
    
    int[][] arr = new int[N][2];


    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      arr[i][0] = d; //day
      arr[i][1] = p; //pay
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    Arrays.sort(arr, (o1,o2) -> {
      if(o1[0] == o2[0]) return o2[1]-o1[1];
      return o1[0]-o2[0];
    });
    //날짜 순으로 정렬 같을 경우, p값이 큰거먼저 정렬되게(내림차순)

    int currentDay = 0;
    int result = 0;

    for(int i = 0; i < N; i++){
      pq.add(arr[i][1]);
      
      if(pq.size() > arr[i][0]){
        pq.poll();
      }
    }
    while(!pq.isEmpty()){
      result += pq.poll();
    }
    System.out.println(result);
  }
  
}
