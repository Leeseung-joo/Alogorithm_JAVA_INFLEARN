import java.util.*;
import java.io.*;

public class Main_52 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int S = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    
    ArrayDeque<Integer> q = new ArrayDeque<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
      map.put(arr[i], 0);
    }

    for(int num : arr){
      if(map.get(num) == 0 && q.size() < S){
        q.add(num);
        map.put(num, 1);
      }else if(map.get(num) == 1){ //캐시 히트
        q.remove(num);
        q.add(num); 
      } else if(map.get(num) == 0 && q.size() >= S){
        int n  = q.poll();
        map.put(n, 0);
        map.put(num, 1);
        q.add(num);
      
      }
    }
    while(!q.isEmpty()) {
      System.out.print(q.pollLast() + " ");
  }


  }

  
}
