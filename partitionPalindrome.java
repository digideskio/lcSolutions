public class Solution {
    HashMap<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
    public List<List<String>> partition(String s) {
        if (map.containsKey(s)) return map.get(s);
        List<List<String>> lst = new LinkedList<List<String>>();
        if (s == null || s.length() == 0) return lst;
        
        int index = 1;
        while (index <= s.length()) {
            String str = s.substring(0, index);
            if (isValid(str)) {
                if (str.length() == s.length()) {
                    List<String> newList = new LinkedList<String>();
                    newList.add(str);
                    lst.add(newList);
                } else {
                    List<List<String>> rest = partition(s.substring(index));
                    for (List<String> listRest : rest) {
                        List<String> newList = new LinkedList<String>(listRest);
                        newList.add(0, str);
                        lst.add(newList);
                    }
                }
                
            }
            index++;
        }
        map.put(s, lst);
        
        return lst;
    }
    
    boolean isValid(String s) {
        String reversed = new StringBuffer(s).reverse().toString();
        return reversed.equals(s);
    }
}