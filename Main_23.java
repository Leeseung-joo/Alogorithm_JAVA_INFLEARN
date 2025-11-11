package string;
import java.util.*;

public class Main_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] A = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) A[i] = sc.nextInt();
        for (int i = 0; i < N; i++) B[i] = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int result = (A[i] - B[i] + 3) % 3;
            if (result == 0) System.out.println("D");
            else if (result == 1) System.out.println("A");
            else System.out.println("B");
        }
    }
}
