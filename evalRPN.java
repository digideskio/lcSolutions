public class Solution {
     void Operator(char c) {
        int first, second;
        
        int result = 0;
        
        second = stack.pop();
        first = stack.pop();
        switch (c) {
            case '+':
                result = first + second;
                break;
            case '-':
                result = first - second;
                break;
            case '*':
                result = first * second;
                break;
            case '/':
                result = first / second;
                break;
        }
        stack.push(result);
    }

    Stack<Integer> stack = new Stack<Integer>();
    public int evalRPN(String[] tokens) {

        for (String s : tokens) {
            char[] c = s.toCharArray();
            if (s.equals("+") || s.equals("/") || s.equals("-") || s.equals("*") ) {
                Operator(c[0]);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }
}