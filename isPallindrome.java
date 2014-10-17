// best solution (simpler) is to reverse the number and check whether two are equal

public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int len = 0;
        int num = x;
        while (num != 0) {
            num /= 10;
            len++;
        }
        
        int mid = len / 2;
        int high = len;
        int low = 1;
        while (mid > 0) {
            if (getDigit(x, high) != getDigit(x, low)) {
                return false;
            }
            high--;
            low++;
            mid--;
        } 
        return true;
    }
    
    public int getDigit(int num, int digit) {
        int scale = (int) Math.pow(10, digit - 1);
        num /= scale;
        return num % 10;
    }

    public boolean isPalindromeOp(int x) {
        int toBuild = 0;
        int toCompare = x;

        while (x > 0) {
            toBuild = toBuild * 10 + (x % 10);
            x /= 10;
        }

        if (toBuild == toCompare) {
            return true;
        }
        return false;
    }
}