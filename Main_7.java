import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_7 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] ch = br.readLine().toCharArray();
    String flag = "YES";
    for(int i = 0; i < ch.length; i++){
      if(Character.isUpperCase(ch[i])){
        ch[i] = Character.toLowerCase(ch[i]);
      }
    }
    for(int i = 0; i < ch.length/2; i++){
      if(ch[i]!= ch[ch.length-1-i]) flag = "NO";
    }
    System.out.println(flag);
    



  }
  
}
