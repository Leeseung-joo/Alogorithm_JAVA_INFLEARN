import java.io.*;
import java.util.*;
public class Main_14 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    int cnt = 0;
    int maxHeight = 0;
    for(int i = 0; i < n; i++){
      int present = Integer.parseInt(st.nextToken());
      if(present > maxHeight){
        cnt++;
        maxHeight = present;
      }
    }
    System.out.println(cnt);
  }
  
}
