import java.util.*;
import java.io.*;

class Main_12845{
  public static void main(String[] args) throws IOException {
    
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

    for(int i = 0; i < N; i++){
      int a = sc.nextInt();
      q.add(a);
    }

    int sum = 0;
    while(q.size() > 1){
      int a = q.poll();
      int b = q.poll();

      sum += a + b;
      q.add(a);
    }
    System.out.println(sum);
  

  }
}