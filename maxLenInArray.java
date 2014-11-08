// max len of non descending elements in an array

class Solution {
    int maxLen(int[] array) {
        int n = array.length;
        int maxLen = 0;
        int[] last = new int[n];
        for (int i = 0; i < n; i++) {
            int index = insertIndex(last, array[i], 0, maxLen - 1);
            if (index == maxLen) {
                maxLen++;
                last[index] = array[i];
            } else if (array[i] < last[index]) {
                last[index] = array[i];
            }
        }

        return maxLen;
    }

    int insertIndex(int[] array, int target, int low, int high) {
        if (low > high) return low;
        int mid = (low + high) / 2;
        if (array[mid] == target) return mid;
        else if (array[mid] > target) return insertIndex(array, target, low, mid - 1);
        else return insertIndex(array, target, mid + 1, high);
    }
}