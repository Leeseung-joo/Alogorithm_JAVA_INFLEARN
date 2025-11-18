
import java.util.*;
import java.io.*;
public class Main_3 {

  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] st = br.readLine().split(" ");
    int maxLength = 0;
    for(int i = 0; i < st.length; i++){
        
      if(st[i].length() > maxLength){
        maxLength = st[i].length();
      }else if(st[i].length() == maxLength){
        
      }
    }

  }
}
