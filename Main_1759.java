import java.util.*;

public class Main_1759 {

    static int N;      // L (암호 길이)
    static int C;      // C (문자 개수)
    static char[] arr; // 입력 문자들

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 4
        C = sc.nextInt(); // 6

        arr = new char[C];
        for (int i = 0; i < C; i++) {
            arr[i] = sc.next().charAt(0); // 'a', 't', ...
        }

        Arrays.sort(arr); // 오름차순 정렬

        dfs(0, 0, new char[N], 0, 0); // idx, length, result, vowelCnt, consonantCnt
    }

    static void dfs(int idx, int length, char[] result, int vowelCnt, int consonantCnt) {

        // N개 다 뽑았으면 조건 검사 후 출력
        if (length == N) {
            if (vowelCnt >= 1 && consonantCnt >= 2) {
                System.out.println(new String(result));
            }
            return;
        }

        // 더 이상 뽑을 문자가 없으면 종료
        if (idx == C) return;

        // 1) 현재 문자 선택
        char ch = arr[idx];
        result[length] = ch;

        if (isVowel(ch)) {
            dfs(idx + 1, length + 1, result, vowelCnt + 1, consonantCnt);
        } else {
            dfs(idx + 1, length + 1, result, vowelCnt, consonantCnt + 1);
        }

        // 2) 현재 문자 선택 안 함
        dfs(idx + 1, length, result, vowelCnt, consonantCnt);
    }

    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
