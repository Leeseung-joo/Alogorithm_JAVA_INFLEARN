import java.util.*;
import java.io.*;
public class Main_2110 {

  public static void main(String[] args) throws Exception {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr);
    int left = 1;
    int right = arr[N-1] - arr[0]; 
    int answer = 0;
    while(left <= right){

      int mid = (left+right)/2;

      if(calculate(arr, mid) >= C){
        answer = mid; // 가능할 때만 갱신
        left = mid+1;
        
      }else{
        right = mid -1;
      }
      
    }
    System.out.println(answer);

  }

  static int calculate(int[] arr, int mid){
    int cnt = 1;
    int temp = arr[0];
    for(int i = 1; i < arr.length; i++){
      if(arr[i] - temp < mid){
        continue;
      }else{
        cnt++;
        temp = arr[i];
      }
    }
    return(cnt);
  }
  
}
