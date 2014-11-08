public class Solution {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int closest = Integer.MAX_VALUE;
        int val = 0;
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1;
            int end = num.length - 1;

            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (Math.abs(target - sum) < closest) {
                    closest = Math.abs(target - sum);
                    val = sum;
                }
                if (target == sum) return sum;
                else if (target > sum) {
                    start++;
                } else {
                    end--;
                }
            }

        }
        return val;
    }
}