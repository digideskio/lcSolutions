public class Solution {
    public int firstMissingPositive(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= 0) {
                A[i] = A.length + 1;
            }
        }
        
        for (int i = 0; i < A.length; i++) {
            int num = Math.abs(A[i]);
            if (num > 0 && num <= A.length) {
                A[num-1] = -Math.abs(A[num-1]);
            }
        }
        
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) return i + 1;
        }
        
        return A.length + 1;
    }
}