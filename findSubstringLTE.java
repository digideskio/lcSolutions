// this solution got TLEed on last large dataset

public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        // ask for corner scenario
        Map<String,Integer> map = new HashMap<String,Integer>();

        for (String s : L) {
            map.put(s, 0);
        }

        int len = L[0].length();
        for (int i = 0; i < S.length() - len; i++) {
            for (int j = 0; j < L.length; j++) {
                if (S.substring(i, i + len).equals(L[j])) {
                    map.put(L[j], map.get(L[j]) + 1);
                }
            }
        }

        return findSubstring(S, L, map);
    }

    List<Integer> findSubstring(String S, String[] L, Map<String,Integer> map) {
        List<Integer> lst = new LinkedList<Integer>();
        int len = L[0].length();
        for (int i = 0; i < S.length() - len * L.length; i++) {
            Map<String,Integer> count = new HashMap<String,Integer>(map);
            boolean success = true;
            int cnt = 0;
            for (int j = i; j <= S.length() - len; j += len) {

                String str = S.substring(j, j + len);
                if (map.containsKey(str)) {
                    if (count.get(str) <= 0) {
                        success = false;
                        break;
                    }
                    count.put(str, count.get(str) - 1);
                    cnt++;
                    if (cnt == L.length) break;
                } else {
                    success = false;
                    break;
                }
            }
            if (success) lst.add(i);
        }

        return lst;
    }
}