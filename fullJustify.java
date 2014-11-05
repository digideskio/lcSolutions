// very trivial question that needs special care at the last line;

public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> temp = new LinkedList<String>();
        List<String> lst = new LinkedList<String>();
        
        int len = 0;
        for (String word : words) {
            if (temp.size() == 0) {
                temp.add(word);
                len = word.length();
                continue;
            }
            if (len + word.length() + 1 <= L) {
                temp.add(word);
                len += word.length() + 1;
            } else {
                lst.add(generateLine(temp, len, L));
                temp.clear();
                temp.add(word);
                len = word.length();
            }
        }
        // the rest
        if (temp.size() != 0) {
            lst.add(generateLastLine(temp, len, L));
        }
        
        return lst;
    }
    
    String getNSpace(int n) {
        StringBuffer sb = new StringBuffer();
        while (n > 0) {
            sb.append(" ");
            n--;
        }
        return sb.toString();
    }
    
    String generateLine(List<String> list, int len, int L) {
        int nSpace = list.size() - 1;
        if (nSpace == 0) {
            return list.get(0) + getNSpace(L - list.get(0).length()); 
        }
        int wordLen = len - nSpace;
        int space = (L - wordLen) / nSpace;
        int nHighSpace = (L - wordLen) % nSpace;
        StringBuffer sb = new StringBuffer(list.get(0));
        int index = 1;
        for (; index <= nHighSpace; index++) {
            sb.append(getNSpace(space + 1) + list.get(index));
        }
        for (; index <= nSpace; index++) {
            sb.append(getNSpace(space) + list.get(index));
        }
        return sb.toString();
    }
    
    String generateLastLine(List<String> list, int len, int L) {
        int nSpace = list.size();
        if (nSpace == 1) {
            return list.get(0) + getNSpace(L - list.get(0).length()); 
        }
        int wordLen = len - nSpace;
        int space = (L - wordLen) / nSpace;
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for (; index < nSpace; index++) {
            sb.append(list.get(index) + " ");
        }
        sb.append(getNSpace(L - sb.length()));
        return sb.toString();
    }
}