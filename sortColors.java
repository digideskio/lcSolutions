// O(n) solution: put all zeros to the left side and twos the right side

public class Solution {
    public void sortColors(int[] A) {
        int zeros = 0;
        int twos = A.length - 1;
        
        int index = 0;
        
        while (index <= twos) {
            if (A[index] == 0) {
                int temp = A[index];
                A[index] = A[zeros];
                A[zeros] = temp;
                zeros++;
            } else if (A[index] == 1) {
                index++;
            } else if (A[index] == 2) {
                int temp = A[index];
                A[index] = A[twos];
                A[twos] = temp;
                twos--;
            }
            index = Math.max(zeros, index);
        }
    }
}