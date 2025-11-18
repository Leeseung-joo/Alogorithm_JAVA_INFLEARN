import java.util.*;
import java.io.*;

public class Main_43 {
  public static void main(String[] args) {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N+1];
    for(int i = 1; i <= N; i++){
      arr[i] =  Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr);

    int max = 0;
    for(int i = 1; i <= N; i++){
      max = Math.max(max, arr[i]*(N-i+1));
    }
    System.out.println(max);






  }
  
}
