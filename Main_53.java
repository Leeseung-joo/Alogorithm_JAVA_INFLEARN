import java.util.*;
import java.io.*;


public class Main_53 {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[N+1];
    int[] sorted = new int[N+1];

    for(int i = 1; i <= N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
      sorted[i] = arr[i];
    }

    Arrays.sort(sorted);
    int a = -1;
    int b = -1;
    for(int i = 1; i <= N;i++){
      if(arr[i] != sorted[i]){
        if(a == -1){
          a = i;
        }else{
          b = i;
        }
        
      }
      
    }
   
    
    System.out.print(a + " " + b);



  }
  
}
