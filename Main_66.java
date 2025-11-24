import java.util.*;
import java.io.*;

public class Main_66 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;

    ArrayList<Event> list = new ArrayList<>();

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int s  = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      

      list.add(new Event(s,+1)); //도착
      list.add(new Event(e,-1)); //퇴장
    }
    
    Collections.sort(list, (o1,o2) -> {
      if(o1.time == o2.time) return o1.type -o2.type;
      return o1.time - o2.time;
    });

    int current = 0;
    int maxCnt = 0;
    
    for(Event e: list){
      current += e.type;  //도착이면 +1, 퇴장이면 -1
      maxCnt = Math.max(maxCnt, current);
    }
    System.out.println(maxCnt);
  }

  static class Event{
    int time;
    int type; //도착 : 1, 퇴장: -1;
  
  
  Event(int time, int type){
    this.time = time;
    this.type = type;
  }
}
}