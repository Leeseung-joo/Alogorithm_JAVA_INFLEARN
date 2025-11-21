import java.util.*;
import java.io.*;

public class Main_54 {

  public static void main(String[] args) throws IOException {
     
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;
    ArrayList<Point> list = new ArrayList<>();
    for(int i = 0; i < N; i++){

      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      list.add(new Point(a,b));

    }
    Collections.sort(list , (o1,o2) -> {
      if(o1.x == o2.x) return o1.y-o2.y;
      return o1.x-o2.x;
    });

    for(Point p : list){
      System.out.println(p.x + " " + p.y);
    }
  }
  static class Point{
    int x,y;

    Point(int x, int y){
      this.x = x;
      this.y = y;
    }
  }
  
}
