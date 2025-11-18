import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class Main_41 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    StringTokenizer st;


    for(int i =0; i < t; i++){
      int max = -1;
      int sum = 0;
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      int[] arr = new int[N];
      int left = 0;
      int right = N-1;
    
      
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++){
        arr[j] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);

    while(left < right){
      sum = arr[left]+arr[right];
      if(sum <= M){
        max = Math.max(max, sum);
        left++;
      }else{
        right--;
      }
    }
    System.out.println("#" + String.valueOf(i+1) + " " + max);
      
    }
    
}
  }


