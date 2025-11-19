import java.io.*;
import java.util.*;

public class Main_50{
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] ch = br.readLine().toCharArray();

    Stack<Integer> stack = new Stack<>();
    for(char c : ch){
      if(Character.isDigit(c)) stack.push(c-48);
      else{
        int rt = stack.pop();
        int lt = stack.pop();
        if(c == '+') stack.push(lt+rt);
        if(c == '-') stack.push(lt-rt);
        else if(c == '*') stack.push(lt*rt);
        else if(c == '/') stack.push(lt/rt);
      }
     
      
    }
    int answer = stack.get(0);
    System.out.println(answer);

  }
}