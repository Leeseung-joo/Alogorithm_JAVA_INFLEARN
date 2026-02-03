import java.io.*;

public class Main_1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        String[] parts = line.split("-");

        int result = 0;

        // 첫 덩어리는 더함
        for (String s : parts[0].split("\\+")) {
            result += Integer.parseInt(s);
        }

        // 그 이후 덩어리는 전부 빼기
        for (int i = 1; i < parts.length; i++) {
            for (String s : parts[i].split("\\+")) {
                result -= Integer.parseInt(s);
            }
        }

        System.out.println(result);
    }
}
