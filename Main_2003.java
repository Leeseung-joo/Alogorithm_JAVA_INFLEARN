import java.util.*;
import java.io.*;

public class Main_2003 {


  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int result = 0;
    int[] arr = new int[N+1];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 0; i < N; i++){
      int temp = arr[i];
      if(temp == M){
        result++;
        continue;
      }
      for(int j = i+1; j < N; j++){
        temp += arr[j];
        if(temp == M){
          result ++;
          continue;
        }
      }
    }
    System.out.println(result);
  }
  
}
