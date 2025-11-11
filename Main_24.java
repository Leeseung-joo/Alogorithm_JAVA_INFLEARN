import java.io.*;
import java.util.*;

public class Main_24 {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[] answer = new int[N];
    answer[0] = 1;
    answer[1] = 1;
    
    for(int i = 2; i < N; i++){
      answer[i] = answer[i-2] + answer[i-1];
    }

    System.out.println(answer[N-1]);
  }
  
}
