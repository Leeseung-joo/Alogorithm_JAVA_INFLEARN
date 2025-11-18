import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_46 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    HashMap<Integer, Integer> map = new HashMap<>();

    for(int i = 0; i < K; i++){
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }
    ArrayList<Integer> list = new ArrayList<>();
    list.add(map.size());
    

    for(int i =K; i < arr.length; i++){
      map.put(arr[i-K], map.getOrDefault(arr[i-K], 0) - 1);
      if(map.get(arr[i-K]) == 0) map.remove(arr[i-K]);
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    
      list.add(map.size());
    }
   
    for(int i : list){
      System.out.print(i + " ");
    }
  }
  
}
