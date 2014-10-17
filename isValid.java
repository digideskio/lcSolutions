// stack implementation
// use exception to catch empty stack case

import java.util.EmptyStackException;

public class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) return true;

        Stack<Character> stack = new Stack<Character>();

        while (!s.isEmpty()) {
            char c = s.charAt(0);
            try {
                switch(c) {
                    case '(':
                    case '[':
                    case '{':
                        stack.push(c);
                        break;
                    case ')':
                        if (stack.pop() != '(') return false;
                        break;
                    case ']':
                        if (stack.pop() != '[') return false;
                        break;
                    case '}':
                        if (stack.pop() != '{') return false;
                        break;
                }
            } catch (EmptyStackException e) {
                return false;
            }
            
            s = s.substring(1);
        }

        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }
}