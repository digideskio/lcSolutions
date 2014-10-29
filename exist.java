// got TLEed
class Solution {
     Map<String, Boolean> set = new HashMap<String, Boolean>();
    Set<Integer> tracking = new HashSet<Integer>();

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0) return false;

        if (word == null || word.length() == 0) return true;

        int m = board.length;
        int n = board[0].length;
        
        if (word.length() > m * n) return false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

               if(check(board, i, j, word)) return true;

            }
        }

        return false;
    }

    public boolean check(char[][] board, int row, int col, String word) {
        if (word.length() == 0) return true;
        if(set.containsKey(row + " " + col + " " + word.length())) return set.get(row + " " + col + " " + word.length());

        int m = board.length;
        int n = board[0].length;

        boolean success = false;
        
        if (word.charAt(0) == board[row][col] && word.length() <= m * n - tracking.size()) {
            tracking.add(row * n + col);
            if (row + 1 < m && !tracking.contains((row + 1) * n + col)) {
                success = check(board, row + 1, col, word.substring(1));
            }
            if (!success && row - 1 >= 0 && !tracking.contains((row - 1) * n + col)) {

                success = check(board, row - 1, col, word.substring(1));
            }
            if (!success && col + 1 < n && !tracking.contains(row * n + col + 1)) {

                success = check(board, row, col + 1, word.substring(1));
            }
            if (!success && col - 1 >= 0 && !tracking.contains(row * n + col - 1)) {

                success = check(board, row, col - 1, word.substring(1));
            }

            tracking.remove(Integer.valueOf(row * n + col));
        }

        set.put(row + " " + col + " " + word.length(), success);
        return success;
    }
}