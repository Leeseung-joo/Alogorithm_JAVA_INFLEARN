import java.util.*;
import java.io.*;

public class Main_55 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < arr.length; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    int left = 0;
    int right = arr.length-1;
    int mid = -1;
    while(left <= right){
      mid = (left + right)/2;
      
      if(arr[mid] > M) right = mid -1;
      else if(arr[mid] < M) left = mid+1;
      else break;
    }
    System.out.println(mid+1);
  }
  
}
