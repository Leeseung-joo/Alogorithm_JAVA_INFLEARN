import java.util.*;
import java.io.*;

public class Main_2580 {
  static int[][] map = new int[9][9];

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    for(int i = 0; i < 9; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 9; j++){
      
        map[i][j] =Integer.parseInt(st.nextToken());
    }
    
  }
  sudoku(0,0);
  }
  static void sudoku(int row, int col){
    if(col == 9){
      sudoku(row+1, 0); //해당 행이 다 채워졌을 경우 다음 행의 첫번째 열부터 시작
      return;
    }

    if(row == 9){ //행과 열이 모두 채워졌을 경우 출력 후 종료
      StringBuilder  sb = new StringBuilder();
      for(int i = 0; i < 9; i++){
        for(int j = 0; j < 9; j++){
          sb.append(map[i][j]).append(' ');
        }
        sb.append('\n');
      }
      System.out.println(sb);
      System.exit(0);

    }

    if(map[row][col] == 0){  //해당 위치의 값이 0이라면 1부터 9까지 가능한 수 탐색
      for(int i = 1; i <= 9; i++){
        if(possibilty(row, col, i)){
          map[row][col] = i;
          sudoku(row,col+1);
        }
      }
      map[row][col] = 0;
      return;
 
    }

    sudoku(row,col+1);    //현재 선택이 전체 문제를 해결할 수 있는지 검증하러 가는 단계




  }

  static boolean possibilty(int row, int col, int value){
    for(int i = 0; i < 9; i++){  //같은 행 중복 검사
      if(map[row][i] == value){
        return false;
      }
    }

    for(int i = 0; i < 9; i++){ //같은 열 중복 검사
      if(map[i][col] == value){
        return false;
      }
    }

    int set_row = (row/3) *3;
    int set_col = (col/3) * 3;

    for(int i = set_row; i < set_row+3; i++){
      for(int j = set_col; j < set_col+3; j++){
        if(map[i][j] == value){
          return false;
        }
      }
    }
    return true;   //중복되는 것이 없을 경우 true 반환
  }
 
  
}
