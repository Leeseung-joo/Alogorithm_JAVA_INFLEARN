import java.util.*;
import java.io.*;

public class Main_65 {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;

    ArrayList<Point> list = new ArrayList<>();

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      list.add(new Point(s,e));
    }

    int cnt = 0;

    Collections.sort(list, (o1,o2) -> {
      if(o1.end == o2.end) return o1.start -o2.start;
      return o1.end - o2.end;
   //시작 시간이 빠른것부터, 같다면 
    });


    int current = 0;
    for(Point p : list){
      if(current <= p.start){
        current = p.end;
        cnt++;
      }
      
    }
    System.out.println(cnt);
  }


  static class Point{
    int start;
    int end;

    Point(int start, int end){
      this.start = start;
      this.end = end;
    }
  }
}
