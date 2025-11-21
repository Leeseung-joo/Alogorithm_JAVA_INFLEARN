import java.util.*;
import java.io.*;
public class Main_57 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int left = Arrays.stream(arr).max().getAsInt();
    int right = Arrays.stream(arr).sum(); 
    int mid = -1;
    while(left <= right){
      mid = (left+right) / 2;

      if(count(arr,mid) <= M){
        right = mid-1;
      }else{
        left = mid+1;
      }
    }
    System.out.println(left);
  }

  public static int count(int[] arr, int mid){
    int cnt = 1;
    int temp = 0;

    for(int i = 0; i < arr.length; i++){
      
      if(temp + arr[i] <= mid){
        temp += arr[i];
      }else{
        cnt++;
        temp = arr[i];
      }
    }
    return cnt;
  }
  
}
