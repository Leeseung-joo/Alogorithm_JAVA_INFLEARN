import java.util.*;
import java.io.*;

public class Main_1987 {
    static ArrayList<Character> list = new ArrayList<>();
    static char[][] map;
    static int R, C;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++){
            String line = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = line.charAt(j);
            }
        }

        // 시작칸 넣고 시작
        list.add(map[0][0]);
        dfs(0,0);

        System.out.println(ans);
    }

    static void dfs(int x,int y){
        // 현재까지 길이로 최댓값 갱신
        ans = Math.max(ans, list.size());

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!inRange(nx,ny)) continue;
            if(list.contains(map[nx][ny])) continue;

            list.add(map[nx][ny]);
            dfs(nx,ny);
            list.remove(list.size() - 1); // 마지막 원소 제거가 안전
        }
    }

    static boolean inRange(int x,int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
