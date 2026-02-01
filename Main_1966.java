import java.util.*;
import java.io.*;
public class Main_1966 {

  static class Print{
    int order;
    int level; //중요도

    Print(int order, int level){
      this.order = order;
      this.level = level;
    }
  }
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
    
    for(int t = 0; t < T; t++){

      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken()); //궁금한 문서의 현재 위치(인덱스)

      int[] importance = new int[N];
      ArrayDeque<Print> q = new ArrayDeque<>();
      st = new StringTokenizer(br.readLine());
      List<Integer> list = new ArrayList<>();
      for(int i = 0; i < N; i++){
        
        int temp = Integer.parseInt(st.nextToken());
        importance[i] = temp;
        q.add(new Print(i, temp));
        list.add(temp);
      }

      Collections.sort(list, Collections.reverseOrder()); //
      int idx = 0;
      while(true){

        Print queue = q.poll();
        if(list.get(0) == queue.level){
          list.remove(0);
          idx++;
          if(M == queue.order){
            System.out.println(idx);
            break;
          }

        }else{
          q.add(queue);
        }
      }

      


    }
  }
  
}
