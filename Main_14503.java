import java.util.*;
import java.io.*;
public class Main_14503{
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int cnt = 0;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        simulate(r,c,d);
        

        System.out.println(cnt);







    }
    static void simulate(int x,int y, int d){
       if(map[x][y] == 0 && !visited[x][y]){
        cnt++;
        visited[x][y] = true;
       }

        
        while(true){
           
            boolean flag = false;

            for(int i = 0; i < 4; i++){

                d = (d+3)%4;
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(!isRange(nx,ny)) continue;

                if(map[nx][ny] == 0 && !visited[nx][ny]){
                    x = nx;
                    y = ny;
                    flag = true;
                    visited[x][y] = true; //청소 완료
                    cnt++;
                    break;
                }
            }
            if(!flag){
                x = x - dx[d];
                y = y - dy[d];
                if(!isRange(x,y) || map[x][y] == 1){
                    break;
                }
            } 
        }
        return;
    }

    static boolean isRange(int x, int y){
        return x >= 0  && x < N && y >= 0 && y < M;
    }
}