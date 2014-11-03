public class Solution {
    public String convert(String s, int nRows) {
        if (nRows == 1) return s;
        List<List<Character>> lst = new ArrayList<List<Character>>();
        for (int i = 0; i < nRows; i++) {
            List<Character> l = new LinkedList<Character>();
            lst.add(l);
        }
        int step = (nRows - 1) * 2;
        for (int i = 0; i + step <= s.length(); i += step) {
            int offset = 0;
            while (offset < nRows) {
                lst.get(offset).add(s.charAt(i + offset));
                offset++;
            }
            while (offset < step) {
                lst.get(step - offset).add(s.charAt(i + offset));
                offset++;
            }
        }
        
        int remainder = s.length() % step;
        int start = s.length() - remainder;

        int offset = 0;
        while (start + offset < s.length()) {
            if (offset < nRows) {
                lst.get(offset).add(s.charAt(start + offset));
            } else {
                lst.get(step - offset).add(s.charAt(start + offset));
            }
            offset++;
        }


        StringBuffer sb = new StringBuffer("");
        for (List<Character> l : lst) {
            for (Character c : l) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}