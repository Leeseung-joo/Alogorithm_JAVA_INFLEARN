import java.util.*;
import java.io.*;
public class Main_11000{
  public static void main(String[] args) throws IOException {
    

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;
    ArrayList<Event> list = new ArrayList<>();

    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      
      list.add(new Event(1,start));
      list.add(new Event(-1,end));
    }


    Collections.sort(list, (o1,o2) ->{
      if(o1.time == o2.time){
        return o1.type - o2.type;
      }
      return o1.time - o2.time;
    });

    int max = 0;
    int current = 0;
    for(Event e: list){
      current += e.type;
      if(current > max){
        max = current;
      }
    }
    System.out.println(max);
  }

  static class Event{
    int type; //시작: 1, 끝: -1
    int time; //시간



      Event(int type, int time){
        this.type = type;
        this.time = time;
      }

    
  }
}