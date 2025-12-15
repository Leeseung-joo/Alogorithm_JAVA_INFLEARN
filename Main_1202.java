import java.util.*;
import java.io.*;

public class Main_1202 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] jw = new int[N][2];

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int V = Integer.parseInt(st.nextToken());

      jw[i][0] = M; //보석의 무게
      jw[i][1] = V; //보석의 가치
    }

    int[] bag = new int[K];
    for(int i = 0; i < K; i++){
      int C = Integer.parseInt(br.readLine());
      bag[i] = C;
    }
    
    Arrays.sort(jw, (o1,o2) -> {
      return o1[0] - o2[0];   // 무게로 오름ㅍ차순
    });

    Arrays.sort(bag); // 오름차순

    //가방 여러개, 보석 가치와 무게가 있음
    // 무거운거 담을 수 있는 가방에 
    
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
  
    long result = 0;
    int idx = 0;
    for(int i = 0; i < K; i++){
      while(idx < N && jw[idx][0] <= bag[i]){
        pq.add(jw[idx][1]);
        idx++;
      }
      if(!pq.isEmpty()){
        result += pq.poll();
      }
    }
    System.out.println(result);

}
}