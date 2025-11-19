import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_48 {
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] ch = br.readLine().toCharArray();

    ArrayDeque<Character> q = new ArrayDeque<>();
    for(char c : ch){
      if(c == '(') q.add('(');
      else{
        if(q.isEmpty()){
          System.out.println("NO");
          return;
        }
        q.pop();
      }
    }
    if(!q.isEmpty()){
      System.out.println("NO");
    }else{
      System.out.println("YES");
    }

  }
}
 