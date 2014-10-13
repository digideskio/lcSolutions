public class Solution {
    public void rotate(int[][] matrix) {
        int rowToVisit = matrix.length / 2;
        int size = matrix.length;
        for (int i = 0; i < rowToVisit; i++) {
            //grids: size - 2 * row;
            //begining: i
            //last: size - i - 1
            for (int j = i; j < size - i - 1; j++) {
                int offset = j - i;
                int temp = matrix[i][j];
                
                 //left to top
                matrix[i][j] = matrix[size - i - 1 - offset][i];
                
                  //bottom to left
                matrix[size - i - 1 - offset][i] = matrix[size - i - 1][size - i - 1 - offset];
                
                 //right to bottom
                matrix[size - i - 1][size - i - 1 - offset] = matrix[i + offset][size - i - 1];
                
                //top to right
                matrix[i + offset][size - i - 1] = temp;
               
            }
        }
    }
    
   
}