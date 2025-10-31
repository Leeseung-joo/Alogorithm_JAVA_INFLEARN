import java.util.*;
import java.io.*;
public class Main_5 {

  public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String st = br.readLine();
    String answer = "";
    for(int i = 0; i < st.length(); i++){
      if(st.indexOf(st.charAt(i)) == i) answer += st.charAt(i);
    }
    System.out.println(answer);
  }
}
