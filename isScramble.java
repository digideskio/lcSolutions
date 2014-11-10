public class Solution {
    HashMap<String,Boolean> map = new HashMap<String,Boolean>();
    public boolean isScramble(String s1, String s2) {
        if (map.containsKey(s1 + "+" + s2)) return map.get(s1 + "+" + s2);
        int len = s1.length();

        boolean success = false;

        if (len <= 2) {
            if (s1.equals(s2)) success = true;
            s2 = new StringBuffer(s2).reverse().toString();
            if (s1.equals(s2)) success = true;
            map.put(s1 + "+" + s2, success);
            return success;
        }

        for (int i = 1; i < len; i++) {
            String first = s1.substring(0, i);
            String second = s2.substring(0, i);
            if (isScramble(first, second)) {
                if (isScramble(s1.substring(i), s2.substring(i))) {
                    success = true;
                    break;
                }
            }

            second = s2.substring(s2.length() - i, s2.length());
            if (isScramble(first, second)) {
                if (isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                    success = true;
                    break;
                }
            }
        }

        map.put(s1 + "+" + s2, success);
        return success;
    }

}