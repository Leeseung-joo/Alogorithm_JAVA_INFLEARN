import java.io.*;
import java.util.*;

public class Main_2006 {

    static class Player {
        int level;
        String name;
        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int P = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Player>> rooms = new ArrayList<>();

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            Player player = new Player(level, name);

            boolean joined = false;

            // 1) 들어갈 수 있는 방 찾기
            for (List<Player> room : rooms) {
                int baseLevel = room.get(0).level;
                if (room.size() < M && Math.abs(baseLevel - level) <= 10) {
                    room.add(player);
                    joined = true;
                    break;
                }
            }

            // 2) 못 찾으면 새 방 생성
            if (!joined) {
                List<Player> newRoom = new ArrayList<>();
                newRoom.add(player);
                rooms.add(newRoom);
            }
        }

        // 방별 정렬 + 출력
        StringBuilder sb = new StringBuilder();
        for (List<Player> room : rooms) {
            room.sort(Comparator.comparing(p -> p.name));

            if (room.size() == M) sb.append("Started!\n");
            else sb.append("Waiting!\n");

            for (Player p : room) {
                sb.append(p.level).append(" ").append(p.name).append("\n");
            }
        }

        System.out.print(sb);
    }
}
