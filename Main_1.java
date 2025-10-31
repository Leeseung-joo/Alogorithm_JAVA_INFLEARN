import java.util.*;
import java.io.*;
public class Main_1 {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine().toLowerCase();
    String st = br.readLine().toLowerCase();
    
    int cnt = 0;
    for(char c : input.toCharArray()){
      if(c == st.charAt(0)){
        cnt++;
      }
    }
    System.out.println(cnt);

  }
}