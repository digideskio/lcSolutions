// Two solutions, both dp
// optimal solution only uses only O(n) space
// second one uses 2*2 table to store

// This is a good question to let you think in subquestions.

public class Solution {
    public int minDistanceOptimal(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        
        int[] table = new int[len2+1];
        for (int i = 0; i < table.length; i++) {
            table[i] = i;
        }
        
        for (int i = 1; i <= len1; i++) {
            int prev = i;
            for (int j = 1; j <= len2; j++) {
                int cur;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cur = table[j-1];
                } else {
                    cur = Math.min(Math.min(table[j-1], prev), table[j]) + 1;
                }
                
                table[j-1] = prev;
                prev = cur;
            }
            table[len2] = prev;
        }
        
        return table[len2];
    }


    public  static int minDistance(String word1, String word2) {

        int l1 = word1.length();
        int l2 = word2.length();

        int[][] f = new int[l1+1][l2+1];
        for (int j = 1; j <= l2; ++j)
            f[0][j] = j;
        for (int j = 1; j <= l1; ++j)
            f[j][0] = j;



        for (int i = 1; i <= l1; ++i)
        {

            for (int j = 1; j <= l2; ++j)
            {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i-1][j-1];
                } else {
                    f[i][j] = Math.min(Math.min(f[i][j-1], f[i-1][j-1]), f[i-1][j]) + 1;
                }

            }

        }
        return f[l1][l2];

    }
}