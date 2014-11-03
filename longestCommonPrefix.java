public class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        if (strs == null || strs.length == 0) return sb.toString();
        if (strs.length == 1) return strs[0];
        
        for (int i = 0; ; i++) {
            boolean success = true;
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].length() <= i || strs[j+1].length() <= i) {
                    success = false;
                    break;
                }
                if (strs[j].charAt(i) != strs[j+1].charAt(i)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        
        return sb.toString();
    }
}