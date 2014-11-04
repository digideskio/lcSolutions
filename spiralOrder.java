// pay attention to the calculation of layers
// there is probably a better way to add abnormal layers's ints, 
// the approach now is somewhat silly to separate single row, single col and single cell

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> lst = new LinkedList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return lst;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int layer = Math.min(m, n);
        layer = (layer + 1) / 2;
        
        for (int i = 0; i < layer; i++) {
            int start = i;
            int endCol = n - start - 1;
            int endRow = m - start - 1;
            if (start == endCol && endCol == endRow) {
                lst.add(matrix[start][start]);
            } else if (start == endCol){
                for (int j = start; j <= endRow; j++) {
                    lst.add(matrix[j][start]);
                }    
            } else if (start == endRow) {
                for (int j = start; j <= endCol; j++) {
                    lst.add(matrix[start][j]);
                }
            } else {
                // top
                for (int j = start; j < endCol; j++) {
                    lst.add(matrix[start][j]);
                }
                //right
                for (int j = start; j < endRow; j++) {
                    lst.add(matrix[j][endCol]);
                }
                //bottom
                for (int j = endCol; j > start; j--) {
                    lst.add(matrix[endRow][j]);
                }    
            
                //left
                for (int j = endRow; j > start; j--) {
                    lst.add(matrix[j][start]);
                }
            }
            
        }
        
        return lst;
    }
}