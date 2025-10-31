package string;
import java.util.*;
import java.io.*;
public class Main_5 {
  
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String st = br.readLine();
    String[] s = new String[st.length()];
    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < st.length(); i++){
      if(Character.isAlphabetic(st.charAt(i))){
        sb.append(String.valueOf(st.charAt(i)));
        s[i] = "";
        
      }
        else{
          s[i] = String.valueOf(st.charAt(i)); //애는 그대로
        }
      }
      sb.reverse();
      int idx = 0;
      for(int i = 0; i < s.length; i++){
        if(s[i].equals("")){
          s[i] = String.valueOf(sb.charAt(idx));
          idx++;
        }
      }
      for(String a : s){
        System.out.print(a);
      }
      
    }
  }

