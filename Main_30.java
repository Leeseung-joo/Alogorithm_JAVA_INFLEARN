import java.util.*;
import java.io.*;

public class Main_30 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    

    int N = Integer.parseInt(br.readLine());
    int[][] arr = new int[N+1][5];
    for(int i = 1; i <= N; i++){
      st = new StringTokenizer(br.readLine());
      for (int k = 0; k < 5; k++) {
          arr[i][k] = Integer.parseInt(st.nextToken());
      }
  }
  

    int answer = 0;
    int max = Integer.MIN_VALUE;

    for(int i = 1; i <=N; i++){
      int cnt = 0;
      for(int j = 1; j<= N; j++){
        for(int k = 0; k < 5; k++){
          if(arr[i][k] == arr[j][k]){
            cnt++;
            break;
          }
        }

        }
        if(cnt > max){
          max = cnt;
          answer = i;
        }else if(cnt == max && answer > i){
          answer = i;
        }
      }
      System.out.println(answer);
    }



  }
  

//1이상 9이하 