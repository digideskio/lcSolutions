public class Solution {
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
            count *= i + 1;
        }
        k--;
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            count /= (n - i);
            int index = k / count;
            k %= count;
            sb.append(nums[index]);
            for (int j = index; j < n - 1; j++) {
                nums[j] = nums[j+1];
            }
        }
        
        return sb.toString();
  
    }
}