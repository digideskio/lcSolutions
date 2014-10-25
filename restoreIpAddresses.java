// pay attention to the start and end case.
// also, mind 0 as special case!

// a mistake when coding: pay attention to the use of ipStr in for loop, don't add accumulative characters in that variable!!


public class Solution {
    
    List<String> lst = new LinkedList<String>();
    public List<String> restoreIpAddresses(String s) {
        return restoreIpAddresses(s, 4, "");
    }

    public List<String> restoreIpAddresses(String s, int index, String ipStr) {
        int len = s.length();
        if (len > index * 3 || len < index || index < 1) {
            return lst;
        }
        
        int maxDigits = Math.min(3, s.length());
        int highestDigit = s.charAt(0) - '0';
        if (highestDigit > 2) maxDigits = Math.min(2, s.length());
        else if (highestDigit == 0) maxDigits = 1;
        for (int i = 1; i <= maxDigits; i++) {
            String str = s.substring(0, i);
            int num = Integer.parseInt(str);
            if (num > 255) continue;
            if (i == s.length()) {
                if (index == 1) {
                    String newStr = ipStr + "." + str;
                    lst.add(newStr);
                }
            } else {
                String newStr;
                if (index == 4) restoreIpAddresses(s.substring(i), index - 1, str);
                else restoreIpAddresses(s.substring(i), index - 1, ipStr + "." + str);
                
            }

        }
        return lst;
    }
}