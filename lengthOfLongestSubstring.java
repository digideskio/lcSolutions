// first edition got TLEed
// this version runs at O(n)
// idea is to have two pointers, one at end one at the start pos, change start pos to the lastest same char + 1

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int start = 0;
        int end = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        
        for (int i = 0; i < s.length(); i++) {
            Integer p = map.get(s.charAt(i));
            if (p >= start) { 
                start = p + 1;
            } 
            max = Math.max(i - start + 1, max);
            map.put(s.charAt(i), i);
        }
        
        return max;
    }
}