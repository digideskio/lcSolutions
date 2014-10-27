// do always deal with base case at the very top

public class Solution {
    
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public int numDecodings(String s) {
        if (map.containsKey(s)) return map.get(s);
        if (s == null || s.length() == 0) return 0;

        int ways = 0;

        int digit1 = s.charAt(0) - '0';
        if (digit1 == 0) {
            map.put(s, ways);
            return ways;
        }

        String str = s.substring(1);
        if (str.length() == 0) {
            ways += 1;
        } else {
            ways += numDecodings(str);
        }
        
        if (s.length() > 1) {
            int digit2 = s.charAt(1) - '0';
            int num = digit1 * 10 + digit2;
            if (num <= 26) {
                String str2 = s.substring(2);
                if (str2.length() == 0) {
                    ways += 1;
                } else {
                    ways += numDecodings(str2);
                }
            }
        }
        map.put(s, ways);
        return ways;
    }
}