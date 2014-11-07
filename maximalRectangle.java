// O(N^2 solution), should check largest rect problem

public class Solution {
      public int maximalRectangle(char[][] matrix) {
        if (matrix == null) return 0;


        for (int i = 1; i < matrix.length; i++){
            for  (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '0') {
                    if (matrix[i-1][j] != '0') {
                        matrix[i][j] = (char)(matrix[i-1][j] + 1);
                    }
                }   

            }
        }
        int gMax = 0;

        for (int i = 0; i < matrix.length; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            int max = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (stack.empty() || matrix[i][j] > matrix[i][stack.peek()]) {
                    stack.push(j);
                } else {
                    int index = stack.pop();
                    max = Math.max(max, (stack.isEmpty() ? j : j - 1 - stack.peek()) * (matrix[i][index] - '0'));
                    j--;
                }
            }
            while (!stack.isEmpty()) {
                int index = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? matrix[0].length : matrix[0].length - 1 - stack.peek()) * (matrix[i][index] - '0'));
            }
            gMax = Math.max(gMax, max);
        }
        
        return gMax;

    }
}