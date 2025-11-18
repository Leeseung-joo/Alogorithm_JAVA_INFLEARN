import java.util.*;
import java.io.*;

public class Main_45 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String s1 = br.readLine();

        // 길이 다르면 바로 NO
        if (s.length() != s1.length()) {
            System.out.println("NO");
            return;
        }

        char[] a = s.toCharArray();
        char[] b = s1.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        if (Arrays.equals(a, b)) System.out.println("YES");
        else System.out.println("NO");
    }
}
