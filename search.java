


public class Solution {
    public int search(int[] A, int target) {
        return search(A, target, 0, A.length - 1);
    }
    
    public int search(int[] A, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;
        if (A[mid] == target) {
            return mid;
        } else {
            if (A[mid] <= A[high]) { //right is sorted
                if (target > A[mid] && target <= A[high]) {
                    return search(A, target, mid + 1, high);
                } else {
                    return search(A, target, low, mid - 1);
                }
            } else {  //left is sorted
                if (target < A[mid] && target >= A[low]) {
                    return search(A, target, low, mid - 1);
                } else {
                    return search(A, target, mid + 1, high);
                }
            }
        }
    }
}