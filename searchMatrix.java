public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // let m be num of rows; n num of columns
        int m = matrix.length;
        int n = matrix[0].length;
        
        return searchMatrix(matrix, target, 0, m * n - 1);
    }
    
    public boolean searchMatrix(int[][] matrix, int target, int low, int high) {
        if (low > high) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int mid = (low + high) / 2;
        
        if (matrix[mid/n][mid%n] == target) {
            return true;
        } else if (target < matrix[mid/n][mid%n]) {
            return searchMatrix(matrix, target, low, mid - 1);
        } else {
            return searchMatrix(matrix, target, mid + 1, high);
        }
        
    }    
}