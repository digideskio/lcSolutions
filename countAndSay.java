public class Solution {
     public String countAndSay(int n) {
        String str = "1";
        n--;
        for (int i = 0; i < n; i++) {
            int index = 0;
            StringBuffer sb = new StringBuffer();
            int cnt = 0;
            char last = '0';
            while (index < str.length()) {

                if (last != '0' && last != str.charAt(index)) {
                    sb.append(cnt);
                    sb.append(last);
                    cnt = 0;
                }
                last = str.charAt(index);
                index++;
                cnt++;
            }
            // last one
            if (cnt != 0) {
                sb.append(cnt);
                sb.append(str.charAt(str.length() - 1));
            }
            str = sb.toString();
        }
    return str;
    }
}