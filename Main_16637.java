import java.util.*;
import java.io.*;
public class Main_16637 {
  static int N; 
  static char[] expr;
  static int max = Integer.MIN_VALUE;
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    expr = br.readLine().toCharArray();

    dfs(0,expr[0] - '0');
    System.out.println(max);
    
  }

  static void dfs(int idx, int current){

    if (idx >= N - 1) {
      max = Math.max(max, current);
      return;
  }

  //괄호 없이 진행
  char op = expr[idx+1]; //연산자
  int nextNum = expr[idx+2] - '0';
  int result1 = calc(current,op,nextNum);
  dfs(idx+2,result1);

  //다음 연산을 괄호로 묶는 경우
  if(idx+4 < N){
    int bracket = calc(expr[idx+2] - '0', expr[idx+3], expr[idx+4] - '0');
    int result2 = calc(current,op,bracket);
    dfs(idx+4,result2);
  }


  }
  static int calc(int a, char op, int b) {
    if (op == '+') return a + b;
    if (op == '-') return a - b;
    return a * b;
  
}
}