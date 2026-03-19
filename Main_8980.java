import java.util.*;
import java.io.*;

public class Main_8980 {

    static class Delivery {
        int from;
        int to;
        int box;

        Delivery(int from, int to, int box) {
            this.from = from;
            this.to = to;
            this.box = box;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭 용량
        int M = Integer.parseInt(br.readLine());  // 배송 정보 수

        List<Delivery> list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            list.add(new Delivery(from, to, box));
        }

        // 도착 마을이 빠른 순, 같으면 출발 마을이 빠른 순
        Collections.sort(list, (o1, o2) -> {
            if (o1.to == o2.to) {
                return o1.from - o2.from;
            }
            return o1.to - o2.to;
        });

// load[i] : i -> i+1 구간에 현재 실려 있는 박스 수
        int[] load = new int[N + 1];
        int result = 0;

        for(Delivery d : list){
          int maxLoaded = 0;

          for(int i = d.from; i < d.to; i++){
            maxLoaded = Math.max(maxLoaded, load[i]);
          }

          //현재 남은 공간보다 작아야지만 담을 수 잇음
          int canTake = Math.min(d.box,C -maxLoaded);

          for(int i = d.from; i < d.to; i++){
            load[i] += canTake;
          }
          result += canTake;
        }
        System.out.println(result);
        

      }
    }