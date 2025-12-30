import java.util.*;
import java.io.*;

public class Main_1339 {

  public static void main(String[] args) throws IOException{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String[] strArr = new String[N];
    int[] alphaArr = new int[26];
    
    for(int i = 0; i < N; i++){
      String str = br.readLine();
      int len = str.length();
      
      for(int j = 0; j < len; j++){
        char c = str.charAt(j);
        alphaArr[c-'A'] += (int)Math.pow(10,len-1-j);
      }
    }

    Arrays.sort(alphaArr);
    int multipleNum = 9;
    int index = 25;
    int sum = 0;

    while(alphaArr[index] > 0){
      sum += alphaArr[index] * multipleNum;
      index--;
      multipleNum--;
    }
    System.out.println(sum);
  }
  
}
