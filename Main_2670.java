import java.util.*;
import java.io.*;

public class Main_2670 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] pos = new int[N][2];
        int result=0;

        for(int i = 0; i < N; i++){
          String line = br.readLine();
          int p1 = Integer.parseInt(line.split(" ")[0]);
          int p2 = Integer.parseInt(line.split(" ")[1]);
          pos[i][0] = p1;
          pos[i][1] = p2;
        }

        Arrays.sort(pos, (o1,o2) -> {
          if(o1[0] == o2[0]) return o1[1]-o2[1];
          return o1[0]- o2[0];
        });

        int min = pos[0][0];
        int max = pos[0][1];
        int len = max - min;

        for(int i = 1; i < N; i++){
          if(min <= pos[i][0] && max >= pos[i][1]) continue;
          else if(max > pos[i][0]) len += pos[i][1]-max;  //현재 선의 시작점이 이전 선에 포함된다면
          else{
            len += pos[i][1] - pos[i][0];
          }
           min = pos[i][0];
            max = pos[i][1];
        }
        System.out.println(len);
  }
  
}
