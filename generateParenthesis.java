public class Solution {
    public List<String> generateParenthesis(int n) {
        return generateParenthesis("", n, n);
    }
    
    List<String> generateParenthesis(String str, int left, int right) {
        List<String> list = new ArrayList<String>();
        if (left == 0 && right == 0) {
            list.add(str);
            return list;
        }
        
         if (left > 0) {
            list.addAll(generateParenthesis(str + "(", left - 1, right));
        }
        if (right > left) {
            list.addAll(generateParenthesis(str + ")", left, right - 1));
        }

        
        return list;
    }
    

}