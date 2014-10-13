//Scan from the least significant bit; Record the last carry and check if the digit is 9

public class Solution {
    public int[] plusOne(int[] digits) {
        boolean carry = true;
        int index = digits.length - 1;
        
        while (carry && index >= 0) {
            if (digits[index] == 9) {
                digits[index] = 0;
            } else {
                digits[index]++;
                carry = false;
            }
            index--;
        }
        
        if (digits[0] == 0) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                newDigits[i + 1] = digits[i];
            }
            digits = newDigits;
        }
        
        return digits;
    }
}