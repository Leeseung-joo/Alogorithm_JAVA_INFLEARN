import java.util.*;
import java.io.*;

public class Main_40 {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    StringTokenizer st;

    for(int i = 0; i < tc; i++){
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int dis =  Integer.parseInt(st.nextToken());

      int[] arr = new int[N];
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++){
        arr[j] = Integer.parseInt(st.nextToken());
      }

      int right = 0;
      int cnt = 0; //세워야하는 차원 관문 수
      int min = 0;
      int currentCnt = 0;  //현재 0 카운트 수
      while(right < N-1){
        if(arr[right] == 0){
          currentCnt++;
          if(currentCnt >= dis){
            cnt++;
            currentCnt = 0;
          }
        }else{
          currentCnt = 0;
        }
        right++;
      }

      System.out.println("#" + String.valueOf(i+1) + " " + cnt);



    }
  }
  
}
