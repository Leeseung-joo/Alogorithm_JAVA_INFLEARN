import java.util.*;
import java.io.*;
public class Main_3273 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    

    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[N];
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int M = Integer.parseInt(br.readLine());

    Arrays.sort(arr);

    int left = 0;
    int right = N-1;
    int result = 0;
    while(left < right){

      int temp = arr[left]+arr[right];
      if(temp >M){
        right--;
      }else{

        if(temp == M){
          result++;
        }
        left++;
      }
    }
    System.out.println(result);

  }
  
}
