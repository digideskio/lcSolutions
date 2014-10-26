public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[m-1][n-1] == 1) return 0;
        
        int[][] map = new int[m][n];
        return uniquePathsWithObstacles(obstacleGrid, m - 1, n - 1, map);
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid, int m, int n, int[][] map) {
        if (m == 0 && n == 0) return 1;
        
        if (map[m][n] != 0) return map[m][n];
        
        int ways = 0;
        if (m > 0 && checkValid(obstacleGrid, m - 1, n)) 
            ways += uniquePathsWithObstacles(obstacleGrid, m - 1, n, map);
        if (n > 0 && checkValid(obstacleGrid, m, n - 1)) 
            ways += uniquePathsWithObstacles(obstacleGrid, m, n - 1, map);
        
        map[m][n] = ways;
        
        return ways;
    }
    
    public boolean checkValid(int[][] obstacleGrid, int m, int n) {
        return obstacleGrid[m][n] == 0 ? true : false;
    }
}