public class Solution {
      public int atoi(String str) {
        if (str == null || str.length() == 0) return 0;

        str = str.trim();

        boolean flag = true;
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        } else if (str.charAt(0) == '-') {
            str = str.substring(1);
            flag = false;
        }


        long value = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {
                value = value * 10 + (str.charAt(i) - '0');
            } else {
                break;
            }
            if (value >= Integer.MAX_VALUE && flag) {
                return Integer.MAX_VALUE;
            } else if (-value <= Integer.MIN_VALUE && !flag) {
                return  Integer.MIN_VALUE;
            }
        }

        return (int) (flag ? value : -value);
    }
}