import java.util.*;
import java.io.*;

public class Main_61 {
  static int max = 0;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M =  Integer.parseInt(st.nextToken());

    int[] score = new int[N];
    int[] time = new int[N];
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      score[i] = Integer.parseInt(st.nextToken());
      time[i] = Integer.parseInt(st.nextToken());
    } 
    dfs(score, time, 0, M, 0,0);  //현재인덱스, 제한 시간, 지금까지의 점수, 현재 사용시간
    System.out.println(max);
  }

  static void dfs(int[] score, int[] time, int idx, int M, int sum, int currentTime){
    if(max < sum) max = sum;
    if(idx == score.length) return;

    if(time[idx]+currentTime <= M){
      dfs(score, time, idx+1, M, sum+score[idx],time[idx]+currentTime);
    }
    dfs(score, time, idx+1, M, sum,currentTime);

  }
  
}
