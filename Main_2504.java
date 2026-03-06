import java.util.*;
import java.io.*;

public class Main_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Stack<Character> stack = new Stack<>();
        int result = 0;
        int temp = 1;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '(') {
                stack.push(ch);
                temp *= 2;
            } else if (ch == '[') {
                stack.push(ch);
                temp *= 3;
            } else if (ch == ')') {
                // 올바르지 않은 경우
                if (stack.isEmpty() || stack.peek() != '(') {
                    System.out.println(0);
                    return;
                }

                // 바로 직전이 '('이면 값 더하기
                if (line.charAt(i - 1) == '(') {
                    result += temp;
                }

                stack.pop();
                temp /= 2;
            } else if (ch == ']') {
                // 올바르지 않은 경우
                if (stack.isEmpty() || stack.peek() != '[') {
                    System.out.println(0);
                    return;
                }

                // 바로 직전이 '['이면 값 더하기
                if (line.charAt(i - 1) == '[') {
                    result += temp;
                }

                stack.pop();
                temp /= 3;
            }
        }

        // 스택이 남아있으면 올바르지 않은 괄호열
        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}