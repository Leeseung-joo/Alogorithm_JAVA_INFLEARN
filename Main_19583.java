import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 개강총회를 시작한 시간, 끝낸 시간, 스트리밍을 끝낸 시간을 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String start = st.nextToken();
        String end = st.nextToken();
        String quit = st.nextToken();

        // 시간을 LocalTime 객체로 변환
        LocalTime s = timeMaker(start);
        LocalTime e = timeMaker(end);
        LocalTime q = timeMaker(quit);

        // 입장한 학회원을 저장할 Set 생성
        Set<String> in = new HashSet<>();

        // 이미 출석으로 확인된 학회원을 저장할 Set 생성
        Set<String> counted = new HashSet<>();

        String str;
        int count = 0;

        // 채팅 기록을 읽어오면서 출석 여부 확인
        while ((str = br.readLine()) != null) {
            st = new StringTokenizer(str, " ");
            LocalTime time = timeMaker(st.nextToken());
            String member = st.nextToken();

            // 개강총회 시작 전에 대화한 학회원은 입장으로 간주
            if (time.isBefore(s) || time.equals(s)) {
                in.add(member);
            }

            // 개강총회 끝난 시간 이후, 스트리밍 끝난 시간 이전에 대화한 학회원 중 이미 입장한 학회원을 출석으로 간주
            if ((time.isAfter(e) || time.equals(e)) && (time.isBefore(q) || time.equals(q))) {
                if (in.contains(member)) {
                    counted.add(member);
                }
            }
        }

        // 출석이 확인된 학회원의 인원 수 출력
        System.out.println(counted.size());
    }

    // 문자열을 LocalTime 객체로 변환하는 함수
    static LocalTime timeMaker(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        LocalTime time = LocalTime.of(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        return time;
    }
}