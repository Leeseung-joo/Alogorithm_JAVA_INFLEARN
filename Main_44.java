import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_44 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    char[] ch = br.readLine().toCharArray();
    HashMap<Character, Integer> map = new HashMap<>();

    for(int i = 0; i < N; i++){
      map.put(ch[i], map.getOrDefault(ch[i], 0) + 1);

    }
    int max = 0;
    String s = "";
    for(char c: map.keySet()){
      if(max < map.get(c)){
        max = map.get(c);
        s= String.valueOf(c);
      }
    }
    System.out.println(s);

  }
  
}
