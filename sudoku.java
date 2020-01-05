import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

class sudoku{

 /*   public int[][] board =  { 
        {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
        {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
        {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
        {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
        {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
        {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
        {0, 0, 5, 2, 0, 6, 3, 0, 0} 
}; 
*/
    public int[][] board;
    sudoku(int N){
        board = new int[N][N];
    }
    public int[] findCell(){
        int pos[] = new int[2];
        pos[0] = -1;
        pos[0] = -1;
        for(int i=0;i<9;i++)
        for(int j=0;j<9;j++)
        if(board[i][j] == 0){
            pos[0] = i;
            pos[1] = j;
            return pos;
        }
        return null;
    }
    //Check if give number n is safe in its 3x3-block,row and column
    public boolean check(int n, int x,int y){
        
        for(int i=0;i<9;i++){
            //Check for row
            if(board[x][i] == n)
            return false;
            //check for column
            if(board[i][y]== n)
            return false;
        }
        //Check for 3x3 grid 
        if(x>=0 & x<=2 & y>=0 & y<=2){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=3 & x<=5 & y>=0 & y<=2){
            for(int i=3;i<=5;i++){
                for(int j=0;j<3;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=6 & x<=8 & y>=0 & y<=2){
            for(int i=6;i<=8;i++){
                for(int j=0;j<3;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=0 & x<=2 & y>=3 & y<=5){
            for(int i=0;i<3;i++){
                for(int j=3;j<=5;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=0 & x<=2 & y>=6 & y<=8){
            for(int i=0;i<3;i++){
                for(int j=6;j<=8;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=3 & x<=5 & y>=3 & y<=5){
            for(int i=3;i<=5;i++){
                for(int j=3;j<=5;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=3 & x<=5 & y>=6 & y<=8){
            for(int i=3;i<=5;i++){
                for(int j=6;j<=8;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=6 & x<=8 & y>=3 & y<=5){
            for(int i=6;i<=8;i++){
                for(int j=3;j<=5;j++)
                if(board[i][j] == n)
                return false;
            }
        }
        if(x>=6 & x<=8 & y>=6 & y<=8){
            for(int i=6;i<=8;i++){
                for(int j=6;j<=8;j++)
                if(board[i][j] == n)
                return false;
            }
        }

        return true;

    }
    public boolean solve(){
        int pos[] = findCell();
        if(pos == null){
            return true;
        }
        for(int i=1;i<=9;i++){
            if(check(i, pos[0], pos[1])){
                board[pos[0]][pos[1]] = i;
                if(solve())
                return true;
                else{
                    board[pos[0]][pos[1]] = 0;
                }
            }
        }
        return false;
    }
    private List<Integer> list = new ArrayList<Integer>();
    public boolean fillDiagnols(int n){
        if(n>6){
        return true;
        }
        for(int i=n;i<n+3;i++){
            for(int j=n;j<n+3;j++){
                int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
                if(list.contains(randomNum)){
                    j--;
                }else{
                list.add(randomNum);
                board[i][j] = randomNum;
                }  
            }
        }
        list.clear();
        fillDiagnols(n+3);
        return true;
    }
   
    public void removeNelements(int n){
       
        while(n != 0){
            int randomX = ThreadLocalRandom.current().nextInt(0, 9);
            int randomY = ThreadLocalRandom.current().nextInt(0, 9);
            if(board[randomX][randomY] != 0){
                board[randomX][randomY] = 0;
                n--;
            }
        }
    }
    public void generateSudoku(){
       fillDiagnols(0);
       solve();
       removeNelements(20);
       for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            System.out.print(board[i][j]+" ");
        }
        System.out.println();
    }
}
    public static void main(String args[]){
      
      sudoku obj = new sudoku(9);
      obj.generateSudoku();
      System.out.println();
      obj.solve();
      for(int i=0;i<9;i++){
      for(int j=0;j<9;j++){
          System.out.print(obj.board[i][j]+" ");
      }
      System.out.println();
    }

    }
}