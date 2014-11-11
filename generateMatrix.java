public class Solution {
public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int layer = (n + 1) / 2;
        int count = 1;
        for (int i = 0; i < layer; i++) {
            int start = i;
            int end = n - i - 1;
            if (start == end) {
                matrix[start][start] = count++;
                break;
            }
            // top
            for (int j = start; j < end; j++) {
                matrix[start][j] = count++;
            }
            //right
            for (int j = start; j < end; j++) {
                matrix[j][end] = count++;
            }
            //bottom
            for (int j = end; j > start; j--) {
                matrix[end][j] = count++;
            }
            //bottom
            for (int j = end; j > start; j--) {
                matrix[j][start] = count++;
            }
        }

        return matrix;
    }
}