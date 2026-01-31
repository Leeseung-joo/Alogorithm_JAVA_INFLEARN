import java.util.*;
import java.io.*;

public class Main_21608 {

    static List<Student> students = new ArrayList<>();
    static int[][] map;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N;
    static boolean[][] like;

    static class Student {
        int num;
        List<Integer> favorites;

        Student(int num, List<Integer> favorites) {
            this.num = num;
            this.favorites = favorites;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        like = new boolean[N*N +1][N*N+1];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
                int f = Integer.parseInt(st.nextToken());
                list.add(f);
                like[n][f] = true;
            }

            students.add(new Student(n, list));
            simulate(i);
        }
        System.out.println(calcSatisfaction());
    }

    static void simulate(int a) {

        int bestR = -1, bestC = -1;
        int bestFriend = -1;
        int bestEmpty = -1;

        Student current = students.get(a);
        List<Integer> favorite = current.favorites;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] != 0) continue; // 이미 차있으면 스킵

                int friendCnt = 0;
                int emptyCnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (!inRange(nr, nc)) continue;

                    if (map[nr][nc] == 0) emptyCnt++;
                    else if (favorite.contains(map[nr][nc])) friendCnt++;
                }

                if (friendCnt > bestFriend
                        || (friendCnt == bestFriend && emptyCnt > bestEmpty)
                        || (friendCnt == bestFriend && emptyCnt == bestEmpty && i < bestR)
                        || (friendCnt == bestFriend && emptyCnt == bestEmpty && i == bestR && j < bestC)) {

                    bestFriend = friendCnt;
                    bestEmpty = emptyCnt;
                    bestR = i;
                    bestC = j;
                }
            }
        }

        map[bestR][bestC] = current.num;
    }

    static int calcSatisfaction() {
      int[] score = {0, 1, 10, 100, 1000};
      int sum = 0;
  
      for (int r = 0; r < N; r++) {
          for (int c = 0; c < N; c++) {
              int student = map[r][c];
              int cnt = 0;
  
              for (int k = 0; k < 4; k++) {
                  int nr = r + dr[k];
                  int nc = c + dc[k];
                  if (!inRange(nr, nc)) continue;
  
                  int neighbor = map[nr][nc];
                  if (like[student][neighbor]) cnt++;
              }
  
              sum += score[cnt];
          }
      }
      return sum;
  }  

    static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
