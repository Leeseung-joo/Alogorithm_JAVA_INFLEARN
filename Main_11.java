import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
public class Main_11 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringBuilder sb = new StringBuilder();

    int num = 1;
    String temp = String.valueOf(str.charAt(0));
    for(int i = 1; i < str.length(); i++){
        if(temp.equals(String.valueOf(str.charAt(i)))){
          num++;
        }else{
          sb.append(temp);
          
          if(num >= 2){
            sb.append(String.valueOf(num));
            num = 1;
          }
          temp = String.valueOf(str.charAt(i));
        }
    }
    sb.append(temp);
    if(num >= 2) sb.append(String.valueOf(num));
    System.out.println(sb.toString());
  }
}
