import java.io.IOError;
import java.io.IOException;
import java.util.*;
import java.io.*;
public class Main_42 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[N+1];
    for(int i = 1; i <= N;i ++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    int sum = 0;
    int temp = 0;
    
    for(int i = 1; i <= N; i++){
      sum += arr[i];
      if(i >= 2){
        temp += arr[i-1];
        sum += temp;
      }
    }
    System.out.println(sum);
  }
  
}
