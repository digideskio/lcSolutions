public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        if (num.length == 1) return num[0];
        return findMin(num, 0, num.length - 1);
    }
    
    public int findMin(int[] num, int low, int high) {
        if (low == high) {
            return num[low];
        }
        
        int mid = (low + high) / 2;
        if (num[mid] < num[high]) { // pivot is at left
            return findMin(num, low, mid);
        } else return findMin(num, mid + 1, high); //pivot is at right
    }
}