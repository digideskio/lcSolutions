public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        int steps = 1;
        Stack<String> stack = new Stack<String>();
        Set<String> set = new HashSet<String>();
        stack.add(start);
        set.add(start);
        
        while (!stack.isEmpty()) {
            Stack<String> next = new Stack<String>();
            steps++;
            
            for (String w : stack) {
                for (int i = 0; i < w.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != w.charAt(i)) {
                            String word = w.substring(0, i) + c + w.substring(i + 1);
                            if (word.equals(end)) return steps;
                            if (dict.contains(word) && !set.contains(word)) {
                                next.add(word);
                                set.add(word);
                            }
                        }
                    }
                }
            }
            stack = next;
        }
        
        return 0;
    }
}