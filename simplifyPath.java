// not familiar with unix style paths, the solution is from discussion. 

public class Solution {
    public String simplifyPath(String path) {
         String[] paths = path.split("/");
        Stack<String> stack = new Stack<String>();
        for (String s : paths) {
    
            if (s.length() > 0) {
                if (s.equals("..")) {
                    if (stack.empty()) {
                        continue;
                    }
                    stack.pop();
                } else if (s.equals(".")) {
                    continue;
                } else {
                    stack.push(s);
                }
            }
    
        }
        String result = "";
        while (!stack.empty()) {
            result = "/" + stack.pop() + result;
        }
        if (result.length() == 0) {
            result += "/";
        }
        return result;
        }
}