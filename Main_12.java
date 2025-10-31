import java.util.*;
import java.io.*;
public class Main_12 {
  public static void main(String[] args) throws  IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int cnt = Integer.parseInt(br.readLine());
    String[] sArr = new String[cnt];
    String[] result = new String[cnt];
    String temp = br.readLine();
    for(int i = 0; i < cnt*7; i = i+7){
      sArr[i/7] = temp.substring(i, i+7);
    }

    for(int i = 0; i < cnt; i++){
      StringBuilder sb = new StringBuilder();
      for(int j = 0; j < 7; j++){
        if(sArr[i].charAt(j) == '#'){
          sb.append("1");
        }else{
          sb.append("0");
        }
      }
      String s = sb.toString();
      int k = Integer.parseInt(s,2); //63

      result[i] = String.valueOf((char)k);
    }

    System.out.println(String.join("", result));
    }

  }
  
