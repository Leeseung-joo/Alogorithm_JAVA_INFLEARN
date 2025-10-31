import java.io.*;
import java.util.*;
public class Main_13 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int num = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int temp = 0;
    for(int i = 0; i < num; i++){
      int n = Integer.parseInt(st.nextToken());
      if(n > temp){
        System.out.print(n + " ");
      }
      temp = n;
    }


  }
}
