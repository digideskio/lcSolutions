// naive approach got TLEed (O(N^2)? NEED TO BE VERIFIED)
// used hashmap to store visited cases

public class Solution {
    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    public boolean isInterleave(String s1, String s2, String s3) {
        if (map.containsKey(s1+ "+" + s2)) return map.get(s1 + "+" + s2);
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.length() == 0 || s2.length() == 0) {
            if (s1.length() != 0) return s1.equals(s3);
            else if (s2.length() != 0) return s2.equals(s3);
            else return s3.equals("");
        }
        
        boolean sub1 = false;
        boolean sub2 = false;
        if (s1.charAt(0) == s3.charAt(0)) {
            sub1 = isInterleave(s1.substring(1), s2, s3.substring(1));
        }
        if (s2.charAt(0) == s3.charAt(0)) {
            sub2 = isInterleave(s1, s2.substring(1), s3.substring(1));
        }
        map.put(s1 + "+" + s2, sub1 || sub2);
        
        return sub1 || sub2;
        
    }
}