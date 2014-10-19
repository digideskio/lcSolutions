//solve by dfs

public class Solution {
    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0);
    }
    
    public boolean solveSudoku(char[][] board, int step) {
        int num = board.length;
        if (step == num * num) {
            return true;
        }
        
        if (board[step/num][step%num] == '.') {
            for (int c = '1'; c <= '9'; c++) {
                board[step/num][step%num] = (char)c;
                if (isValid(board, step) && solveSudoku(board, step + 1)) {
                    return true;
                } 
                board[step/num][step%num] = '.';
            }
        } else {
            return solveSudoku(board, step + 1);
        }
        
        return false;
    } 
    
    public boolean isValid(char[][] board, int step) {
        int num = board.length;
        int row = step / num;
        int col = step % num;
        char digit = board[row][col];
        board[row][col] = '.';
        for (int i = 0; i < num; i++) {
            if (board[row][i] == digit || board[i][col] == digit) return false;
        }
        
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == digit) return false;
            }
        }
        board[row][col] = digit;
        return true;
    }
}