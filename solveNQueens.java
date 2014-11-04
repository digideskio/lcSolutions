public class Solution {
        public List<String[]> solveNQueens(int n) {
        int[] rows = new int[n];
        solveNQueens(n, rows, 0);
        return lst;
    }
    List<String[]> lst = new LinkedList<String[]>();
    void solveNQueens(int n, int[] rows, int cur) {

        if (n == cur) {
            lst.add(toBoard(rows));
        }

        for (int i = 0; i < n; i++) {
            if (isValid(rows, cur, i)) {
                rows[cur] = i;
                solveNQueens(n, rows, cur + 1);
            }
        }
    }

    boolean isValid(int[] rows, int cur, int pos) {
        for (int i = 0; i < cur; i++) {
            if (rows[i] == pos) return false;
            int offset = Math.abs(cur - i);
            if (rows[i] == pos + offset || rows[i] == pos - offset) return false;
        }
        return true;
    }

    String[] toBoard(int[] rows) {
        int n = rows.length;
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] == j) board[i][j] = 'Q';
                else board[i][j] = '.';
            }
        }
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = String.valueOf(board[i]);
        }
        return str;
    }
}