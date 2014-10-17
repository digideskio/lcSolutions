// super neat solution for trap question, 
// must justify the movement of two pointers in the inteview

public class Solution {
    public int trap(int[] A) {
        int len = A.length;
        int water = 0;
        if (len < 2) {
            return water;
        }
        
        
        int start = 1, end = len - 2;
        int leftHeight = A[0], rightHeight = A[len-1];
        
        while (start <= end) {
            if (leftHeight < rightHeight) {
                water += Math.max(leftHeight - A[start], 0);
                leftHeight = Math.max(leftHeight, A[start]);
                start++;
            } else {
                water += Math.max(rightHeight - A[end], 0);
                rightHeight = Math.max(rightHeight, A[end]);
                end--;
            }
        }
        
        return water;
    }
    
}