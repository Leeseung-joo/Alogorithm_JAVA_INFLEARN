import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int time;
        int people;

        Pair(int time, int people) {
            this.time = time;
            this.people = people;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Pair[] requests = new Pair[N];
        Pair[] buses = new Pair[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 탑승 인원
            int b = Integer.parseInt(st.nextToken()); // 최대 대기 가능 시간
            requests[i] = new Pair(b, a); // (시간, 인원) 순으로 저장
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()); // 버스 정원
            int d = Integer.parseInt(st.nextToken()); // 도착 예정 시간
            buses[i] = new Pair(d, c); // (시간, 인원) 순으로 저장
        }

        Arrays.sort(requests, (o1, o2) -> {
            if (o1.time == o2.time) return Integer.compare(o1.people, o2.people);
            return Integer.compare(o1.time, o2.time);
        });

        Arrays.sort(buses, (o1, o2) -> {
            if (o1.time == o2.time) return Integer.compare(o1.people, o2.people);
            return Integer.compare(o1.time, o2.time);
        });

        TreeMap<Integer, Integer> busMap = new TreeMap<>();
        int busIdx = 0;
        int answer = 0;

        for (Pair req : requests) {
            int maxWait = req.time;
            int needPeople = req.people;

            while (busIdx < M && buses[busIdx].time <= maxWait) {
                int cap = buses[busIdx].people;
                busMap.put(cap, busMap.getOrDefault(cap, 0) + 1);
                busIdx++;
            }

            Integer key = busMap.ceilingKey(needPeople);
            if (key != null) {
                answer++;
                int count = busMap.get(key);
                if (count == 1) busMap.remove(key);
                else busMap.put(key, count - 1);
            }
        }

        System.out.println(answer);
    }
}