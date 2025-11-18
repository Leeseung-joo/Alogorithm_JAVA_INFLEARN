import java.util.*;
import java.io.*;
public class Main_47 {
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K =  Integer.parseInt(st.nextToken());

    int[] arr = new int[N+1];
    
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

    for(int i = 0; i < N; i++){
      for(int j = i+1; j <N; j++){
        for(int k = j+1; k < N; k++){
          set.add(arr[i]+arr[j] + arr[k]);
        }
      }
    }
    int cnt = 0;
    for(int x : set){
      cnt++;
      if(cnt == K) {
        System.out.println(x);
        return;
    }
    System.out.println(-1);



  }
  }

 
  
}
