public class Solution {
   public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        else if (p.length() == 0) return false;

        if (p.length() == 1) {
            if (s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                return true;
            } else {
                return false;
            }
        }

        if (p.charAt(1) == '*') {
            String ss = s;
            char match = p.charAt(0);
            if(isMatch(ss, p.substring(2))) return true;
            while (ss.length() > 0 && ((ss.charAt(0) == match) || match == '.')) {
                ss = ss.substring(1);
                if(isMatch(ss, p.substring(2))) return true;
            }
            return false;
        } else {
            if (p.length() > 0 && s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
               
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
            
        }
    }
}