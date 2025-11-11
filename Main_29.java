import java.io.*;
import java.util.*;

public class Main_29 {

  public static void main(String[] args) throws IOException{

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      StringTokenizer st; 

      int[][] arr = new int[N+2][N+2];
      for(int i = 0; i < N+2; i++){
        arr[0][i] = 0;
        arr[i][0] = 0;
        arr[N+1][i] = 0;
        arr[i][N+1] = 0;
      }
      for(int i = 1; i <N+1; i++){
        st = new StringTokenizer(br.readLine());
        for(int j = 1; j <N+1; j++){
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      int sum = 0;
      for(int i = 1; i < N+1; i++){
        for(int j= 1; j < N+1; j++){
          if(arr[i][j] > arr[i-1][j] && arr[i][j] > arr[i][j-1] && arr[i][j] > arr[i+1][j] &&arr[i][j] >  arr[i][j+1]){
            sum++;
          }
        }
      }
      System.out.println(sum);
  } 
  
}
