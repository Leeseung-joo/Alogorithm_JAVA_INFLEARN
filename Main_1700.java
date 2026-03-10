import java.util.*;
import java.io.*;
public class Main_1700 {

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N =  Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] arr = new int[K];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < K; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> plug = new ArrayList<>();
    int cnt = 0;  //플러그를 뺴는 최소 횟수

    for(int i = 0; i < K; i++){
      if(plug.contains(arr[i])) continue;
      else{
        if(plug.size() < N){
          plug.add(arr[i]);
        }else{
          int removeIdx = -1;
          int farthest = -1;

         for(int j = 0; j < plug.size(); j++){
          int next  = -1;
            for(int k = i+1; k < K; k++){ //앞으로 나올 놈들에 존재하는지
              if(plug.get(j) == arr[k]){
                next = k; 
                break;
              }

            }
            if(next == -1){
              removeIdx = j; //다시 안 나오므로 제거 대상
              break;
            }
            if(next > farthest){
              farthest = next; //가장 늦게 나오는 것 기록
              removeIdx = j;
            }
         }
         plug.remove(removeIdx);
         plug.add(arr[i]);
         cnt++;
          
        }
      }
    }
    System.out.println(cnt);
  }
  
}//현재 리스트


//앞으로 아예 안 쓰일 걸 뺴거나 다 쓰이는 경우엔 젤 늦게 쓰이는 걸 뻄