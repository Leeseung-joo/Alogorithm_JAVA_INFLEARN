package string;

import java.util.*;
import java.io.*;

public class Main_2 {
    public static void main(String[] args)throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      char[] ch = br.readLine().toCharArray();
      StringBuilder sb = new StringBuilder();
      for(char c : ch){
        if(Character.isLowerCase(c)) sb.append(Character.toUpperCase(c));
        else{
          sb.append(Character.toLowerCase(c));
        }
      }
      System.out.println(sb.toString());;

    } //대문자 65부터 90, 소문자 97부터 122
}
