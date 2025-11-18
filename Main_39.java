import java.io.IOException;
import java.util.*;
import java.io.*;
public class Main_39 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] arr = new int[N];
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int left = 0;
    int length = 0;
    int max = Integer.MIN_VALUE;
    int zeroCount = 0;

    for(int right = 0; right < N; right++){
      if(arr[right] == 0) zeroCount++;

      while(zeroCount > k){
        if(arr[left] == 0) zeroCount--;  //0이 K개를 초과하면 왼쪽을 줄여서 다시 K이하로 맞춤
        left++;
      }

      max = Math.max(max, right -left +1);

    }
    System.out.println(max);

  }
  
}
