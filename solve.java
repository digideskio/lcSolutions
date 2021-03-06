// two strategies: 
// 1. mark all boundary '0's
// 2. found all contained '0' blocks and clean them, the second one got TLEed for largest data set, still don't know why :(
// bfs graph needs visited array to mark all visited nodes!
public class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        // from the border we find the 'O's, which will not flip
        for (int i = 0; i < n; ++i) {
            int j = 0;
            if (board[i][j] == 'O') {
                queue.push(i * m + j);
            }
            j = m - 1;
            if (board[i][j] == 'O') {
                queue.push(i * m + j);
            }
        }
        for (int j = 0; j < m; ++j) {
            int i = 0;
            if (board[i][j] == 'O') {
                queue.push(i * m + j);
            }
            i = n - 1;
            if (board[i][j] == 'O') {
                queue.push(i * m + j);
            }
        }

        bfs(board, visited, queue);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void bfs(char[][] board, boolean[][] visited, LinkedList<Integer> queue) {
        int n = board.length;
        int m = board[0].length;
        int t, x, y;
        while (!queue.isEmpty()) {
            t = queue.poll();
            x = t / m;
            y = t % m;

            if (x < 0 || x > n || y < 0 || y > m) continue;
            if (visited[x][y]) continue;

            if (board[x][y] == 'O') {
                visited[x][y] = true;
                queue.push(x * m + y + 1);
                queue.push((x + 1) * m + y);
                queue.push(x * m + y - 1);
                queue.push((x - 1) * m + y);

            }

      
        }
    }
}