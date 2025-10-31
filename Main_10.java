import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
public class Main_10 {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    String str = st.nextToken();
    char target = st.nextToken().charAt(0);

    int[] answer = new int[str.length()];

    int p = 1000;
    for(int i = 0; i < str.length(); i++){
      if(str.charAt(i) == target){
        p = 0; 
        answer[i] = p;
      }else{
        p++;
        answer[i] = p;
      }
    }
    p = 1000;
    for(int i = str.length()-1; i >= 0; i--){
      if(str.charAt(i) == target){
        p = 0;
      }else{
        p++;
        answer[i] = Math.min(p, answer[i]);
      }
    }
    for (int i = 0; i < answer.length; i++) {
      System.out.print(answer[i] + " ");
    }

  }
}
