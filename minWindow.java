public class Solution {
        public String minWindow(String S, String T) {
        int[] counter1 = new int[128];
        int[] counter2 = new int[128];

        for (int i = 0; i < T.length(); i++) {
            counter1[T.charAt(i)]++;
        }

        int minStart = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int hit = 0;
        while (end < S.length()) {
            if (counter2[S.charAt(end)] < counter1[S.charAt(end)]) {
                hit++;
            }
            counter2[S.charAt(end)]++;

            if (hit == T.length()) {
                while (start <= end) {
                    if (counter2[S.charAt(start)] > counter1[S.charAt(start)]) {
                        counter2[S.charAt(start)]--;
                    } else {
                        int len = end - start + 1;
                        if (minLen > len) {
                            minStart = start;
                            minLen = len;
                        }
                        break;
                    }
                    start++;
                }
            }

            end++;
        }
        if (minLen != Integer.MAX_VALUE)
            return S.substring(minStart, minStart + minLen);
        return "";
    }
}