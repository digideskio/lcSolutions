public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int start = 0;
        
        int maxLen = 0;
        int curLen = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (; start < s.length(); start++) {
            if (s.charAt(start) == ')') {
                if (stack.isEmpty()) {
                    maxLen = Math.max(curLen, maxLen);
                    curLen = 0;
                } else {
                    curLen += 2 + stack.pop();
                    maxLen = Math.max(curLen, maxLen);
                }
            } else {
                maxLen = Math.max(curLen, maxLen);
                stack.push(curLen);
                curLen = 0;
            }
        }
        
        return maxLen;
        
        
    }
}