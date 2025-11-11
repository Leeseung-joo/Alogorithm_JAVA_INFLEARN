import java.util.*;
import java.io.*;
public class Main_27{
  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] arr = new int[N];
    int[] rank = new int[N];
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
      rank[i] = 1;
    }

    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr.length; j++){
        if(arr[i] < arr[j]) rank[i]++;
      }
    }

    for(int r : rank){
      System.out.print(r + " ");
    }


  }
}