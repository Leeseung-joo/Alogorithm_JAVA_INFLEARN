import java.util.*;
import java.io.*;
public class Main_13549{

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int MAX = 100001;
    int[] dist = new int[MAX];
    Arrays.fill(dist, -1);

    Deque<Integer> dq = new ArrayDeque<>();

    dq.offer(N);
    dist[N] = 0;

    while(!dq.isEmpty()){

    int cur = dq.poll();

    if(cur == K){
      System.out.println(dist[K]);;
      return;
    }

    int next = cur * 2;
    if(next < MAX && (dist[next] == -1 || dist[next] > dist[cur])){
      dist[next] = dist[cur];
      dq.offerFirst(next); //이게 중요!!!
    }


    // 2. +1 이동 (1초)
    next = cur + 1;
    if (next < MAX && (dist[next] == -1 || dist[next] > dist[cur] + 1)) {
        dist[next] = dist[cur] + 1;
        dq.offerLast(next);
    }

    // 3. -1 이동 (1초)
    next = cur - 1;
    if (next >= 0 && (dist[next] == -1 || dist[next] > dist[cur] + 1)) {
        dist[next] = dist[cur] + 1;
        dq.offerLast(next);
    }






    }

  }
}