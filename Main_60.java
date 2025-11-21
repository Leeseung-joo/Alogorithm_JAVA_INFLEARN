import java.util.*;
import java.io.*;

public class Main_60 {
  static int weight;
  static int max = 0;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(br.readLine());
    }
    
    dfs(C,0,arr,weight);
    System.out.println(max);
  }

  static void dfs(int limit, int L,int[] arr, int weight){
    if(max < weight) max = weight;
    if(L == arr.length) return;

    if(limit > weight + arr[L]){
      dfs(limit, L+1,arr, weight + arr[L]);
    }
      dfs(limit, L+1, arr,weight);

  }
  
}
