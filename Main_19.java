import java.util.*;
import java.io.*;
public class Main_19 {
  
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;

    ArrayList<Point> list = new ArrayList<>();

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int a =Integer.parseInt(st.nextToken());
      int b =Integer.parseInt(st.nextToken());
      int c =Integer.parseInt(st.nextToken());
      list.add(new Point(a,b,c));
    }

    list.sort((o1,o2) -> {
      return o2.n-o1.n; //밑면 기준으로 내림차순 정리
    });
    int[] dp = new int[N+1]; //i번째가 올라갔을 때의 최대 높이
    
    dp[0] = list.get(0).h;
    int answer = 0;
    answer = dp[0];
    for(int i = 1; i < N; i++){
      int max_h = 0;
      for(int j = i-1; j >= 0; j--){
        if(list.get(j).w > list.get(i).w && dp[j] > max_h){
          max_h = dp[j];
        }
      }
      dp[i] = max_h + list.get(i).h;
      answer = Math.max(dp[i], answer);
      System.out.println(answer);
    }






  }

  static class Point{
    int n,h,w;

    Point(int n, int h, int w){
      this.n = n;
      this.h = h;
      this.w = w;
    }
  }
}
