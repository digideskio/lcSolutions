// Two pointers, O(N) solution

public class Solution {
    public int removeDuplicates(int[] A) {
        if (A.length < 3) {
            return A.length;
        }
        
        int index = 2;
        for (int i = 2; i < A.length; i++) {
            if (A[index - 1] != A[index - 2] || A[index - 1] != A[i]) {
                A[index] = A[i];
                index++;
            }
        }
        
        return index;
    }
}