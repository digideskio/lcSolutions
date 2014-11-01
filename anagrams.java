// some special sort function tailored for string ranging from 'a' to 'z'
String sort(String s)
    {
        char []freq = new char[26];
        for (int i=0; i<s.length(); i++)
        {
            int ind = (int)(s.charAt(i) - 97);
            if (ind>=0 && ind <=26)
                freq[ind]++;
        }
        String sorted = "";
        for (int i=0; i<26; i++)
        {
            for (int j=0; j<freq[i]; j++)
            {
                char c = (char)(97+i);
                sorted += c;
            }
        }
     return sorted;
    }

public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> lst = new LinkedList<String>();
       HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String s : strs) {
            char[] ar = s.toCharArray();
        	Arrays.sort(ar);
        	String sorted = String.valueOf(ar);
        	if (map.containsKey(sorted)) {
        	    map.get(sorted).add(s);
        	} else {
        	    List<String> l = new LinkedList<String>();
        	    l.add(s);
        	    map.put(sorted, l);
        	}
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                lst.addAll(map.get(key));
            }
        }
        return lst;
    }
}