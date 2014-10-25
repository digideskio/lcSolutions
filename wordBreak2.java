// word break II 
// solved by using hashmap to store visited node

public class Solution {
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    public List<String> wordBreak(String s, Set<String> dict) {
        if (map.containsKey(s)) return map.get(s);
        List<String> lst = new LinkedList<String>();
        if (s.length() == 0) return lst;

        for (int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);

            if (dict.contains(word)) {
                if (i == s.length()) {
                    lst.add(word);
                } else {
                    List<String> wordList = wordBreak(s.substring(i), dict);
                    for (String item : wordList) {
                        lst.add(word + " " + item);
                    }
                }
            }
        }
        map.put(s, lst);

        return lst;
    }
}