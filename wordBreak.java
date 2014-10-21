// got TLE when using recursion without dp
// got TLE when using HashMap to store visited substring
// changed to int table and got accepted 
// table used to store 3 states: unvisited; word is valid; word is not valid, can it just be two states?

public class Solution {
     public boolean wordBreak(String s, Set<String> dict) {
        if (s.length() == 0) return false;
        int[] table = new int[s.length()+1];

        for (int i = 0; i < table.length; i++) table[i] = -1;
        table[s.length()] = 1;
        return wordBreak(s, dict, table, 0);
    }

    public boolean wordBreak(String s, Set<String> dict, int[] table, int index) {
        if (table[index] != -1) {
            return table[index] == 1 ? true : false;
        }

        for (int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);
            boolean isWord = dict.contains(word);
            boolean isRestWord = wordBreak(s.substring(i, s.length()), dict, table, index + i);
            table[index+i] = (isRestWord) ? 1 : 0;
            if (isWord && isRestWord) {
                return true;
            }
        }


        return false;
    }
}