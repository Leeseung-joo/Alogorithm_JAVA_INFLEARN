import java.util.*;
import java.io.*;

public class Main_14719 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());

    int result = 0;

    int[] arr = new int[W];

    for(int i = 0; i <  W; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] leftMax = new int[W];
    leftMax[0] = 0;

    for(int i = 1; i <  W; i++){
      leftMax[i] = Math.max(leftMax[i-1], arr[i]);
    }

    int[] rightMax = new int[W];
    rightMax[W-1] = arr[W-1];
    for(int i = W-2; i >= 0; i--){
      rightMax[i] = Math.max(rightMax[i+1], arr[i]);
    }

    for(int i = 1; i <= W-2; i++){ //양 끝은 물이 고일 수 없음
      int waterLevel = Math.min(leftMax[i], rightMax[i]);
      
      if(waterLevel > arr[i]){
        result += waterLevel - arr[i];
      }
    }
    System.out.println(result);



  }
}
