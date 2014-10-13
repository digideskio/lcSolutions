public class Solution {
  public static int uniquePaths(int m, int n) {

        int[][] maps = new int[m][n];

        return uniquePaths(m-1, n-1, maps);

    }

    public static int uniquePaths(int m, int n, int[][] maps) {
        if (m == 0 && n == 0) {
            return 1;
        }

        if (maps[m][n] != 0) {
            return maps[m][n];
        }

        int ways = 0;
        if (m > 0) {
           ways += uniquePaths(m - 1, n, maps);
        }
        if (n > 0) {
            ways += uniquePaths(m, n - 1, maps);
        }
        
        maps[m][n] = ways;

        return ways;
    }
}