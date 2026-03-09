import java.util.*;
import java.io.*;
public class Main_1439 {

  public static void main(String[] args) throws IOException {
    

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();


    //덩어리 수가 적은 걸 바꿈

    int zero = 0;
    int one = 0;
    int prev = -1;
    if(line.charAt(0) == '0'){
      zero += 1;
      prev = 0;
    }else{
      one += 1;
      prev = 1;
    }
    for(int i = 1; i < line.length(); i++){
      if(line.charAt(i) == '1' && prev == 0){
        one += 1;
        prev = 1;
      }else if(line.charAt(i) == '0' && prev == 1){
        zero += 1;
        prev = 0;
      }
    }
    System.out.println(Math.min(zero,one));
  }
  
}
