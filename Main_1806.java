import java.util.*;
import java.io.*;
public class Main_1806 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int left = 0;
    int temp = 0;
    int minSum = Integer.MAX_VALUE;

    for(int right = 0; right < arr.length; right++){
      temp += arr[right];

      if(temp >= S){
        while(temp >= S){
          minSum = Math.min(minSum, right - left + 1);
          temp -= arr[left];
          left++;
        }
      }
    }
    if (minSum == Integer.MAX_VALUE) {
      System.out.println(0);
  } else {
      System.out.println(minSum);
  }

  }
  
}
