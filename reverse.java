public class Solution {
    public int reverse(int x) {
        
        int len = 0;
        int digit = x;
        while (digit != 0) {
            len++;
            digit /= 10;
        }
        digit = 0;
        
        while (x != 0) {
            digit += (x % 10) * Math.pow(10, len - 1);
            x /= 10;
            len--;
        }
        return digit;
    }
}