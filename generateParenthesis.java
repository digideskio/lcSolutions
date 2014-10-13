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
        if (right > 0 && checkRightValid(str)) {
            list.addAll(generateParenthesis(str + ")", left, right - 1));
        }

        
        return list;
    }
    
    public int countOccurance(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == c)
            {
                 count++;
            }
        }
        return count;
    }
    
    public boolean checkRightValid(String str) {
        int left = countOccurance(str, '(');
        int right = str.length() - left;
        if (left > right) {
            return true;
        }
        return false;
    }
}