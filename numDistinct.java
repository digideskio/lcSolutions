public class Solution {
    public int numDistinct(String S, String T) {
        if (S.length() < T.length()) return 0;
        
        int[][] table = new int[T.length()+1][S.length()+1];
        for (int i = 0; i <= S.length(); i++) {
            table[0][i] = 1;
        }
        
        for (int i = 1; i <= T.length(); i++) {
            for (int j = 1; j <= S.length(); j++) {
                if (S.charAt(j-1) == T.charAt(i-1)) {
                    table[i][j] = table[i-1][j-1] + table[i][j-1];
                } else {
                    table[i][j] = table[i][j-1];
                }
            }
        }
        
        return table[T.length()][S.length()];
    }

    // optimal solution that uses only O(n) space

    public int numDistinct(String S, String T) {
        if (S.length() < T.length()) return 0;
        
        int[] table = new int[S.length()+1];
        for (int i = 0; i <= S.length(); i++) {
            table[i] = 1;
        }
        int last = 0;
        int cur = 0;
        for (int i = 1; i <= T.length(); i++) {
            last = 0;
            for (int j = 1; j <= S.length(); j++) {
                if (S.charAt(j-1) == T.charAt(i-1)) {
                    cur = last + table[j-1];
                    //table[i][j] = table[i-1][j-1] + table[i][j-1];
                } else {
                    //table[i][j] = table[i][j-1];
                    cur = last;
                }
                
                table[j-1] = last;
                last = cur;
            }
            table[S.length()] = cur;
        }
        
        return table[S.length()];
    }
    
}