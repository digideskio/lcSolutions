// be very carefull with base case !!!!

class Solution {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0) return false;

        if (word == null || word.length() == 0) return true;

        int m = board.length;
        int n = board[0].length;

        if (word.length() > m * n) return false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                    if(check(board, i, j, word, new HashSet<Integer>())) return true;

            }
        }

        return false;
    }

    public boolean check(char[][] board, int row, int col, String word, HashSet<Integer> tracking) {
      
        if (word.length() == 1 && word.charAt(0) == board[row][col]) return true;


        int m = board.length;
        int n = board[0].length;

        boolean success = false;

        if (word.charAt(0) == board[row][col] && word.length() <= m * n - tracking.size()) {
            tracking.add(row * n + col);
            if (row + 1 < m && !tracking.contains((row + 1) * n + col)) {
                success = check(board, row + 1, col, word.substring(1), tracking);
            }
            if (!success && row - 1 >= 0 && !tracking.contains((row - 1) * n + col)) {

                success = check(board, row - 1, col, word.substring(1), tracking);
            }
            if (!success && col + 1 < n && !tracking.contains(row * n + col + 1)) {

                success = check(board, row, col + 1, word.substring(1), tracking);
            }
            if (!success && col - 1 >= 0 && !tracking.contains(row * n + col - 1)) {

                success = check(board, row, col - 1, word.substring(1), tracking);
            }

            tracking.remove(Integer.valueOf(row * n + col));
        }


        return success;
    }
}