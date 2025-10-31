
import java.util.*;
import java.io.*;

public class Main_8 {
  
  public static void main(String[] args) throws IOException {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  char[] c = br.readLine().toCharArray();
  StringBuilder sb = new StringBuilder();
  String flag = "YES";

  for(int i = 0; i < c.length; i++){
    if(Character.isAlphabetic(c[i])){//알파벳이면
      sb.append(String.valueOf(Character.toLowerCase(c[i])));
    }
  }
  String st = sb.toString();
  for(int i = 0; i <= st.length()/2; i++){
    if(st.charAt(i) != st.charAt(st.length()-1-i)) {
      flag = "NO";
      break;
  }
    
  }
  System.out.println(flag);
  }
}
//알파벳이외의 문자들은 무시, 대소문자 구분 x