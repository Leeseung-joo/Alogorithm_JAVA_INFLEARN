import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution_1 {

    private static int matchIgnoreDigits(String s, int start, String target) {
        int i = start;
        int t = 0;
        int n = s.length();

        while (i < n && t < target.length()) {
            char c = s.charAt(i);

            // 단어 "중간" 숫자는 무시(소비만 함)
            if (c >= '0' && c <= '9') {
                i++;
                continue;
            }

            if (c != target.charAt(t)) {
                return -1;
            }

            i++;
            t++;
        }

        return (t == target.length()) ? i : -1;
    }

    public static String solution(String s) {
        StringBuilder out = new StringBuilder();
        int i = 0, n = s.length();

        while (i < n) {
            char cur = s.charAt(i);

            //  핵심 수정: "문자"에서만 매칭 시도
            if (cur >= 'a' && cur <= 'z') {
                int endDog = matchIgnoreDigits(s, i, "dog");
                if (endDog != -1) {
                    out.append("cat");
                    i = endDog;
                    continue;
                }

                int endCat = matchIgnoreDigits(s, i, "cat");
                if (endCat != -1) {
                    out.append("dog");
                    i = endCat;
                    continue;
                }
            }

            // 문자 아니거나 매칭 실패면 그대로 출력
            out.append(cur);
            i++;
        }

        return out.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine(); // 한 줄 입력
        System.out.println(solution(s));
    }
}
