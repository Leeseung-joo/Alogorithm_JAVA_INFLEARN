package string;

import java.util.*;
import java.io.*;
public class Main_4 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    

    String[] st = new String[n];
    for(int i = 0; i < n; i++){
      st[i] = br.readLine();
    }

    for(String x : st){
      String sb = new StringBuilder(x).reverse().toString();
      System.out.println(sb);
    }

  }
  
}
