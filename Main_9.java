import java.io.*;

public class Main_9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) { // 숫자인지 체크
                sb.append(ch);
            }
        }

        int result = Integer.parseInt(sb.toString()); // 문자열 → 숫자
        System.out.println(result);
    }
}
// String s = br.readLine().replaceAll("[^0-9]", ""); // 숫자만 남김
// System.out.println(Integer.parseInt(s));
br.readLine().replaceAll("[^0-9]", "");