public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (!isValidRow(board, i)) return false;
            if (!isValidCol(board, i)) return false;
        }
        for (int i = 0; i < n; i += 3) {
            for (int j = 0; j < n; j += 3) {
                if (!isValidGrid(board, i, j)) return false;
            }
        }
        return true;
    }
    
    boolean isValidGrid(char[][] board, int i, int j) {
        int n = board.length;
        boolean[] occupied = new boolean[n];
        for (int row = i; row < i + 3; row++) {
            for (int col = j; col < j + 3; col++) {
                if (board[row][col] != '.') {
                    if (occupied[board[row][col]-'1']) {
                        return false;
                    } else {
                        occupied[board[row][col]-'1'] = true;
                    }
                }
            }
        }
        return true;
    }
    
    boolean isValidRow(char[][] board, int row) {
        int n = board.length;
        boolean[] occupied = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (board[row][i] != '.') {
                if (occupied[board[row][i]-'1']) {
                    return false;
                } else {
                    occupied[board[row][i]-'1'] = true;
                }
            }
            
        }
        
        return true;
    }
    
    boolean isValidCol(char[][] board, int col) {
       int n = board.length;
        boolean[] occupied = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (board[i][col] != '.') {
                if (occupied[board[i][col]-'1']) {
                    return false;
                } else {
                    occupied[board[i][col]-'1'] = true;
                }
            }
            
        }
        
        return true;
    }
}