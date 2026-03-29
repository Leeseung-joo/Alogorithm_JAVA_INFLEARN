import java.util.*;
import java.io.*;
public class Main_1919 {
  
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    int[] arr1 = new int[26];
    int[] arr2 = new int[26];
    String a = br.readLine();
    String b = br.readLine();

    for (int i = 0; i < a.length(); i++) {
      char ch = a.charAt(i);
      arr1[ch - 'a']++;
  }

  // 두 번째 문자열 알파벳 개수 세기
  for (int i = 0; i < b.length(); i++) {
    char ch = b.charAt(i);
    arr2[ch - 'a']++;
}

int answer = 0;

// 각 알파벳 개수 차이 더하기
for (int i = 0; i < 26; i++) {
    answer += Math.abs(arr1[i] - arr2[i]);
}

System.out.println(answer);
  }
}
