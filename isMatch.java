// my recursive solution got TLEed
// found one iterative solution

public class Solution {
    Set<String> set = new HashSet<String>();
    public boolean isMatch(String s, String p) {
        if (set.contains(s + " " + p)) return false;
        if (s.equals(p)) return true;
        if (p.length() == 0) return false;
        
        while (s.length() > 0 && p.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')) {
            s = s.substring(1);
            p = p.substring(1);
        }
        if (p.length() > 0 && p.charAt(0) == '*') {
            if (s.length() == 0) return true;
            String ss = s.substring(1);
            String pp = p.substring(1);
            if (isMatch(ss, pp)) return true;
            if (isMatch(ss, p)) return true;
            if (isMatch(s, pp)) return true;
           
        }
        set.add(s + " " + p);
        return false;
        
    }

    public boolean isMatchAlt(String s, String p) {
        int ss = 0, pp = 0, match = 0, starIdx = -1;
        while (ss < s.length()){
            // advancing both pointers
            if (pp < p.length()  && (p.charAt(pp) == '?' || s.charAt(ss) == p.charAt(pp))){
                ss++;
                pp++;
            }
            // * found, only advancing p pointer
            else if (pp < p.length() && p.charAt(pp) == '*'){
                starIdx = pp;
                match = ss;
                pp++;
            }
            // last p pointer was *, advancing string pointer
            else if (starIdx != -1){
                pp = starIdx + 1;
                match++;
                ss = match;
            }
            //current p pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in p
        while (pp < p.length() && p.charAt(pp) == '*')
            pp++;

        return pp == p.length();

    }
}