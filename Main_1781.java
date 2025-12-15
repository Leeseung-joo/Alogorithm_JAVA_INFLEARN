import java.util.*;
import java.io.*;
public class Main_1781{
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st;
    int[][] arr = new int[N][2];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      arr[i][0] = d; //day
      arr[i][1]  = c; //컵라면 개수
    }
    
    Arrays.sort(arr, (o1,o2) -> {
      if(o1[0] == o2[0]) return o2[1] - o1[1]; 
      return o1[0]-o2[0];
    });

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for(int i = 0; i < N; i++){

      pq.add(arr[i][1]);
      if(pq.size() > arr[i][0]){
        pq.poll();
      }

    }
    int result = 0;
    while(!pq.isEmpty()){
      result += pq.poll();
    }
    System.out.println(result);


  }
}