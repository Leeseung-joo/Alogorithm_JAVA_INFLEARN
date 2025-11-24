import java.util.*;
import java.io.*;

public class Main_64 {
  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;

    ArrayList<Point> list = new ArrayList<>();

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      list.add(new Point(h,w));
    }
    
    Collections.sort(list, (o1,o2) -> {
      return o2.height - o1.height;
    });

    int maxWeight = 0;
    int cnt = 0;
    for(Point p : list){
      if(p.weight > maxWeight){
        cnt++;
        maxWeight = p.weight;
      }
     
    }
    System.out.println(cnt);
  }

  static class Point{
    int height;
    int weight;

    Point(int height, int weight){
      this.height = height;
      this.weight = weight;
    }
  }
}
