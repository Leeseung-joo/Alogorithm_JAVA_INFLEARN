import java.util.*;
import java.io.*;

public class Main_33 {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int M = Integer.parseInt(br.readLine());
    int[] arr1 = new int[M];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < M; i++){
      arr1[i] = Integer.parseInt(st.nextToken());
    }

    int p1 = 0;
    int p2 = 0;
    Arrays.sort(arr);
    Arrays.sort(arr1);

    ArrayList<Integer> list = new ArrayList<>();
    while(p1 < N && p2 < M){
      if(arr[p1] == arr1[p2]){
        list.add(arr[p1]);
        p1++;
        p2++;
      }else if(arr[p1] > arr1[p2]){
        p2++;
      }else{
        p1++;
      }
    }
    for(int i : list){
      System.out.print(i + " ");
    }

    

  }
  
}
