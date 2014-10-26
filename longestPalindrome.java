// TODO: check Manacher's algorithm, O(n) complexity 

public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        
        int maxLength = 1;
        String str = s.substring(0, 1);
        
        for (int i = 1; i < s.length(); i++) {
            int low = i - 1;
            int high = i;
            int len = 0;
            while (low >= 0 && high <s.length() && s.charAt(low) == s.charAt(high)) {
                len += 2;
                low--;
                high++;
            }
            if (maxLength < len) {
                maxLength = len;
                str = s.substring(++low, --high + 1);
            }
            low = i - 1;
            high = i + 1;
            len = 1;
            while (low >= 0 && high <s.length() && s.charAt(low) == s.charAt(high)) {
                len += 2;
                low--;
                high++;
            }
            if (maxLength < len) {
                maxLength = len;
                str = s.substring(++low, --high + 1);
            }
        }
        
        return str;
    }
}