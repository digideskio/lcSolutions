// two passes, 2 * n*logn
// modified binary search to find left and right bounds;

public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] range = {-1, -1};
        if (A == null) {
            return range;
        }
        
        range[0] = leftBound(A, target, 0, A.length - 1);
        range[1] = rightBound(A, target, 0, A.length - 1);
        return range;
    }
    
    public int leftBound(int[] A, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        
        int mid = (low + high) / 2;
        if (A[mid] == target) {
            if (mid == 0) {
                return mid;
            } else if (A[mid-1] == target) {
                return leftBound(A, target, low, mid - 1);
            } else {
                return mid;
            }
        } else if (target < A[mid]) {
            return leftBound(A, target, low, mid - 1); 
        } else {
            return leftBound(A, target, mid + 1, high);
        }
    }
    
    public int rightBound(int[] A, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        
        int mid = (low + high) / 2;
        if (A[mid] == target) {
            if (mid == A.length - 1) {
                return mid;
            } else if (A[mid+1] == target) {
                return rightBound(A, target, mid + 1, high);
            } else {
                return mid;
            }
        } else if (target < A[mid]) {
            return rightBound(A, target, low, mid - 1); 
        } else {
            return rightBound(A, target, mid + 1, high);
        }
    }
}