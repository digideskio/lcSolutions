public class Solution {
    public int minPathSum(int[][] grid) {
        // grid[m][n]
        int m = grid.length;
        int n = grid[0].length;
    
        
        int[][] mins = new int[m + 1][n + 1];
        
        return minPathSum(grid, m - 1, n - 1, mins);
    }
    
    public int minPathSum(int[][] grid, int m, int n, int[][] mins) {
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        
        if (mins[m][n] != 0) {
            return mins[m][n];
        }
        
        int minLeft = Integer.MAX_VALUE;
        int minDown = Integer.MAX_VALUE;
        if (m > 0) {
            minLeft = minPathSum(grid, m - 1, n, mins);
        }
        if (n > 0) {
            minDown = minPathSum(grid, m, n - 1, mins);
        }
        
        mins[m][n] = Math.min(minLeft, minDown) + grid[m][n];
        
        return mins[m][n];
    }
}