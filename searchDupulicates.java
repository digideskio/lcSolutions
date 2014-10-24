// roated array search with duplicates:
// just be carefull with case that dupicates are are both ends
// worst case could be as high as O(n)? (NEED TO BE CONFIRMED)
public class Solution {
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) return false;
        return search(A, target, 0, A.length - 1);
    }
    
    public boolean search(int[] A, int target, int low, int high) {
        if (low > high) return false;
        
        int mid = (low + high) / 2;
        if (A[mid] == target) return true;
        else if (A[low] < A[mid]) {
            if (target >= A[low] && target < A[mid]) return search(A, target, low, mid - 1);
            else return search(A, target, mid + 1, high);
        } else if (A[low] > A[mid]) {
            if (target > A[mid] && target <= A[high]) return search(A, target, mid + 1, high);
            else return search(A, target, low, mid - 1);
        } else {
            if (A[low] == A[high]) {
                boolean left = search(A, target, low, mid - 1);
                boolean right = search(A, target, mid + 1, high);
                return left || right;
            } else {
                return search(A, target, mid + 1, high);
            }
        }
         
        
    }
}