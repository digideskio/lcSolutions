public class Solution {
    public int findMin(int[] num) {
        if (num.length == 1) return num[0];
        return findMin(num, 0, num.length - 1);
    }
    
    int findMin(int[] num, int low, int high) {
        if (low == high) return num[low];
        
        int mid = (low + high) / 2;
        if (num[mid] < num[high]) {
            return findMin(num, low, mid);
        } else if (num[mid] > num[high]) {
            return findMin(num, mid + 1, high);
        } else {
            if (num[mid] != num[low]) {
                return findMin(num, low, mid);
            } else {
                return Math.min(findMin(num, low, mid), findMin(num, mid + 1, high));
            }
        }
        
        
    }
}