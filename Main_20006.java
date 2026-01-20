import java.io.*;
import java.util.*;

public class Main {

    static class Player {
        int level;
        String name;
        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }

    static class Room {
        int baseLevel;               // 방 기준 레벨(첫 입장자 레벨)
        List<Player> players = new ArrayList<>();
        Room(int baseLevel) {
            this.baseLevel = baseLevel;
        }
        boolean canJoin(int level, int m) {
            return players.size() < m && Math.abs(baseLevel - level) <= 10;
        }
        void add(Player p) {
            players.add(p);
        }
        boolean isFull(int m) {
            return players.size() == m;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방 정원

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            Player player = new Player(level, name);

            boolean placed = false;
            for (Room room : rooms) {
                if (room.canJoin(level, m)) {
                    room.add(player);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                Room newRoom = new Room(level);
                newRoom.add(player);
                rooms.add(newRoom);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Room room : rooms) {
            if (room.isFull(m)) sb.append("Started!\n");
            else sb.append("Waiting!\n");

            room.players.sort(Comparator.comparing(a -> a.name));

            for (Player pl : room.players) {
                sb.append(pl.level).append(" ").append(pl.name).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}
