// good example of using dp

// bfs got TLEed, depth too high if as depth gets deeper

public class Solution {
        public int minCut(String s) {
        if (s == null) return 0;
       

        int[] cuts = new int[s.length()+1];
        boolean[][] isPal = new boolean[s.length()][s.length()+1];

        for (int i = 0; i < cuts.length; i++) {
            cuts[i] = Integer.MAX_VALUE;
        }
        cuts[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (j == i - 1 || (s.charAt(i - 1) == s.charAt(j) && (j == i - 2 || isPal[j+1][i-1]))) {
                    isPal[j][i] = true;

                    if (j != 0) cuts[i] = Math.min(cuts[i], cuts[j] + 1);
                    else cuts[i] = 0;
                }

            }
        }

        return cuts[s.length()];
    }

}