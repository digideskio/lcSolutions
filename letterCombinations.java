// simple dfs search solution
// should be as good as it can be, since every path is valid
// just pay attention to member variable initialization: can use init block or constructor, TODO CHECK THE DIFFERENCE

public class Solution {
    char[][] mapping = new char[10][];

    {
        mapping[2] = "abc".toCharArray();
        mapping[3] = "def".toCharArray();
        mapping[4] = "ghi".toCharArray();
        mapping[5] = "jkl".toCharArray();
        mapping[6] = "mno".toCharArray();
        mapping[7] = "pqrs".toCharArray();
        mapping[8] = "tuv".toCharArray();
        mapping[9] = "wxyz".toCharArray();

    }

    
    
    public List<String> letterCombinations(String digits) {
        List<String> lst = new LinkedList<String>();
        if (digits.length() == 0) {
            lst.add("");
            return lst;
        }
        
        
        int digit = digits.charAt(0) - '0';
        for (int i = 0; i < mapping[digit].length; i++) {
            List<String> strs = letterCombinations(digits.substring(1));
            for (String str : strs) {
                lst.add(Character.toString(mapping[digit][i]) + str);
            }
        }
        
        return lst;
    }
}